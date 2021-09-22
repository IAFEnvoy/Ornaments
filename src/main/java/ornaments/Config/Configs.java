package ornaments.Config;

import java.io.File;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.JsonUtils;
import net.minecraft.text.TranslatableText;

public class Configs implements IConfigHandler {
  private static final String FILE_PATH = "./config/ornaments.json";
  private static final File CONFIG_DIR = new File("./config");

  public static class General {
    public static final ConfigHotkey MENU_OPEN_KEY = new ConfigHotkey(new TranslatableText("config.ornaments.open_menu_key").getString(), "F7", KeybindSettings.DEFAULT, "111");
    public static final ConfigBoolean SHOW_WITH_ELYTRA = new ConfigBoolean(new TranslatableText("config.ornaments.show_with_elytra").getString(), false, "111");
    public static void Init() {
      Category.GENERAL.add(MENU_OPEN_KEY);
      Category.GENERAL.add(SHOW_WITH_ELYTRA);
    }
  }
  public static class Details{
    public static final ConfigString CapeUser = new ConfigString(new TranslatableText("config.ornaments.capeuser").getString(), "", "222");
    public static final ConfigString wingType = new ConfigString(new TranslatableText("config.ornaments.wingtype").getString(), "", "222");
    public static final ConfigColor lwingcolorl1 = new ConfigColor(new TranslatableText("config.ornaments.lwingcolorl1").getString(), "0x30FFFFFF", "111");
    public static final ConfigColor lwingcolorl2 = new ConfigColor(new TranslatableText("config.ornaments.lwingcolorl2").getString(), "0x30FFFFFF", "111");
    public static final ConfigColor rwingcolorl1 = new ConfigColor(new TranslatableText("config.ornaments.rwingcolorl1").getString(), "0x30FFFFFF", "111");
    public static final ConfigColor rwingcolorl2 = new ConfigColor(new TranslatableText("config.ornaments.rwingcolorl2").getString(), "0x30FFFFFF", "111");
    // public static final ConfigOptionList OPTION_LIST;
    public static void Init() {
      Category.DETAILS.add(CapeUser);
      Category.DETAILS.add(wingType);
      Category.DETAILS.add(lwingcolorl1);
      Category.DETAILS.add(lwingcolorl2);
      Category.DETAILS.add(rwingcolorl1);
      Category.DETAILS.add(rwingcolorl2);
    }
  }

  public static void Init() {
    General.Init();
    Details.Init();
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
