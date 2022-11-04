package iafenvoy.ornaments.renderer.cape;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class PhysicalRenderer {
    public static boolean use = false;

    public static void render(MatrixStack matrices, AbstractClientPlayerEntity entity, float tickDelta, float rotate) {
        if (!use) return;

        final double capeX = MathHelper.lerp(tickDelta, entity.prevCapeX, entity.capeX) - MathHelper.lerp(tickDelta, entity.prevX, entity.getX());
        final double capeY = MathHelper.lerp(tickDelta, entity.prevCapeY, entity.capeY) - MathHelper.lerp(tickDelta, entity.prevY, entity.getY());
        final double capeZ = MathHelper.lerp(tickDelta, entity.prevCapeZ, entity.capeZ) - MathHelper.lerp(tickDelta, entity.prevZ, entity.getZ());
        final float bodyYaw = entity.bodyYaw;
        final double bodyYawY = MathHelper.sin(bodyYaw * 0.017453292F);
        final double bodyYawX = -MathHelper.cos(bodyYaw * 0.017453292F);
        float q = (float) capeY * 10.0F;
        q = MathHelper.clamp(q, -6.0F, 32.0F);
        float r = (float) (capeX * bodyYawY + capeZ * bodyYawX) * 100.0F;
        r = MathHelper.clamp(r, 0.0F, 150.0F);
        float s = (float) (capeX * bodyYawX - capeZ * bodyYawY) * 100.0F;
        s = MathHelper.clamp(s, -20.0F, 20.0F);
        if (r < 0.0F) r = 0.0F;

        final float t = MathHelper.lerp(tickDelta, entity.prevStrideDistance, entity.strideDistance);
        q += MathHelper.sin(MathHelper.lerp(tickDelta, entity.prevHorizontalSpeed, entity.horizontalSpeed) * 6.0F) * 32.0F * t;
        if (entity.isInSneakingPose()) q += 25.0F;

        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotate - s / 2.0F));
    }
}
