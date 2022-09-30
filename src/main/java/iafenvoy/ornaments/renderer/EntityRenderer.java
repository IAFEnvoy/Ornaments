package iafenvoy.ornaments.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.utils.FakePlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

public class EntityRenderer {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static void render(MatrixStack matrices, float rotateX, float rotateY, boolean shouldRenderElytra, int size) {
        if (OrnamentClient.hasBobby) {
            DrawableHelper.drawCenteredText(matrices, client.textRenderer, new TranslatableText("compat.ornaments.bobby"), 300, 100, -1);
            return;
        }
        int i = 250, j = 100;
        FakePlayerEntity.DummyClientPlayerEntity player = FakePlayerEntity.DummyClientPlayerEntity.INSTANCE;
        player.loadSkin(null);
        player.equip(100 + EquipmentSlot.CHEST.getEntitySlotId(), new ItemStack(shouldRenderElytra ? Items.ELYTRA : Items.AIR));
        drawEntity(i + 51, j + 75, size, rotateX, rotateY, player);
    }

    private static void drawEntity(int x, int y, int size, float rotateX, float rotateY, PlayerEntity player) {
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float) x, (float) y, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrices = new MatrixStack();
        matrices.translate(0.0D, 0.0D, 1000.0D);
        matrices.scale((float) size, (float) size, (float) size);
        Quaternion quaternion = Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F);
        Quaternion quaternion2 = Vec3f.POSITIVE_X.getDegreesQuaternion(rotateY);
        quaternion.hamiltonProduct(quaternion2);
        matrices.multiply(quaternion);
        player.headYaw = player.prevHeadYaw = player.bodyYaw = player.yaw = rotateX + 180.0F;
        player.pitch = 0;
        player.capeX = player.prevCapeX = 0;
        player.capeY = player.prevCapeY = 0;
        player.capeZ = player.prevCapeZ = 0;
        player.setPos(0, 0, 0);
        EntityRenderDispatcher dispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        quaternion2.conjugate();
        dispatcher.setRotation(quaternion2);
        dispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

        ClientPlayerEntity entity = client.player;
        client.player = (ClientPlayerEntity) player;
        RenderSystem.runAsFancy(() -> dispatcher.render(player, 0, 0, 0, 0, 1, matrices, immediate, 15728880));
        client.player = entity;

        immediate.draw();
        dispatcher.setRenderShadows(true);
        RenderSystem.popMatrix();
    }
}
