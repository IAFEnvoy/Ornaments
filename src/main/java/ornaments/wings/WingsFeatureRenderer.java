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
import ornaments.Data.GlobalData;
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
    if (entity instanceof PlayerEntity) {
      if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "hide").equals(String.valueOf("true")) && !CapeRender.showcape)
        return;
      if (!GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "name").equals(((PlayerEntity)entity).getName().asString()))
        return;
      if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "wing") != "") {
        String wingType = "";
        if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "wing").equals("Feathered")) {
          wingModel = new FeatheredWingModel<>();
          wingType = "feathered";
        } else if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "wing").equals("Dragon")) {
          wingModel = new LeatherWingModel<>();
          wingType = "dragon";
        } else if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "wing").equals("Light")) {
          wingModel = new LightWingsModel<>();
          wingType = "light";
        } else if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "wing").equals("Discords")) {
          wingModel = new DiscordsWingsModel<>();
          wingType = "discords";
        } else if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "wing").equals("Zanzas")) {
          wingModel = new ZanzasWingsModel<>();
          wingType = "zanzas";
        } else if (GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "wing").equals("Tech")) {
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
        this.renderWings(matrices, vertexConsumers, layer2, light, 
          Float.parseFloat(GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "r2")),
          Float.parseFloat(GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "g2")), 
          Float.parseFloat(GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "b2")));
        this.renderWings(matrices, vertexConsumers, layer1, light, 
          Float.parseFloat(GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "r1")),
          Float.parseFloat(GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "g1")), 
          Float.parseFloat(GlobalData.getKey(((PlayerEntity)entity).getName().asString(), "b1")));
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
