package iafenvoy.ornaments.Items.Cape;

import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.Config.Configs;
import iafenvoy.ornaments.Items.Cape.Enum.ColorEnum;
import iafenvoy.ornaments.Items.Cape.Enum.Pattern;
import iafenvoy.ornaments.Util.PlayerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class CapeRender<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private CapeModel<T> model = new CapeModel<T>();

  public CapeRender(FeatureRendererContext<T, M> context) {
    super(context);
  }

  public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity,
      float f, float g, float h, float j, float k, float l) {
    if (livingEntity.isSpectator() || livingEntity.isInvisible() || !livingEntity.isAlive())
      return;
    if (livingEntity instanceof PlayerEntity) {
      if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA)
        return;
      PlayerEntity player = (PlayerEntity) livingEntity;
      model = new CapeModel<T>();
      matrixStack.push();
      if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
        if (livingEntity.isInSneakingPose()) {
          matrixStack.translate(0.0D, 0.1D, 0.115D);
        } else {
          matrixStack.translate(0.0D, 0.0D, 0.125D);
        }
      } else if (livingEntity.isInSneakingPose()) {
        matrixStack.translate(0.0D, 0.1D, 0.125D);
      } else {
        matrixStack.translate(0.0D, 0.0D, 0.225D);
      }
      final double d = MathHelper.lerp(h, player.prevCapeX, player.capeX)
          - MathHelper.lerp(h, player.prevX, player.getX());
      final double e = MathHelper.lerp(h, player.prevCapeY, player.capeY)
          - MathHelper.lerp(h, player.prevY, player.getY());
      final double m = MathHelper.lerp(h, player.prevCapeZ, player.capeZ)
          - MathHelper.lerp(h, player.prevZ, player.getZ());
      final float n = player.prevBodyYaw + (player.bodyYaw - player.prevBodyYaw);
      final double o = MathHelper.sin(n * 0.017453292F);
      final double p = -MathHelper.cos(n * 0.017453292F);
      float q = (float) e * 10.0F;
      q = MathHelper.clamp(q, -6.0F, 32.0F);
      float r = (float) (d * o + m * p) * 100.0F;
      r = MathHelper.clamp(r, 0.0F, 150.0F);
      float s = (float) (d * p - m * o) * 100.0F;
      s = MathHelper.clamp(s, -20.0F, 20.0F);
      if (r < 0.0F)
        r = 0.0F;

      final float t = MathHelper.lerp(h, player.prevStrideDistance, player.strideDistance);
      q += MathHelper.sin(MathHelper.lerp(h, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;
      if (player.isInSneakingPose())
        q += 25.0F;

      matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
      matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
      matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));

      if (livingEntity.getName().getString().equals(PlayerUtil.getRenderPlayer())) {
        if (Configs.INSTANCE.SHOW_CAPE.getBooleanValue())
          if (Configs.INSTANCE.UseImage.getBooleanValue())
            renderImage(
                new Identifier(OrnamentClient.MOD_ID,
                    "textures/addons/" + Configs.INSTANCE.ImageName.getStringValue() + ".png"),
                matrixStack, vertexConsumerProvider, i);
          else
            renderBanner(matrixStack, vertexConsumerProvider, i);
      }
      matrixStack.pop();
    }
  }

  private void renderImage(Identifier texture, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
      int i) {
    VertexConsumer consumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
        RenderLayer.getArmorCutoutNoCull(texture), false, false);
    model.render(matrixStack, consumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
  }

  private void renderBanner(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
      int i) {
        float[] f=((ColorEnum)Configs.INSTANCE.colorbase.getOptionListValue()).getDyeColor().getColorComponents();
    model.render(matrixStack,
        ModelLoader.BANNER_BASE.method_30001(vertexConsumerProvider, RenderLayer::getEntitySolid, false), i,
        OverlayTexture.DEFAULT_UV,f[0],f[1],f[2],1.0F);
    for (int count = 0; count < 8; count++) {
      if (Configs.INSTANCE.nameList[count].getOptionListValue() == Pattern.BASE)
        break;
      Identifier texture = new Identifier("minecraft",
          "entity/banner/" + Configs.INSTANCE.nameList[count].getOptionListValue().getStringValue());
      SpriteIdentifier spriteIdentifier = new SpriteIdentifier(TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE,
          texture);
      float[] f1 = ((ColorEnum) Configs.INSTANCE.colorList[count].getOptionListValue()).getDyeColor().getColorComponents();
      model.render(matrixStack,
          spriteIdentifier.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntityNoOutline), i,
          OverlayTexture.DEFAULT_UV, f1[0], f1[1], f1[2], 1.0F);
    }
  }
}