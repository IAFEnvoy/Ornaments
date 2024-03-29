package iafenvoy.ornaments.renderer.cape;

import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.config.CapeInfo;
import iafenvoy.ornaments.gui.settings.EntityGuiBase;
import iafenvoy.ornaments.renderer.cape.type.Pattern;
import iafenvoy.ornaments.utils.ClientUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ElytraRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private static final Identifier ORIGIN = new Identifier("textures/entity/elytra.png");
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private final ElytraWingModel<AbstractClientPlayerEntity> leftWing = new ElytraWingModel<>(false);
    private final ElytraWingModel<AbstractClientPlayerEntity> rightWing = new ElytraWingModel<>(true);

    public ElytraRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider provider, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isSpectator() || !entity.isAlive())
            return;
        if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) {
            if (entity.getName().asString().equals(ClientUtil.getRenderPlayer())) {
                final CapeInfo left = CapeInfo.leftElytra, right = CapeInfo.rightElytra;
                if (left.shouldRender())
                    renderSplitBanner(left, leftWing, matrices, provider, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
                else
                    renderOrigin(leftWing, matrices, provider, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
                if (right.shouldRender())
                    renderSplitBanner(right, rightWing, matrices, provider, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
                else
                    renderOrigin(rightWing, matrices, provider, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
            } else {
                renderOrigin(leftWing, matrices, provider, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
                renderOrigin(rightWing, matrices, provider, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
            }
        }
    }

    public void renderOrigin(ElytraWingModel<AbstractClientPlayerEntity> model, MatrixStack matrices, VertexConsumerProvider provider, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack elytra = entity.getEquippedStack(EquipmentSlot.CHEST);
        if (elytra.getItem() == Items.ELYTRA) {
            Identifier texture = ORIGIN;
            if (entity.canRenderElytraTexture() && entity.getElytraTexture() != null)
                texture = entity.getElytraTexture();
            else if (entity.canRenderCapeTexture() && entity.getCapeTexture() != null && entity.isPartVisible(PlayerModelPart.CAPE))
                texture = entity.getCapeTexture();

            matrices.push();
            matrices.translate(0.0D, 0.0D, 0.125D);

            if (!(client.currentScreen instanceof EntityGuiBase))
                PhysicalRenderer.render(matrices, entity, tickDelta, 0);

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(provider, RenderLayer.getArmorCutoutNoCull(texture), false, elytra.hasGlint());
            this.getContextModel().copyStateTo(model);
            model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
        }
    }

    public void renderSplitBanner(CapeInfo info, ElytraWingModel<AbstractClientPlayerEntity> wing, MatrixStack matrices, VertexConsumerProvider provider, int light, AbstractClientPlayerEntity entity, float limbAngle, float tickDelta, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);

        if (!(client.currentScreen instanceof EntityGuiBase))
            PhysicalRenderer.render(matrices, entity, tickDelta, 0);

        ItemStack elytra = entity.getEquippedStack(EquipmentSlot.CHEST);
        this.getContextModel().copyStateTo(wing);
        wing.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        Identifier elytraTexture = new Identifier(OrnamentClient.MOD_ID, "textures/entity/elytra.png");
        VertexConsumer glintConsumer = ItemRenderer.getDirectItemGlintConsumer(provider, RenderLayer.getEntityTranslucent(elytraTexture), false, elytra.hasGlint());
        float[] f_ = info.getBaseColor().getDyeColor().getColorComponents();
        wing.render(matrices, glintConsumer, light, OverlayTexture.DEFAULT_UV, f_[0], f_[1], f_[2], 1.0F);

        for (int i = 0; i < 8; i++) {
            if (info.getPatternById(i) == Pattern.BASE) break;
            Identifier texture = new Identifier(OrnamentClient.MOD_ID, "entity/elytra_banner/" + info.getPatternById(i).getStringValue());
            SpriteIdentifier spriteIdentifier = new SpriteIdentifier(TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE, texture);
            float[] f1 = info.getColorById(i).getDyeColor().getColorComponents();
            if (spriteIdentifier.getSprite().getId() != MissingSprite.getMissingSpriteId())
                wing.render(matrices, spriteIdentifier.getVertexConsumer(provider, RenderLayer::getEntityTranslucent), light, OverlayTexture.DEFAULT_UV, f1[0], f1[1], f1[2], 1.0F);
        }
        matrices.pop();
    }
}
