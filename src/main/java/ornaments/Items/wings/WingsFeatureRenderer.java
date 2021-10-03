package ornaments.Items.Wings;

import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import ornaments.Client.OrnamentClient;
import ornaments.Config.Configs;
import ornaments.Items.Wings.models.FeatheredWingModel;
import ornaments.Items.Wings.models.LeatherWingModel;
import ornaments.Items.Wings.models.LightWingsModel;
import ornaments.Items.Wings.models.TechWingsModel;
import ornaments.Items.Wings.models.WingEntityModel;
import ornaments.Items.Wings.models.ZanzasWingsModel;
import ornaments.Util.RenderInfo;
import ornaments.Util.RenderInfo.RenderThings;

public class WingsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private WingEntityModel<T> lwingModel = new WingEntityModel<>();
  private WingEntityModel<T> rwingModel = new WingEntityModel<>();

  public WingsFeatureRenderer(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
      float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (entity instanceof PlayerEntity) {
      if (((PlayerEntity) entity).getName().asString().equals(Configs.General.User.getStringValue())) {
        if (!RenderInfo.ifRender(RenderThings.WINGS, (PlayerEntity)entity))
          return;
        String wingType = Configs.Wings.wingtype.getStringValue();
        if (wingType.equals("feathered")) {
          lwingModel = new FeatheredWingModel<>(true);
          rwingModel = new FeatheredWingModel<>(false);
        } else if (wingType.equals("dragon")) {
          lwingModel = new LeatherWingModel<>(true);
          rwingModel = new LeatherWingModel<>(false);
        } else if (wingType.equals("light")) {
          lwingModel = new LightWingsModel<>(true);
          rwingModel = new LightWingsModel<>(false);
        } else if (wingType.equals("zanzas")) {
          lwingModel = new ZanzasWingsModel<>(true);
          rwingModel = new ZanzasWingsModel<>(false);
        } else if (wingType.equals("tech")) {
          lwingModel = new TechWingsModel<>(true);
          rwingModel = new TechWingsModel<>(false);
        } else
          return;

        Identifier layer1 = new Identifier(OrnamentClient.MOD_ID, "textures/wing/" + wingType + "_wings.png");
        Identifier layer2 = new Identifier(OrnamentClient.MOD_ID, "textures/wing/" + wingType + "_wings_2.png");

        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(this.lwingModel);
        this.lwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.renderWings(matrices, vertexConsumers, layer2, light, Configs.Wings.lwingcolorl2.getColor(), true);
        this.renderWings(matrices, vertexConsumers, layer1, light, Configs.Wings.lwingcolorl1.getColor(), true);
        matrices.pop();

        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(this.rwingModel);
        this.rwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.renderWings(matrices, vertexConsumers, layer2, light, Configs.Wings.rwingcolorl2.getColor(), false);
        this.renderWings(matrices, vertexConsumers, layer1, light, Configs.Wings.rwingcolorl1.getColor(), false);
        matrices.pop();
      }
    }
  }

  public void renderWings(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName, int light,
      Color4f color, boolean left) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    if (left)
      this.lwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b,
          color.a);
    else
      this.rwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b,
          color.a);
  }
}
