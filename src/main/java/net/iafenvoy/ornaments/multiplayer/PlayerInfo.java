package net.iafenvoy.ornaments.multiplayer;

import java.net.URL;

import com.google.gson.JsonObject;

import fi.dy.masa.malilib.util.Color4f;
import net.iafenvoy.ornaments.Config.Configs;
import net.iafenvoy.ornaments.Items.Cape.BannerInfo;
import net.iafenvoy.ornaments.Items.Cape.ImageSize;
import net.iafenvoy.ornaments.Items.Cape.Pattern;
import net.iafenvoy.ornaments.Items.Wings.WingType;
import net.iafenvoy.ornaments.Util.ParseColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

public class PlayerInfo {
    // cape
    public boolean showCape;
    public ImageSize imageSize;
    public boolean useImage;
    public Identifier imagePath;
    public String imageName;
    public BannerInfo banner;
    // wing
    public boolean showWings;
    public boolean overwriteElytra;
    public WingType wingType;
    public Color4f lwingcolorl1, lwingcolorl2, rwingcolorl1, rwingcolorl2;

    public PlayerInfo(JsonObject jsons) throws Exception {
        // cape
        JsonObject cape = jsons.get("Cape").getAsJsonObject();
        showCape = cape.get("config.ornaments.show_cape").getAsBoolean();
        imageSize = ImageSize.fromStringStatic(cape.get("config.ornaments.showoption").getAsString());
        useImage = cape.get("config.ornaments.useimage").getAsBoolean();
        imageName = cape.get("config.ornaments.imagename").getAsString();
        if (useImage) {
            try {
                URL url = new URL(
                        String.format(Configs.General.CDN.getStringValue(), "Image", imageName+".png"));
                NativeImage image = NativeImage.read(url.openStream());
                if (image != null) {
                    NativeImageBackedTexture nIBT = new NativeImageBackedTexture(image);
                    imagePath = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture(imageName,
                            nIBT);
                } else
                    imagePath = new Identifier("minecraft", "textures/entity/banner/base.png");
            } catch (Exception e) {
                imagePath = new Identifier("minecraft", "textures/entity/banner/base.png");
                throw e;
            }
        }
        Pattern[] patterns = new Pattern[10];
        for (int i = 1; i <= 8; i++)
            patterns[i] = new Pattern(cape.get("config.ornaments.name" + String.valueOf(i)).getAsString(),
                    ParseColor.parse(cape.get("config.ornaments.color" + String.valueOf(i)).getAsString()));
        banner = new BannerInfo(ParseColor.parse(cape.get("config.ornaments.colorbase").getAsString()), patterns[1],
                patterns[2], patterns[3], patterns[4], patterns[5], patterns[6], patterns[7], patterns[8]);
        // wing
        JsonObject wing = jsons.get("Wings").getAsJsonObject();
        showWings = wing.get("config.ornaments.show_wing").getAsBoolean();
        overwriteElytra = wing.get("config.ornaments.overwrite_elytra").getAsBoolean();
        wingType = WingType.fromStringStatic(wing.get("config.ornaments.wingtype").getAsString());
        lwingcolorl1 = ParseColor.parse(wing.get("config.ornaments.lwingcolorl1").getAsString());
        lwingcolorl2 = ParseColor.parse(wing.get("config.ornaments.lwingcolorl2").getAsString());
        rwingcolorl1 = ParseColor.parse(wing.get("config.ornaments.rwingcolorl1").getAsString());
        rwingcolorl2 = ParseColor.parse(wing.get("config.ornaments.rwingcolorl2").getAsString());
    }
}
