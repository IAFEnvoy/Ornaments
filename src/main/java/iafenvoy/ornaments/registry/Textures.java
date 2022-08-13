package iafenvoy.ornaments.registry;

import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.renderer.cape.type.Pattern;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;

public class Textures {
    public static void register() {
        ClientSpriteRegistryCallback.event(TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            for (Pattern pattern : Pattern.values())
                registry.register(new Identifier(OrnamentClient.MOD_ID, "entity/elytra_banner/" + pattern.getStringValue()));
        });
        ClientSpriteRegistryCallback.event(TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            for (Pattern pattern : Pattern.values())
                if (pattern.shouldReg())
                    registry.register(new Identifier("entity/banner/" + pattern.getStringValue()));
        });
    }
}
