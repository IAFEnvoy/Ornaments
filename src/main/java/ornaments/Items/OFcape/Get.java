package ornaments.Items.OFcape;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class Get {

    public static HashMap<String, Identifier> capes = new HashMap<String, Identifier>();

    public static Identifier fromPlayer(PlayerEntity player) {
        return capes.get(player.getUuidAsString());
    }

    public static void GetCape(PlayerEntity player, String playername) {
        Thread thread = new Thread() {
            public void run() {
                setCapeFromURL(player, String.format("http://s.optifine.net/capes/%s.png", playername));
            }
        };
        thread.start();
    }

    public static void SetCape(PlayerEntity player, String capepath) {
        try {
            InputStream texture = new FileInputStream(capepath);
            NativeImage cape = null;
            try {
                cape = NativeImage.read(texture);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (cape != null) {
                NativeImageBackedTexture nIBT = new NativeImageBackedTexture(parseCape(cape, false));
                Identifier capeTexture = MinecraftClient.getInstance().getTextureManager()
                        .registerDynamicTexture(player.getUuidAsString().replace("-", ""), nIBT);
                capes.put(player.getUuidAsString(), capeTexture);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void HideCape(PlayerEntity player) {
        capes.put(player.getUuidAsString(), null);
    }

    public static boolean setCapeFromURL(PlayerEntity player, String capeStringURL) {
        try {
            URL capeURL = new URL(capeStringURL);
            setCape(player.getUuidAsString(), capeURL.openStream());
            return true;
        } catch (IOException e) {
            capes.put(player.getUuidAsString(), null);
            return false;
        }
    }

    public static void setCape(String uuid, InputStream image) {
        NativeImage cape = null;
        try {
            cape = NativeImage.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cape != null) {
            NativeImageBackedTexture nIBT = new NativeImageBackedTexture(parseCape(cape, true));
            Identifier capeTexture = MinecraftClient.getInstance().getTextureManager()
                    .registerDynamicTexture(uuid.replace("-", ""), nIBT);
            capes.put(uuid, capeTexture);
        }
    }

    public static NativeImage parseCape(NativeImage image, Boolean of) {
        if (of) {
            int imageWidth = 64;
            int imageHeight = 32;
            int imageSrcWidth = image.getWidth();
            int srcHeight = image.getHeight();
            // System.out.println(String.valueOf(imageSrcWidth) + " " + String.valueOf(srcHeight));

            for (int imageSrcHeight = image.getHeight(); imageWidth < imageSrcWidth
                    || imageHeight < imageSrcHeight; imageHeight *= 2) {
                imageWidth *= 2;
            }
            // System.out.println(String.valueOf(imageWidth) + " " + String.valueOf(imageHeight));

            NativeImage imgNew = new NativeImage(imageWidth, imageHeight, true);
            for (int x = 0; x < imageSrcWidth; x++) {
                for (int y = 0; y < srcHeight; y++) {
                    imgNew.setPixelColor(x, y, image.getPixelColor(x, y));
                }
            }
            image.close();
            return imgNew;
        }
        // System.out.println(String.valueOf(image.getWidth()) + " " + String.valueOf(image.getHeight()));
        return image;
    }
}
