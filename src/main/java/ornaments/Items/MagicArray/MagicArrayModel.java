package ornaments.Items.MagicArray;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import ornaments.Commands.Commands;

public class MagicArrayModel<T extends LivingEntity> extends AnimalModel<T> {
  public final ModelPart magic;
  public State state = State.IDLE;

  public MagicArrayModel() {
    textureWidth = 64;
    textureHeight = 64;

    magic = new ModelPart(this);
    // magic.setPivot(Commands.x, Commands.y, Commands.z);
    // setRotationAngle(magic, Commands.xr, Commands.yr, Commands.zr);
    magic.setPivot(0.0F, 0.0F, 0.0F);
    setRotationAngle(magic, 0.0F, 0.0F, 0.0F);
    magic.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 0.1F, 64.0F, 64.0F);
  }

  public void setRotationAngle(ModelPart bone, float x, float y, float z) {
    bone.pitch = x;
    bone.yaw = y;
    bone.roll = z;
  }

  @Override
  protected Iterable<ModelPart> getBodyParts() {
    return ImmutableList.of(this.magic);
  }

  @Override
  protected Iterable<ModelPart> getHeadParts() {
    return ImmutableList.of();
  }

  @Override
  public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw,
      float headPitch) {
    this.magic.pivotX = 32F;
    this.magic.pivotY = 23.5F;
    this.magic.pivotZ = -32F;
    this.magic.pitch = 0.0F;
    this.magic.roll = 1.57F;
    this.magic.yaw = 0.0F;
    if(entity.isSneaking()) 
      this.magic.pivotY = 21F;
  }

  public enum State {
    IDLE, CROUCHING, FLYING
  }
}
