package ornaments.Config;

import java.io.File;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.JsonUtils;
import net.minecraft.text.TranslatableText;

public class Configs implements IConfigHandler {
  private static final String FILE_PATH = "./config/ornaments.json";
  private static final File CONFIG_DIR = new File("./config");

  public static class General {
    public static final ConfigHotkey MENU_OPEN_KEY = new ConfigHotkey(
        new TranslatableText("config.ornaments.open_menu_key").getString(), "F7", KeybindSettings.DEFAULT,
        new TranslatableText("config.ornaments.open_menu_key.help").getString());
    public static final ConfigString User = new ConfigString(new TranslatableText("config.ornaments.user").getString(),
        "", new TranslatableText("config.ornaments.user.help").getString());

    public static void Init() {
      Category.GENERAL.add(MENU_OPEN_KEY);
      Category.GENERAL.add(User);
    }
  }

  public static class Cape {
    public static final ConfigBoolean SHOW_CAPE = new ConfigBoolean(
        new TranslatableText("config.ornaments.show_cape").getString(), false,
        new TranslatableText("config.ornaments.show_cape.help").getString());
    public static final ConfigBoolean Render_With_Elytra = new ConfigBoolean(
        new TranslatableText("config.ornaments.render_with_elytra").getString(), false,
        new TranslatableText("config.ornaments.show_cape.help").getString());
    public static final ConfigString CapeUser = new ConfigString(
        new TranslatableText("config.ornaments.capeuser").getString(), "",
        new TranslatableText("config.ornaments.capeuser.help").getString());

    public static void Init() {
      Category.Cape.add(SHOW_CAPE);
      Category.Cape.add(Render_With_Elytra);
      Category.Cape.add(CapeUser);
    }
  }

  public static class Wings {
    public static final ConfigBoolean SHOW_WING = new ConfigBoolean(
        new TranslatableText("config.ornaments.show_wing").getString(), false,
        new TranslatableText("config.ornaments.show_wing.help").getString());
    public static final ConfigBoolean Overwrite_Elytra = new ConfigBoolean(
        new TranslatableText("config.ornaments.overwrite_elytra").getString(), false,
        new TranslatableText("config.ornaments.overwrite_elytra.help").getString());
    public static final ConfigString wingType = new ConfigString(
        new TranslatableText("config.ornaments.wingtype").getString(), "",
        new TranslatableText("config.ornaments.wingtype.help").getString());
    public static final ConfigColor lwingcolorl1 = new ConfigColor(
        new TranslatableText("config.ornaments.lwingcolorl1").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.lwingcolorl1.help").getString());
    public static final ConfigColor lwingcolorl2 = new ConfigColor(
        new TranslatableText("config.ornaments.lwingcolorl2").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.lwingcolorl2.help").getString());
    public static final ConfigColor rwingcolorl1 = new ConfigColor(
        new TranslatableText("config.ornaments.rwingcolorl1").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.rwingcolorl1.help").getString());
    public static final ConfigColor rwingcolorl2 = new ConfigColor(
        new TranslatableText("config.ornaments.rwingcolorl2").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.rwingcolorl2.help").getString());

    public static void Init() {
      Category.Wings.add(SHOW_WING);
      Category.Wings.add(Overwrite_Elytra);
      Category.Wings.add(wingType);
      Category.Wings.add(lwingcolorl1);
      Category.Wings.add(lwingcolorl2);
      Category.Wings.add(rwingcolorl1);
      Category.Wings.add(rwingcolorl2);
    }
  }

  public static class BackTools {
    public static final ConfigBoolean show_back_tool = new ConfigBoolean(
        new TranslatableText("config.ornaments.show_back_tool").getString(), false,
        new TranslatableText("config.ornaments.show_back_tool.help").getString());
        public static final ConfigString backToolType = new ConfigString(
            new TranslatableText("config.ornaments.backtooltype").getString(), "",
            new TranslatableText("config.ornaments.backtooltype.help").getString());
    public static final ConfigDouble rotateAngle = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangle").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangle.help").getString());

    public static void Init() {
      Category.BackTools.add(show_back_tool);
      Category.BackTools.add(backToolType);
      Category.BackTools.add(rotateAngle);
    }
  }

  public static void Init() {
    General.Init();
    Cape.Init();
    Wings.Init();
    BackTools.Init();
  }

  @Override
  public void load() {
    loadFile();
  }

  public static void loadFile() {
    File settingFile = new File(FILE_PATH);
    if (settingFile.isFile() && settingFile.exists()) {
      JsonElement jsonElement = JsonUtils.parseJsonFile(settingFile);
      if (jsonElement instanceof JsonObject) {

        for (Category category : Category.values())
          ConfigUtils.readConfigBase((JsonObject) jsonElement, category.name(), category.getConfigs());
      }
    }
  }

  @Override
  public void save() {
    if ((CONFIG_DIR.exists() && CONFIG_DIR.isDirectory()) || CONFIG_DIR.mkdirs()) {
      JsonObject configRoot = new JsonObject();

      for (Category category : Category.values())
        ConfigUtils.writeConfigBase(configRoot, category.name(), category.getConfigs());

      JsonUtils.writeJsonToFile(configRoot, new File(FILE_PATH));
    }
  }
}
