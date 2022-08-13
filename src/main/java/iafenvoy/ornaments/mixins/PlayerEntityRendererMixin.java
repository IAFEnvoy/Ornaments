package iafenvoy.ornaments.mixins;

import iafenvoy.ornaments.renderer.BackToolRenderer;
import iafenvoy.ornaments.renderer.BeltToolRenderer;
import iafenvoy.ornaments.renderer.ShoulderPlayerRenderer;
import iafenvoy.ornaments.renderer.cape.CapeRenderer;
import iafenvoy.ornaments.renderer.cape.ElytraRenderer;
import iafenvoy.ornaments.renderer.wings.WingRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<ClientPlayerEntity, PlayerEntityModel<ClientPlayerEntity>> {
    public PlayerEntityRendererMixin(EntityRenderDispatcher dispatcher, PlayerEntityModel<ClientPlayerEntity> model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V", at = @At(value = "RETURN"))
    public void postConstructor(CallbackInfo callbackInfo) {
        this.features.add(new CapeRenderer(this));
        this.features.add(new ElytraRenderer(this));
        this.features.add(new WingRenderer(this));
        this.features.add(new BackToolRenderer(this));
        this.features.add(new BeltToolRenderer(this));
        this.features.add(new ShoulderPlayerRenderer(this));

        this.features.removeIf(renderer -> renderer instanceof ElytraFeatureRenderer);
    }
}
