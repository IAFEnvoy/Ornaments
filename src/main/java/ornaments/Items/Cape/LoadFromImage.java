package ornaments.Items.Cape;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class LoadFromImage {
  public static HashMap<String, Identifier> capes = new HashMap<String, Identifier>();
  public static Identifier fromName(String name) {
    return capes.get(name);
  }
}
