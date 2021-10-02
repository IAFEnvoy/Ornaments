package ornaments.Items.OFcape;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import ornaments.Config.Configs;
import ornaments.Util.RenderInfo;
import ornaments.Util.RenderInfo.RenderThings;

@Environment(EnvType.CLIENT)
public class ElytraRender<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private final ElytraWingModel<T> leftWing = new ElytraWingModel<>(false);
  private final ElytraWingModel<T> rightWing = new ElytraWingModel<>(true);

  public ElytraRender(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity,
      float f, float g, float h, float j, float k, float l) {
    RenderInfo.elytra = livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA;
    if (RenderInfo.elytra) {
      if (!RenderInfo.ifRender(RenderThings.ELYTRA, (PlayerEntity)livingEntity)
          && ((PlayerEntity) livingEntity).getName().asString().equals(Configs.General.User.getStringValue()))
        return;
      matrixStack.push();
      matrixStack.translate(0.0D, 0.0D, 0.125D);
      renderSplit(matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l,
          livingEntity.getEquippedStack(EquipmentSlot.CHEST));
      matrixStack.pop();
    }
  }

  public void renderSplit(MatrixStack matrixStackIn, VertexConsumerProvider vertexConsumerProvider, int i,
      T livingEntity, float f, float g, float h, float j, float k, float l, ItemStack elytra) {
    renderSplitFallback(matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l, elytra, leftWing);
    renderSplitFallback(matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l, elytra, rightWing);
  }

  public void renderSplitFallback(MatrixStack matrixStackIn, VertexConsumerProvider vertexConsumerProvider, int i,
      T livingEntity, float f, float g, float h, float j, float k, float l, ItemStack elytra,
      ElytraWingModel<T> wingIn) {
    Identifier elytraTexture;
    if (livingEntity instanceof AbstractClientPlayerEntity) {
      AbstractClientPlayerEntity abscp = (AbstractClientPlayerEntity) livingEntity;
      if (abscp.canRenderElytraTexture() && abscp.getElytraTexture() != null
          && Configs.Cape.SHOW_CAPE.getBooleanValue())
        elytraTexture = abscp.getElytraTexture();
      else if (abscp.canRenderCapeTexture() && abscp.getCapeTexture() != null
          && abscp.isPartVisible(PlayerModelPart.CAPE) && Configs.Cape.SHOW_CAPE.getBooleanValue())
        elytraTexture = abscp.getCapeTexture();
      else if (PlayerHandler.fromPlayer(abscp) != null && abscp.isPartVisible(PlayerModelPart.CAPE)
          && Configs.Cape.SHOW_CAPE.getBooleanValue())
        elytraTexture = PlayerHandler.fromPlayer(abscp);
      else {
        elytraTexture = new Identifier("minecraft", "textures/entity/elytra.png");
      }
    } else
      elytraTexture = new Identifier("minecraft", "textures/entity/elytra.png");
    this.getContextModel().copyStateTo(wingIn);
    wingIn.setAngles(livingEntity, f, g, j, k, l);
    VertexConsumer glintConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
        RenderLayer.getArmorCutoutNoCull(elytraTexture), false, elytra.hasGlint());
    wingIn.render(matrixStackIn, glintConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
  }
}
