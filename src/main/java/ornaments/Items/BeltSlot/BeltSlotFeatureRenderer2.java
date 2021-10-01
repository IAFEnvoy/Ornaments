package ornaments.Items.BeltSlot;

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
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.math.Vec3f;
import ornaments.Config.Configs;

@Environment(EnvType.CLIENT)
public class BeltSlotFeatureRenderer2 extends HeldItemFeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public BeltSlotFeatureRenderer2(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity livingEntity, float f, float g, float h, float j, float k, float l) {
        if (livingEntity instanceof AbstractClientPlayerEntity) {
            matrixStack.push();
            ModelPart modelPart = this.getContextModel().body;
            modelPart.rotate(matrixStack);
            double switchBeltSide = -0.29D;
            Item item=BeltItems.GetItemFromName(Configs.BeltSlot.beltslotType2.getStringValue());
            matrixStack.translate(switchBeltSide, 0.5D, 0.05D);
            if (item instanceof FlintAndSteelItem) {
                matrixStack.translate(0.01F, 0.0F, -0.1F);
            }
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
            matrixStack.scale(2.0F, 2.0F, 2.0F);
            if (item instanceof ShearsItem || item instanceof FlintAndSteelItem) {
                matrixStack.scale(0.65F, 0.65F, 0.65F);
                if (!livingEntity.hasStackEquipped(EquipmentSlot.CHEST)) {
                    matrixStack.translate(0.0F, 0.0F, 0.015F);
                }
            }
            MinecraftClient.getInstance().getHeldItemRenderer().renderItem(livingEntity, new ItemStack(item), ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i);
            matrixStack.pop();
        }
    }

}