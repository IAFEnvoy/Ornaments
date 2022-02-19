package net.iafenvoy.ornaments.Items.Cape;

import com.google.common.collect.ImmutableList;

import net.iafenvoy.ornaments.Config.Configs;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;

public class CapeModel<T extends LivingEntity> extends AnimalModel<T> {
  public final ModelPart cloak;

  public CapeModel() {
    textureWidth = 64;
    textureHeight = (Configs.Cape.showoption.getStringValue().equals("s8_7") ? 56 : 64);

    this.cloak = new ModelPart(this);
    this.cloak.setTextureSize(64, (Configs.Cape.showoption.getStringValue().equals("s8_7") ? 56 : 64));
    this.cloak.setTextureOffset(0, 0).addCuboid(-10F, -23.0F, -1.0F, 20F,
        (Configs.Cape.showoption.getStringValue().equals("s8_7") ? 35F : 40F), 1.0F, 0.0F);
  }

  @Override
  public void setAngles(T livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw,
      float headPitch) {
  }

  @Override
  protected Iterable<ModelPart> getHeadParts() {
    return ImmutableList.of();
  }

  @Override
  protected Iterable<ModelPart> getBodyParts() {
    return ImmutableList.of(cloak);
  }
}
