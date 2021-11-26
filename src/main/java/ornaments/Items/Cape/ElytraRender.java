package ornaments.Items.Cape;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import fi.dy.masa.malilib.util.Color4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import ornaments.Client.OrnamentClient;
import ornaments.Config.Configs;
import ornaments.multiplayer.PlayerInfo;
import ornaments.multiplayer.OrnamentsNetwork;

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
    if (livingEntity.isSpectator() || !livingEntity.isAlive())
      return;
    if (livingEntity instanceof PlayerEntity)
      if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) {
        if (((PlayerEntity) livingEntity).getName().asString().equals(Configs.General.User.getStringValue())) {
          if (!Configs.Wings.Overwrite_Elytra.getBooleanValue())
            renderSplit(setPattern(), Configs.Cape.SHOW_CAPE.getBooleanValue(), matrixStack, vertexConsumerProvider, i,
                livingEntity, f, g, h, j, k, l, livingEntity.getEquippedStack(EquipmentSlot.CHEST));
        } else if (OrnamentsNetwork.hasInfo(((PlayerEntity) livingEntity).getName().asString())) {
          PlayerInfo info = OrnamentsNetwork.getInfo(((PlayerEntity) livingEntity).getName().asString());
          if (!info.overwriteElytra)
            renderSplit(info.banner, info.showCape, matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k,
                l, livingEntity.getEquippedStack(EquipmentSlot.CHEST));
        }
      }
  }

  public void renderSplit(BannerInfo pattern, boolean showCape, MatrixStack matrixStackIn,
      VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k,
      float l, ItemStack elytra) {
    matrixStackIn.push();
    matrixStackIn.translate(0.0D, 0.0D, 0.125D);
    renderSplitFallback(pattern, showCape, matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l,
        elytra, leftWing);
    renderSplitFallback(pattern, showCape, matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l,
        elytra, rightWing);
    matrixStackIn.pop();
  }

  public void renderSplitFallback(BannerInfo pattern, boolean showCape, MatrixStack matrixStackIn,
      VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k,
      float l, ItemStack elytra, ElytraWingModel<T> wingIn) {
    if (livingEntity instanceof AbstractClientPlayerEntity) {
      Identifier elytraTexture = new Identifier(OrnamentClient.MOD_ID, "textures/entity/elytra.png");
      this.getContextModel().copyStateTo(wingIn);
      wingIn.setAngles(livingEntity, f, g, j, k, l);
      VertexConsumer glintConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider,
          RenderLayer.getEntityNoOutline(elytraTexture), false, elytra.hasGlint());
      wingIn.render(matrixStackIn, glintConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

      if (!showCape)
        return;

      List<Pair<String, Color4f>> list = pattern.getPatterns();
      for (int count = 0; count < list.size(); count++) {
        Pair<String, Color4f> pair = list.get(count);
        if (pair.getFirst().equals(""))
          break;
        Identifier texture = new Identifier(OrnamentClient.MOD_ID,
            "textures/entity/elytra_banner/" + pair.getFirst() + ".png");
        VertexConsumer consumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
            RenderLayer.getArmorCutoutNoCull(texture), false, elytra.hasGlint());
        wingIn.render(matrixStackIn, consumer, i, OverlayTexture.DEFAULT_UV, pair.getSecond().r, pair.getSecond().g,
            pair.getSecond().b, pair.getSecond().a);
      }
    }
  }

  private BannerInfo setPattern() {
    return new BannerInfo(Configs.Cape.colorbase.getColor(),
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
