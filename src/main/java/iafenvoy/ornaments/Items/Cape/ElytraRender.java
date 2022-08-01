package iafenvoy.ornaments.Items.Cape;

import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.Config.Configs;
import iafenvoy.ornaments.Items.Cape.Enum.ColorEnum;
import iafenvoy.ornaments.Items.Cape.Enum.Pattern;
import iafenvoy.ornaments.Util.PlayerUtil;
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
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

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
    if (livingEntity instanceof AbstractClientPlayerEntity)
      if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) {
        if (((AbstractClientPlayerEntity) livingEntity).getName().asString().equals(PlayerUtil.getRenderPlayer())) {
          if (!Configs.INSTANCE.Overwrite_Elytra.getBooleanValue())
            renderSplit(Configs.INSTANCE.SHOW_CAPE.getBooleanValue(), matrixStack, vertexConsumerProvider, i,
                livingEntity, f, g, h, j, k, l, livingEntity.getEquippedStack(EquipmentSlot.CHEST));
        }
      }
  }

  public void renderSplit(boolean showCape, MatrixStack matrixStackIn,
      VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k,
      float l, ItemStack elytra) {
    matrixStackIn.push();
    matrixStackIn.translate(0.0D, 0.0D, 0.125D);
    renderSplitBanner(showCape, matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l,
        elytra, leftWing);
    renderSplitBanner(showCape, matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l,
        elytra, rightWing);
    matrixStackIn.pop();
  }

  public void renderSplitBanner(boolean showCape, MatrixStack matrixStackIn,
      VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k,
      float l, ItemStack elytra, ElytraWingModel<T> wingIn) {
    Identifier elytraTexture = new Identifier(OrnamentClient.MOD_ID, "textures/entity/elytra.png");
    this.getContextModel().copyStateTo(wingIn);
    wingIn.setAngles(livingEntity, f, g, j, k, l);
    VertexConsumer glintConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider,
        RenderLayer.getEntityNoOutline(elytraTexture), false, elytra.hasGlint());

    if (!showCape) {
      wingIn.render(matrixStackIn, glintConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
      return;
    }
    float[] f_ = ((ColorEnum) Configs.INSTANCE.colorbase.getOptionListValue()).getDyeColor().getColorComponents();
    wingIn.render(matrixStackIn, glintConsumer, i, OverlayTexture.DEFAULT_UV, f_[0], f_[1], f_[2], 1.0F);

    for (int count = 0; count < 8; count++) {
      if (Configs.INSTANCE.nameList[count].getOptionListValue() == Pattern.none)
        break;
      Identifier texture = new Identifier(OrnamentClient.MOD_ID, "entity/elytra_banner/"
          + Configs.INSTANCE.nameList[count].getOptionListValue().getStringValue());
      // System.out.println(texture.getPath());
      SpriteIdentifier spriteIdentifier = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE,
          texture);
      float[] f1 = ((ColorEnum) Configs.INSTANCE.colorList[count].getOptionListValue()).getDyeColor()
          .getColorComponents();
      if (spriteIdentifier.getSprite().getId() != MissingSprite.getMissingSpriteId())
        wingIn.render(matrixStackIn,
            spriteIdentifier.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntityTranslucent), i,
            OverlayTexture.DEFAULT_UV, f1[0], f1[1], f1[2], 1.0F);
    }
  }
}
