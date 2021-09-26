package ornaments.Items.BackItems;

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
import ornaments.Config.Configs;

public class BackItemFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private BackItemModel<T> backItemModel = new BackItemModel<>();

  public BackItemFeatureRenderer(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
      float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (entity instanceof PlayerEntity) {
      if (Configs.BackTools.show_back_tool.getBooleanValue()
          && ((PlayerEntity) entity).getName().asString().equals(Configs.General.User.getStringValue())) {
        Identifier layer = new Identifier(client.MOD_ID, "textures/backitem/diamond_sword.png");

        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(this.backItemModel);
        this.backItemModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.renderItems(matrices, vertexConsumers, layer, light, 0.5F, 0.5F, 0.5F);
        matrices.pop();
      }
    }
  }

  public void renderItems(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName, int light,
      float r, float g, float b) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    this.backItemModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
  }
}
