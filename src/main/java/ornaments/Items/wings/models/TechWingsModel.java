package ornaments.Items.wings.models;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.LivingEntity;

public class TechWingsModel<T extends LivingEntity> extends WingEntityModel<T> {
  private final ModelPart leftwing;
  private final ModelPart rightwing;

  public TechWingsModel(boolean left) {
    textureWidth = 128;
    textureHeight = 32;
    leftwing = new ModelPart(this);
    rightwing = new ModelPart(this);

    if (left) {
      leftwing.setPivot(-40F, 27F, 1F);
      setRotationAngle(leftwing, 0F, 1.57F, -0.75F);
      leftwing.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 1.0F, 32.0F, 64.0F, 0.0F, false);
      leftWing.addChild(leftwing);

    } else {
      rightwing.setPivot(40F, 27F, 1F);
      setRotationAngle(rightwing, 0F, -1.57F, 0.75F);
      rightwing.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 1.0F, 32.0F, 64.0F, 0.0F, true);
      rightWing.addChild(rightwing);
    }
  }

  public void setRotationAngle(ModelPart bone, float x, float y, float z) {
    bone.pitch = x;
    bone.yaw = y;
    bone.roll = z;
  }
}
