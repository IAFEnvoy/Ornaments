package iafenvoy.ornaments.renderer.wings;

import fi.dy.masa.malilib.util.Color4f;
import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.config.WingInfo;
import iafenvoy.ornaments.renderer.wings.models.*;
import iafenvoy.ornaments.utils.ClientUtil;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class WingRenderer extends FeatureRenderer<ClientPlayerEntity, PlayerEntityModel<ClientPlayerEntity>> {

    public WingRenderer(FeatureRendererContext<ClientPlayerEntity, PlayerEntityModel<ClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider provider, int light, ClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isSpectator() || entity.isInvisible() || !entity.isAlive())
            return;
        if (!entity.getName().asString().equals(ClientUtil.getRenderPlayer()))
            return;
        if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA || !WingInfo.wing.shouldRender())
            return;
        renderTexture(WingInfo.wing, matrices, provider, light, entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    }

    private void renderTexture(WingInfo info, MatrixStack matrices, VertexConsumerProvider provider, int light, ClientPlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        WingType type = info.getType();
        WingEntityModel<ClientPlayerEntity> rightWing;
        WingEntityModel<ClientPlayerEntity> leftWing;
        if (type == WingType.Feather) {
            leftWing = new FeatheredWingModel<>(true);
            rightWing = new FeatheredWingModel<>(false);
        } else if (type == WingType.Leather) {
            leftWing = new LeatherWingModel<>(true);
            rightWing = new LeatherWingModel<>(false);
        } else if (type == WingType.Light) {
            leftWing = new LightWingsModel<>(true);
            rightWing = new LightWingsModel<>(false);
        } else if (type == WingType.Zanzas) {
            leftWing = new ZanzasWingsModel<>(true);
            rightWing = new ZanzasWingsModel<>(false);
        } else if (type == WingType.Tech) {
            leftWing = new TechWingsModel<>(true);
            rightWing = new TechWingsModel<>(false);
        } else
            return;

        Identifier layer1 = new Identifier(OrnamentClient.MOD_ID, "textures/wing/" + type.getStringValue() + "_wings.png");
        Identifier layer2 = new Identifier(OrnamentClient.MOD_ID, "textures/wing/" + type.getStringValue() + "_wings_2.png");

        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(leftWing);
        leftWing.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.renderWings(leftWing, matrices, provider, light, layer2, info.getLeft2());
        this.renderWings(leftWing, matrices, provider, light, layer1, info.getLeft1());
        matrices.pop();

        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(rightWing);
        rightWing.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.renderWings(rightWing, matrices, provider, light, layer2, info.getRight2());
        this.renderWings(rightWing, matrices, provider, light, layer1, info.getRight1());
        matrices.pop();
    }

    private void renderWings(WingEntityModel<ClientPlayerEntity> model, MatrixStack matrices, VertexConsumerProvider provider, int light, Identifier texture, Color4f color) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(provider, RenderLayer.getEntityTranslucent(texture), false, false);
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b, color.a);
    }
}
