package ornaments.Items.Masks;

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

public class MasksFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private MasksModel<T> maskModel = new MasksModel<>();

  public MasksFeatureRenderer(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
      float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (entity instanceof PlayerEntity) {
      matrices.push();
      matrices.translate(0.0D, 0.0D, 0.125D);
      this.getContextModel().copyStateTo(this.maskModel);
      this.maskModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
      this.renderMagic(matrices, vertexConsumers, new Identifier(OrnamentClient.MOD_ID, "textures/mask/1.png"), light,
          Configs.Magic.color1.getColor());
      matrices.pop();
    }
  }

  public void renderMagic(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName, int light,
      Color4f color) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    this.maskModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b,
        color.a);
  }
}
