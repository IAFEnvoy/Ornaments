package ornaments.Items.SideItems;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import ornaments.Commands.Commands;

public class SideItemModel<T extends LivingEntity> extends AnimalModel<T> {
  public final ModelPart item1,item2;

  public SideItemModel() {
    textureWidth = 16;
    textureHeight = 8;

    item1 = new ModelPart(this);
    item1.setPivot(0.0F, 0.0F, 0.0F);
    setRotationAngle(item1, 0.0F, 0.0F, 0.0F);
    item1.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 0.1F, 8.0F, 8.0F);
    
    item2 = new ModelPart(this);
    item2.setPivot(0.0F, 0.0F, 0.0F);
    setRotationAngle(item2, 0.0F, 0.0F, 0.0F);
    item2.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 0.1F, 8.0F, 8.0F);
  }

  public void setRotationAngle(ModelPart bone, float x, float y, float z) {
    bone.pitch = x;
    bone.yaw = y;
    bone.roll = z;
  }

  @Override
  protected Iterable<ModelPart> getBodyParts() {
    return ImmutableList.of(this.item1,this.item2);
  }

  @Override
  protected Iterable<ModelPart> getHeadParts() {
    return ImmutableList.of();
  }

  @Override
  public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw,
      float headPitch) {
    this.item1.pivotX = 4.1F;
    this.item1.pivotY = 8F;
    this.item1.pivotZ = -5.2F;
    this.item2.pivotX = -4.1F;
    this.item2.pivotY = 8F;
    this.item2.pivotZ = -5.2F;
  }
}
