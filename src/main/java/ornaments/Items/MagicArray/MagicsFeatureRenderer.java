package ornaments.Items.MagicArray;

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
import ornaments.Client.client;
import ornaments.Config.Configs;
import ornaments.Util.RenderInfo;
import ornaments.Util.RenderInfo.RenderThings;

public class MagicsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private MagicArrayModel<T> magicModel = new MagicArrayModel<>();

  public MagicsFeatureRenderer(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
      float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (entity instanceof PlayerEntity) {
      if (RenderInfo.ifRender(RenderThings.MAGIC, (PlayerEntity) entity)
          && ((PlayerEntity) entity).getName().asString().equals(Configs.General.User.getStringValue())) {
        matrices.push();
        matrices.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(this.magicModel);
        this.magicModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        if (!Configs.Magic.type4.getStringValue().isEmpty())
          this.renderMagic(matrices, vertexConsumers,
              new Identifier(client.MOD_ID, "textures/magic/" + Configs.Magic.type4.getStringValue() + ".png"), light,
              Configs.Magic.color4.getColor());
        if (!Configs.Magic.type3.getStringValue().isEmpty())
          this.renderMagic(matrices, vertexConsumers,
              new Identifier(client.MOD_ID, "textures/magic/" + Configs.Magic.type3.getStringValue() + ".png"), light,
              Configs.Magic.color3.getColor());
        if (!Configs.Magic.type2.getStringValue().isEmpty())
          this.renderMagic(matrices, vertexConsumers,
              new Identifier(client.MOD_ID, "textures/magic/" + Configs.Magic.type2.getStringValue() + ".png"), light,
              Configs.Magic.color2.getColor());
        if (!Configs.Magic.type1.getStringValue().isEmpty())
          this.renderMagic(matrices, vertexConsumers,
              new Identifier(client.MOD_ID, "textures/magic/" + Configs.Magic.type1.getStringValue() + ".png"), light,
              Configs.Magic.color1.getColor());
        matrices.pop();
      }
    }
  }

  public void renderMagic(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName, int light,
      Color4f color) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    this.magicModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b,
        color.a);
  }
}
