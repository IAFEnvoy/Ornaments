package iafenvoy.ornaments.renderer;

import iafenvoy.ornaments.config.ToolInfo;
import iafenvoy.ornaments.utils.ClientUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class BeltToolRenderer extends HeldItemFeatureRenderer<ClientPlayerEntity, PlayerEntityModel<ClientPlayerEntity>> {

    public BeltToolRenderer(FeatureRendererContext<ClientPlayerEntity, PlayerEntityModel<ClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider provider, int i, ClientPlayerEntity entity, float f, float g, float h, float j, float k, float l) {
        if (entity.getName().asString().equals(ClientUtil.getRenderPlayer())) {
            if (ToolInfo.leftBeltTool.shouldRender())
                renderItem(ToolInfo.leftBeltTool, matrices, provider, i, entity, true);
            if (ToolInfo.rightBeltTool.shouldRender())
                renderItem(ToolInfo.rightBeltTool, matrices, provider, i, entity, false);
        }
    }

    private void renderItem(ToolInfo info, MatrixStack matrices, VertexConsumerProvider provider, int i, AbstractClientPlayerEntity entity, boolean left) {
        float offsetx = info.getOffsetX();
        float offsety = info.getOffsetY();
        float offsetz = info.getOffsetZ();
        matrices.push();
        ModelPart modelPart = this.getContextModel().body;
        modelPart.rotate(matrices);
        double switchBeltSide = 0.29D;
        ItemStack item = ClientUtil.GetItemFromName(info.getItem(), info.isEnchanted());
        matrices.translate(offsetz + switchBeltSide * (left ? 1 : -1), offsety + 0.5D, offsetx + 0.05D);
        if (item.getItem() instanceof FlintAndSteelItem)
            matrices.translate(offsetz + 0.01F, offsety, offsetx - 0.1F);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(info.getRotateX()));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(info.getRotateY() - 90.0F));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(info.getRotateZ()));
        matrices.scale(info.getSize(), info.getSize(), info.getSize());
        if (item.getItem() instanceof ShearsItem || item.getItem() instanceof FlintAndSteelItem) {
            matrices.scale(0.65F, 0.65F, 0.65F);
            if (!entity.hasStackEquipped(EquipmentSlot.CHEST))
                matrices.translate(offsetz, offsety, offsetx + 0.015F);
        }
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, item, ModelTransformation.Mode.GROUND, false, matrices, provider, i);
        matrices.pop();
    }
}