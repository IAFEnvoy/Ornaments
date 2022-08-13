package iafenvoy.ornaments.mixins;

import iafenvoy.ornaments.gui.settings.SettingGui;
import iafenvoy.ornaments.utils.FakePlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity> {
    @Inject(method = "hasLabel*", at = @At("HEAD"), cancellable = true)
    private void removeLabel(T entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof FakePlayerEntity.DummyClientPlayerEntity && MinecraftClient.getInstance().currentScreen instanceof SettingGui)
            cir.setReturnValue(false);
    }
}
