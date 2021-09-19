package ornaments.wings;

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
import ornaments.Client.client;
import ornaments.Client.config;
import ornaments.OFcape.CapeRender;
import ornaments.wings.models.DiscordsWingsModel;
import ornaments.wings.models.FeatheredWingModel;
import ornaments.wings.models.LeatherWingModel;
import ornaments.wings.models.LightWingsModel;
import ornaments.wings.models.TechWingsModel;
import ornaments.wings.models.WingEntityModel;
import ornaments.wings.models.ZanzasWingsModel;

public class WingsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private WingEntityModel<T> wingModel = new WingEntityModel<>();

  public WingsFeatureRenderer(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
      float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (config.getHide() && !CapeRender.showcape)
      return;
    if (entity instanceof PlayerEntity) {
      if (!config.playername.equals(((PlayerEntity)entity).getName().asString()))
        return;
      if (client.player.getWingType() != "") {
        String wingType = "";
        if (client.player.getWingType().equals("Feathered")) {
          wingModel = new FeatheredWingModel<>();
          wingType = "feathered";
        } else if (client.player.getWingType().equals("Dragon")) {
          wingModel = new LeatherWingModel<>();
          wingType = "dragon";
        } else if (client.player.getWingType().equals("Light")) {
          wingModel = new LightWingsModel<>();
          wingType = "light";
        } else if (client.player.getWingType().equals("Discords")) {
          wingModel = new DiscordsWingsModel<>();
          wingType = "discords";
        } else if (client.player.getWingType().equals("Zanzas")) {
          wingModel = new ZanzasWingsModel<>();
          wingType = "zanzas";
        } else if (client.player.getWingType().equals("Tech")) {
          wingModel = new TechWingsModel<>();
          wingType = "tech";
        } else
          return;

        Identifier layer1 = new Identifier(client.MOD_ID, "textures/entity/" + wingType + "_wings.png");
        Identifier layer2 = new Identifier(client.MOD_ID, "textures/entity/" + wingType + "_wings_2.png");

        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(this.wingModel);
        this.wingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.renderWings(matrices, vertexConsumers, layer2, light, client.player.getColor("r2"),
            client.player.getColor("g2"), client.player.getColor("b2"));
        this.renderWings(matrices, vertexConsumers, layer1, light, client.player.getColor("r1"),
            client.player.getColor("g1"), client.player.getColor("b1"));
        matrices.pop();
      }
    }
  }

  public void renderWings(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName, int light,
      float r, float g, float b) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    this.wingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
  }
}
