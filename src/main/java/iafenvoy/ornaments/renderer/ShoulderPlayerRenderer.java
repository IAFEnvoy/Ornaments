package iafenvoy.ornaments.renderer;

import iafenvoy.ornaments.config.ShoulderPlayerInfo;
import iafenvoy.ornaments.utils.ClientUtil;
import iafenvoy.ornaments.utils.FakePlayerEntity;
import iafenvoy.ornaments.utils.LocalSkin;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

public class ShoulderPlayerRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public ShoulderPlayerRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider provider, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getName().getString().equals(ClientUtil.getRenderPlayer())) {
            final ShoulderPlayerInfo left = ShoulderPlayerInfo.leftPlayer, right = ShoulderPlayerInfo.rightPlayer;
            if (left.shouldRender()) {
                if (!LocalSkin.data.containsKey(left.getPlayerName().toLowerCase()))
                    new LocalSkin(left.getPlayerName()).load();
                LocalSkin data = LocalSkin.data.get(left.getPlayerName().toLowerCase());
                if (data.getIdentifier() != null) {
                    PlayerEntityModel<AbstractClientPlayerEntity> model = new PlayerEntityModel<>(1, left.isThinArm());
                    this.setModelPose(model);
                    AbstractClientPlayerEntity player = new FakePlayerEntity.DummyClientPlayerEntity(data.getIdentifier());
                    this.renderPlayer(model, player, matrices, provider, light, entity, tickDelta, true);
                }
            }
            if (right.shouldRender()) {
                if (!LocalSkin.data.containsKey(right.getPlayerName().toLowerCase()))
                    new LocalSkin(right.getPlayerName()).load();
                LocalSkin data = LocalSkin.data.get(right.getPlayerName().toLowerCase());
                if (data.getIdentifier() != null) {
                    PlayerEntityModel<AbstractClientPlayerEntity> model = new PlayerEntityModel<>(1, right.isThinArm());
                    this.setModelPose(model);
                    AbstractClientPlayerEntity player = new FakePlayerEntity.DummyClientPlayerEntity(data.getIdentifier());
                    this.renderPlayer(model, player, matrices, provider, light, entity, tickDelta, false);
                }
            }
        }
    }

    private void renderPlayer(PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, MatrixStack matrices, VertexConsumerProvider provider, int light, AbstractClientPlayerEntity entity, float tickDelta, boolean left) {
        matrices.push();
        matrices.scale(-0.2F, -0.2F, 0.2F);
        matrices.translate(left ? -1.75D : 1.75D, 1D, 0);
        if (entity.isInSneakingPose())
            matrices.translate(0, -1D, 0);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180));

        model.animateModel(player, 0, 0, tickDelta);
        model.setAngles(player, 0, 0, 0, 0, 0);
        RenderLayer layer = model.getLayer(player.getSkinTexture());
        if (layer != null) {
            VertexConsumer consumer = provider.getBuffer(layer);
            int uv = OverlayTexture.packUv(0, 10);
            model.render(matrices, consumer, light, uv, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        matrices.pop();
    }

    private void setModelPose(PlayerEntityModel<AbstractClientPlayerEntity> model) {
        model.riding = true;
        model.setVisible(true);
        model.hat.visible = model.jacket.visible = true;
        model.leftPants.visible = model.rightPants.visible = true;
        model.leftSleeve.visible = model.rightSleeve.visible = true;
        model.sneaking = false;
        model.leftArmPose = model.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
        model.handSwingProgress = 0;
        model.child = false;
    }
}
