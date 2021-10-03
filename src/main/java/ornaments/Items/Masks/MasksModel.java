package ornaments.Items.Masks;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;

public class MasksModel<T extends LivingEntity> extends AnimalModel<LivingEntity>{
  private final ModelPart front;

  public MasksModel(){
    textureHeight=64;
    textureWidth=64;

    front=new ModelPart(this);
    front.setPivot(0.0F, 0.0F, 0.0F);
    setRotationAngle(front, 0.0F, 0.0F, 0.0F);
    front.setTextureOffset(0, 0).addCuboid(0.0F, 0.0F, 0.0F, 0.1F, 10.0F, 10.0F);
  }

  public void setRotationAngle(ModelPart bone, float x, float y, float z) {
    bone.pitch = x;
    bone.yaw = y;
    bone.roll = z;
  }

  @Override
  protected Iterable<ModelPart> getHeadParts() {
    return ImmutableList.of(front);
  }

  @Override
  public void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress,
      float headYaw, float headPitch) {
    // TODO Auto-generated method stub
    
  }
  @Override
  protected Iterable<ModelPart> getBodyParts() {
    return null;
  }
  
}
