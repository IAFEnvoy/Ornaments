package ornaments.Items.BackItems;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import ornaments.Config.Configs;

public class BackItemModel<T extends LivingEntity> extends AnimalModel<T> {
  public final ModelPart item;
  public State state = State.IDLE;

  public BackItemModel() {
    textureWidth = 32;
    textureHeight = 16;

    item = new ModelPart(this);
    item.setPivot(0.0F, 0.0F, 0.0F);
    setRotationAngle(item, 0.0F, 0.0F, 0.0F);
    item.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 1F, 16.0F, 16.0F);
  }

  public void setRotationAngle(ModelPart bone, float x, float y, float z) {
    bone.pitch = x;
    bone.yaw = y;
    bone.roll = z;
  }

  @Override
  protected Iterable<ModelPart> getBodyParts() {
    return ImmutableList.of(this.item);
  }

  @Override
  protected Iterable<ModelPart> getHeadParts() {
    return ImmutableList.of();
  }

  @Override
  public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw,
      float headPitch) {
    this.item.pivotX = -8F;
    this.item.pivotY = -1F;
    this.item.pivotZ = 1F;
    this.item.pitch = (float) (Configs.BackTools.rotateAngle.getDoubleValue()/180.0F*3.1415926F);
    this.item.roll = 0F;
    this.item.yaw = 1.57F;
  }

  public enum State {
    IDLE, CROUCHING, FLYING
  }
}