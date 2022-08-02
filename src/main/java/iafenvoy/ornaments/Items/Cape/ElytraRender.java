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
import net.minecraft.client.render.entity.PlayerModelPart;
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
  private static final Identifier SKIN = new Identifier("textures/entity/elytra.png");
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

  public void renderSplit(boolean showCape, MatrixStack matrixStack,
      VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k,
      float l, ItemStack elytra) {
    matrixStack.push();
    matrixStack.translate(0.0D, 0.0D, 0.125D);
    if (showCape) {
      renderSplitBanner(matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l, elytra, leftWing);
      renderSplitBanner(matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l, elytra, rightWing);
    } else {
      renderOrigin(matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l);
    }
    matrixStack.pop();
  }

  public void renderOrigin(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i,
      T livingEntity, float f, float g, float h, float j, float k, float l) {
    ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
    if (itemStack.getItem() == Items.ELYTRA) {
      Identifier identifier4;
      if (livingEntity instanceof AbstractClientPlayerEntity) {
        AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity) livingEntity;
        if (abstractClientPlayerEntity.canRenderElytraTexture()
            && abstractClientPlayerEntity.getElytraTexture() != null)
          identifier4 = abstractClientPlayerEntity.getElytraTexture();
        else if (abstractClientPlayerEntity.canRenderCapeTexture()
            && abstractClientPlayerEntity.getCapeTexture() != null
            && abstractClientPlayerEntity.isPartVisible(PlayerModelPart.CAPE))
          identifier4 = abstractClientPlayerEntity.getCapeTexture();
        else
          identifier4 = SKIN;
      } else
        identifier4 = SKIN;

      matrixStack.push();
      matrixStack.translate(0.0D, 0.0D, 0.125D);
      VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
          RenderLayer.getArmorCutoutNoCull(identifier4), false, itemStack.hasGlint());
      this.getContextModel().copyStateTo(this.leftWing);
      this.leftWing.setAngles(livingEntity, f, g, j, k, l);
      this.leftWing.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
      this.getContextModel().copyStateTo(this.rightWing);
      this.rightWing.setAngles(livingEntity, f, g, j, k, l);
      this.rightWing.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
      matrixStack.pop();
    }
  }

  public void renderSplitBanner(MatrixStack matrixStackIn,
      VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k,
      float l, ItemStack elytra, ElytraWingModel<T> wingIn) {
    this.getContextModel().copyStateTo(wingIn);
    wingIn.setAngles(livingEntity, f, g, j, k, l);
    VertexConsumer glintConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider,
        RenderLayer.getEntityTranslucent(SKIN), false, elytra.hasGlint());

    float[] f_ = ((ColorEnum) Configs.INSTANCE.colorbase.getOptionListValue()).getDyeColor().getColorComponents();
    wingIn.render(matrixStackIn, glintConsumer, i, OverlayTexture.DEFAULT_UV, f_[0], f_[1], f_[2], 1.0F);

    for (int count = 0; count < 8; count++) {
      if (Configs.INSTANCE.nameList[count].getOptionListValue() == Pattern.BASE)
        break;
      Identifier texture = new Identifier(OrnamentClient.MOD_ID, "entity/elytra_banner/"
          + Configs.INSTANCE.nameList[count].getOptionListValue().getStringValue());
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
