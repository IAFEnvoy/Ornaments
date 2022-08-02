package iafenvoy.ornaments.Registry;

import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.Items.Cape.Enum.Pattern;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

public class Textures {
  public static void register() {
    ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
      for (Pattern pattern : Pattern.values()) {
        Identifier textureIdentifier = new Identifier(OrnamentClient.MOD_ID,
            "entity/elytra_banner/" + pattern.getStringValue());
        registry.register(textureIdentifier);
      }
    });
    ClientSpriteRegistryCallback.event(TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE)
        .register((atlasTexture, registry) -> {
          for (Pattern pattern : Pattern.values()) {
            if (pattern.shouldReg()) {
              Identifier textureIdentifier = new Identifier("entity/banner/" + pattern.getStringValue());
              registry.register(textureIdentifier);
            }
          }
        });
  }
}
