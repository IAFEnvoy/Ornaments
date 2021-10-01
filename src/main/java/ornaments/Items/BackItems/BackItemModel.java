package ornaments.Items.BackItems;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import ornaments.Config.Configs;

public class BackItemModel<T extends LivingEntity> extends AnimalModel<T> {
  public final ModelPart item;
  private float offsetx, offsety;

  public BackItemModel() {
    textureWidth = 32;
    textureHeight = 16;

    offsetx = 0;
    offsety = 6;

    item = new ModelPart(this);
    item.setPivot(0.0F, 0.0F, 0.0F);
    setRotationAngle(item, 0.0F, 0.0F, 0.0F);
    item.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 0.1F, 16.0F, 16.0F);
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
    this.item.pitch = (float) (Configs.BackTools.rotateAngle.getDoubleValue()) / 180.0F * 3.1415926F;
    this.item.roll = 0F;
    this.item.yaw = 1.57F;

    float angle = (float) (Configs.BackTools.rotateAngle.getDoubleValue() - 135.0F) / 180.0F * 3.1415926F;
    float r = (float) (8 * MathHelper.SQUARE_ROOT_OF_TWO);
    this.item.pivotX = offsetx + r * MathHelper.sin(angle);
    this.item.pivotY = offsety + r * MathHelper.cos(angle);

    if (entity.isSneaking()) {
      this.item.pivotZ = 3.6F-4*(float)(MathHelper.cos((float) Configs.BackTools.rotateAngle.getDoubleValue()));//7.5F~-0.5F

      setRotationAngle(item, (float) (Configs.BackTools.rotateAngle.getDoubleValue() + 90) / 180.0F * 3.1415926F, 1.1F,
          1.57F);
    }
  }
}