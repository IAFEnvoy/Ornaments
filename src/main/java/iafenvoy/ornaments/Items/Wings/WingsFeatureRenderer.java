package iafenvoy.ornaments.Items.Wings;

import fi.dy.masa.malilib.util.Color4f;
import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.Config.Configs;
import iafenvoy.ornaments.Items.Wings.models.FeatheredWingModel;
import iafenvoy.ornaments.Items.Wings.models.LeatherWingModel;
import iafenvoy.ornaments.Items.Wings.models.LightWingsModel;
import iafenvoy.ornaments.Items.Wings.models.TechWingsModel;
import iafenvoy.ornaments.Items.Wings.models.WingEntityModel;
import iafenvoy.ornaments.Items.Wings.models.ZanzasWingsModel;
import iafenvoy.ornaments.Util.PlayerUtil;
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

public class WingsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private WingEntityModel<T> lwingModel = new WingEntityModel<>();
  private WingEntityModel<T> rwingModel = new WingEntityModel<>();

  public WingsFeatureRenderer(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
      float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (entity.isSpectator() || entity.isInvisible() || !entity.isAlive())
      return;
    if (entity instanceof PlayerEntity) {
      if (((PlayerEntity) entity).getName().asString().equals(PlayerUtil.getRenderPlayer())) {
        if (Configs.INSTANCE.SHOW_WING.getBooleanValue())
          if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA
              || Configs.INSTANCE.Overwrite_Elytra.getBooleanValue()) {
            renderTexture((WingType) Configs.INSTANCE.wingtype.getOptionListValue(), Configs.INSTANCE.lwingcolorl1.getColor(),
                Configs.INSTANCE.lwingcolorl2.getColor(), Configs.INSTANCE.rwingcolorl1.getColor(),
                Configs.INSTANCE.rwingcolorl2.getColor(), matrices, vertexConsumers, light, entity, limbAngle,
                limbDistance, tickDelta, animationProgress, headYaw, headPitch);
          }
      }
    }
  }

  private void renderTexture(WingType wingType, Color4f lwingcolorl1, Color4f lwingcolorl2, Color4f rwingcolorl1,
      Color4f rwingcolorl2, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity,
      float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (wingType == WingType.Feather) {
      lwingModel = new FeatheredWingModel<>(true);
      rwingModel = new FeatheredWingModel<>(false);
    } else if (wingType == WingType.Leather) {
      lwingModel = new LeatherWingModel<>(true);
      rwingModel = new LeatherWingModel<>(false);
    } else if (wingType == WingType.Light) {
      lwingModel = new LightWingsModel<>(true);
      rwingModel = new LightWingsModel<>(false);
    } else if (wingType == WingType.Zanzas) {
      lwingModel = new ZanzasWingsModel<>(true);
      rwingModel = new ZanzasWingsModel<>(false);
    } else if (wingType == WingType.Tech) {
      lwingModel = new TechWingsModel<>(true);
      rwingModel = new TechWingsModel<>(false);
    } else
      return;

    Identifier layer1 = new Identifier(OrnamentClient.MOD_ID, "textures/wing/" + wingType.getStringValue() + "_wings.png");
    Identifier layer2 = new Identifier(OrnamentClient.MOD_ID,
        "textures/wing/" + wingType.getStringValue() + "_wings_2.png");

    matrices.push();
    matrices.translate(0.0D, 0.0D, 0.125D);
    this.getContextModel().copyStateTo(this.lwingModel);
    this.lwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.renderWings(matrices, vertexConsumers, layer2, light, lwingcolorl2, true);
    this.renderWings(matrices, vertexConsumers, layer1, light, lwingcolorl1, true);
    matrices.pop();

    matrices.push();
    matrices.translate(0.0D, 0.0D, 0.125D);
    this.getContextModel().copyStateTo(this.rwingModel);
    this.rwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.renderWings(matrices, vertexConsumers, layer2, light, rwingcolorl2, false);
    this.renderWings(matrices, vertexConsumers, layer1, light, rwingcolorl1, false);
    matrices.pop();
  }

  private void renderWings(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName,
      int light, Color4f color, boolean left) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    if (left)
      lwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b, color.a);
    else
      rwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b, color.a);
  }
}
