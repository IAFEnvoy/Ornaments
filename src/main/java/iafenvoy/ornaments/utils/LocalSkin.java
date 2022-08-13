package iafenvoy.ornaments.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class LocalSkin {
    public static final HashMap<String, LocalSkin> data = new HashMap<>();
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private final String name;
    private Identifier registryKey = null;

    public LocalSkin(String name) {
        this.name = name;
        data.put(this.name.toLowerCase(), this);
    }

    public String getSkinPath() {
        return "LocalSkin/" + this.name + ".png";
    }

    public void load() {
        try {
            NativeImage skin = NativeImage.read(Files.newInputStream(Paths.get("./" + this.getSkinPath())));
            NativeImageBackedTexture nIBT = new NativeImageBackedTexture(skin);
            this.registryKey = client.getTextureManager().registerDynamicTexture("ornaments-skin-" + name.toLowerCase(), nIBT);
        } catch (Exception e) {
            this.registryKey = null;
        }
    }

    public Identifier getIdentifier() {
        return registryKey;
    }
}
