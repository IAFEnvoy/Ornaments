package ornaments.Items.BackItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;
import ornaments.Config.Configs;
import ornaments.Util.GetItems;
import ornaments.Util.RenderInfo;
import ornaments.Util.RenderInfo.RenderThings;

@Environment(EnvType.CLIENT)
public class BackToolFeatureRenderer1
    extends HeldItemFeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

  public BackToolFeatureRenderer1(
      FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext) {
    super(featureRendererContext);
  }

  @Override
  public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i,
      AbstractClientPlayerEntity livingEntity, float f, float g, float h, float j, float k, float l) {
    if (livingEntity instanceof AbstractClientPlayerEntity) {
      if (((PlayerEntity) livingEntity).getName().asString().equals(Configs.General.User.getStringValue())) {
        if (!RenderInfo.ifRender(RenderThings.BACKITEM, (PlayerEntity) livingEntity))
          return;
        matrixStack.push();
        ModelPart modelPart = this.getContextModel().body;
        modelPart.rotate(matrixStack);
        ItemStack backSloItem = GetItems.GetItemFromName(Configs.BackTools.backToolType1.getStringValue(),
            Configs.BackTools.enchanted1.getBooleanValue());
        matrixStack.translate(Configs.BackTools.xoffset1.getDoubleValue(), Configs.BackTools.yoffset1.getDoubleValue(),
            0.22D);
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack
            .multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion((float) Configs.BackTools.rotateAngle1.getDoubleValue()));
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(livingEntity, backSloItem,
            ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i);

        matrixStack.pop();
      }
    }
  }
}