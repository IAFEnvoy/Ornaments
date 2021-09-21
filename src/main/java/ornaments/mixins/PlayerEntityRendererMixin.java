package ornaments.mixins;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import ornaments.Items.BackItems.BackItemFeatureRenderer;
import ornaments.Items.MagicArray.MagicsFeatureRenderer;
import ornaments.Items.wings.WingsFeatureRenderer;
import ornaments.OFcape.CapeRender;
import ornaments.OFcape.ElytraRender;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
    extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
  public PlayerEntityRendererMixin(EntityRenderDispatcher dispatcher,
      PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
    super(dispatcher, model, shadowRadius);
  }

  @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "net/minecraft/client/render/entity/PlayerEntityRenderer.addFeature(Lnet/minecraft/client/render/entity/feature/FeatureRenderer;)Z", ordinal = 6))
  public void postConstructor(CallbackInfo callbackInfo) {
    this.addFeature(new CapeRender(this));
    this.addFeature(new ElytraRender<>(this));
    this.addFeature(new WingsFeatureRenderer<>(this));
    // this.addFeature(new MagicsFeatureRenderer<>(this));
    // this.addFeature(new BackItemFeatureRenderer<>(this));
    this.features.removeIf(renderer -> renderer instanceof ElytraFeatureRenderer);
  }
}
