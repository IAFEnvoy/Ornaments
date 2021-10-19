package ornaments.Items.Cape;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import ornaments.Client.OrnamentClient;
import ornaments.Config.Configs;

@Environment(EnvType.CLIENT)
public class CapeRender<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private BannerInfo info = new BannerInfo();
  private CapeModel<T> model = new CapeModel<T>();

  public CapeRender(FeatureRendererContext<T, M> context) {
    super(context);
  }

  public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity,
      float f, float g, float h, float j, float k, float l) {
    if (livingEntity instanceof PlayerEntity) {
      if (!livingEntity.getName().getString().equals(Configs.General.User.getStringValue()))
        return;
      if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA)
        return;
      if (!Configs.Cape.SHOW_CAPE.getBooleanValue())
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
      if (r < 0.0F) {
        r = 0.0F;
      }

      final float t = MathHelper.lerp(h, player.prevStrideDistance, player.strideDistance);
      q += MathHelper.sin(MathHelper.lerp(h, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;
      if (player.isInSneakingPose()) {
        q += 25.0F;
      }

      matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
      matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
      matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));

      if (Configs.Cape.UseImage.getBooleanValue()) {
        Identifier texture = new Identifier(OrnamentClient.MOD_ID,
            "textures/addons/" + Configs.Cape.ImageName.getStringValue() + ".png");
        VertexConsumer consumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
            RenderLayer.getArmorCutoutNoCull(texture), false, false);
        model.render(matrixStack, consumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
      } else {
        setPattern();
        List<Pair<String, Color4f>> list = info.getPatterns();
        for (int count = 0; count < list.size(); count++) {
          Pair<String, Color4f> pair = list.get(count);
          if (pair.getFirst().equals(""))
            break;
          Identifier texture = new Identifier("minecraft", "textures/entity/banner/" + pair.getFirst() + ".png");
          VertexConsumer consumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
              RenderLayer.getArmorCutoutNoCull(texture), false, false);
          model.render(matrixStack, consumer, i, OverlayTexture.DEFAULT_UV, pair.getSecond().r, pair.getSecond().g,
              pair.getSecond().b, pair.getSecond().a);
        }
      }
      matrixStack.pop();
    }
  }

  private void setPattern() {
    info = new BannerInfo(Configs.Cape.colorbase.getColor(),
        new Pattern(Configs.Cape.name1.getStringValue(), Configs.Cape.color1.getColor()),
        new Pattern(Configs.Cape.name2.getStringValue(), Configs.Cape.color2.getColor()),
        new Pattern(Configs.Cape.name3.getStringValue(), Configs.Cape.color3.getColor()),
        new Pattern(Configs.Cape.name4.getStringValue(), Configs.Cape.color4.getColor()),
        new Pattern(Configs.Cape.name5.getStringValue(), Configs.Cape.color5.getColor()),
        new Pattern(Configs.Cape.name6.getStringValue(), Configs.Cape.color6.getColor()),
        new Pattern(Configs.Cape.name7.getStringValue(), Configs.Cape.color7.getColor()),
        new Pattern(Configs.Cape.name8.getStringValue(), Configs.Cape.color8.getColor()));
  }
}