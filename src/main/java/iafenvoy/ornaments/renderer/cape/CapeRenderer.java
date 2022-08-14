package iafenvoy.ornaments.renderer.cape;

import iafenvoy.ornaments.config.CapeInfo;
import iafenvoy.ornaments.renderer.cape.type.Pattern;
import iafenvoy.ornaments.utils.ClientUtil;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class CapeRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private final CapeModel<AbstractClientPlayerEntity> model = new CapeModel<>();

    public CapeRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider provider, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        final CapeInfo info = CapeInfo.cape;
        if (entity.isSpectator() || entity.isInvisible() || !entity.isAlive())
            return;
        if (!entity.getName().asString().equals(ClientUtil.getRenderPlayer()))
            return;
        if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA || !info.shouldRender())
            return;
        matrices.push();
        if (entity.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
            if (entity.isInSneakingPose())
                matrices.translate(0.0D, 0.1D, 0.115D);
            else
                matrices.translate(0.0D, 0.0D, 0.125D);
        } else {
            if (entity.isInSneakingPose())
                matrices.translate(0.0D, 0.1D, 0.125D);
            else
                matrices.translate(0.0D, 0.0D, 0.225D);
        }

        final double capeX = MathHelper.lerp(tickDelta, entity.prevCapeX, entity.capeX) - MathHelper.lerp(tickDelta, entity.prevX, entity.getX());
        final double capeY = MathHelper.lerp(tickDelta, entity.prevCapeY, entity.capeY) - MathHelper.lerp(tickDelta, entity.prevY, entity.getY());
        final double capeZ = MathHelper.lerp(tickDelta, entity.prevCapeZ, entity.capeZ) - MathHelper.lerp(tickDelta, entity.prevZ, entity.getZ());
        final float bodyYaw = entity.bodyYaw;
        final double bodyYawY = MathHelper.sin(bodyYaw * 0.017453292F);
        final double bodyYawX = -MathHelper.cos(bodyYaw * 0.017453292F);
        float q = (float) capeY * 10.0F;
        q = MathHelper.clamp(q, -6.0F, 32.0F);
        float r = (float) (capeX * bodyYawY + capeZ * bodyYawX) * 100.0F;
        r = MathHelper.clamp(r, 0.0F, 150.0F);
        float s = (float) (capeX * bodyYawX - capeZ * bodyYawY) * 100.0F;
        s = MathHelper.clamp(s, -20.0F, 20.0F);
        if (r < 0.0F) r = 0.0F;

        final float t = MathHelper.lerp(tickDelta, entity.prevStrideDistance, entity.strideDistance);
        q += MathHelper.sin(MathHelper.lerp(tickDelta, entity.prevHorizontalSpeed, entity.horizontalSpeed) * 6.0F) * 32.0F * t;
        if (entity.isInSneakingPose()) q += 25.0F;

        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));

        renderBanner(info, matrices, provider, light);
        matrices.pop();
    }

    private void renderBanner(CapeInfo info, MatrixStack matrices, VertexConsumerProvider provider, int light) {
        float[] f = info.getBaseColor().getDyeColor().getColorComponents();
        model.render(matrices, ModelLoader.BANNER_BASE.method_30001(provider, RenderLayer::getEntitySolid, false), light, OverlayTexture.DEFAULT_UV, f[0], f[1], f[2], 1.0F);
        for (int i = 0; i < 8; i++) {
            if (info.getPatternById(i) == Pattern.BASE) break;
            Identifier texture = new Identifier("minecraft", "entity/banner/" + info.getPatternById(i).getStringValue());
            SpriteIdentifier spriteIdentifier = new SpriteIdentifier(TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE, texture);
            float[] f1 = info.getColorById(i).getDyeColor().getColorComponents();
            model.render(matrices, spriteIdentifier.getVertexConsumer(provider, RenderLayer::getEntityNoOutline), i, OverlayTexture.DEFAULT_UV, f1[0], f1[1], f1[2], 1.0F);
        }
    }
}
