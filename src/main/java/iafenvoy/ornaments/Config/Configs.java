package iafenvoy.ornaments.Config;

import java.io.File;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.util.JsonUtils;
import iafenvoy.ornaments.Config.ConfigGUI.Category;
import iafenvoy.ornaments.Config.NativeConfigType.NBoolean;
import iafenvoy.ornaments.Config.NativeConfigType.NColor;
import iafenvoy.ornaments.Config.NativeConfigType.NDouble;
import iafenvoy.ornaments.Config.NativeConfigType.NHotkey;
import iafenvoy.ornaments.Config.NativeConfigType.NOptionList;
import iafenvoy.ornaments.Config.NativeConfigType.NString;
import iafenvoy.ornaments.Items.Cape.Enum.ColorEnum;
import iafenvoy.ornaments.Items.Cape.Enum.ImageSize;
import iafenvoy.ornaments.Items.Cape.Enum.Pattern;
import iafenvoy.ornaments.Items.Wings.WingType;

public class Configs implements IConfigHandler {
  public static final Configs INSTANCE = new Configs();
  private static final String FILE_PATH = "./config/ornaments.json";
  private static final File CONFIG_DIR = new File("./config");

  // General
  public final ConfigHotkey MENU_OPEN_KEY = new NHotkey("config.ornaments.open_menu_key", "F7", Category.General);
  public final ConfigString User = new NString("config.ornaments.user", Category.General);

  // Cape
  public final ConfigBoolean SHOW_CAPE = new NBoolean("config.ornaments.show_cape", Category.Cape);
  public final ConfigBoolean UseImage = new NBoolean("config.ornaments.useimage", Category.Cape);
  public final ConfigString ImageName = new NString("config.ornaments.imagename", Category.Cape);
  public final ConfigOptionList showoption = new NOptionList("config.ornaments.showoption", ImageSize.S8_7,
      Category.Cape);
  public final ConfigOptionList colorbase = new NOptionList("config.ornaments.colorbase", ColorEnum.black,
      Category.Cape);
  public final ConfigOptionList nameList[] = new ConfigOptionList[8];
  public final ConfigOptionList colorList[] = new ConfigOptionList[8];

  // Wings
  public final ConfigBoolean SHOW_WING = new NBoolean("config.ornaments.show_wing", Category.Wings);
  public final ConfigBoolean Overwrite_Elytra = new NBoolean("config.ornaments.overwrite_elytra", Category.Wings);
  public final ConfigOptionList wingtype = new NOptionList("config.ornaments.wingtype", WingType.None,
      Category.Wings);
  public final ConfigColor lwingcolorl1 = new NColor("config.ornaments.lwingcolorl1", Category.Wings);
  public final ConfigColor lwingcolorl2 = new NColor("config.ornaments.lwingcolorl2", Category.Wings);
  public final ConfigColor rwingcolorl1 = new NColor("config.ornaments.rwingcolorl1", Category.Wings);
  public final ConfigColor rwingcolorl2 = new NColor("config.ornaments.rwingcolorl2", Category.Wings);

  // Back Tools
  public final ConfigBoolean show_back_tool = new NBoolean("config.ornaments.show_back_tool", Category.BackTools);

  public final ConfigString backToolType1 = new NString("config.ornaments.backtooltype1", Category.BackTools);
  public final ConfigBoolean enchanted1 = new NBoolean("config.ornaments.enchanted1", Category.BackTools);
  public final ConfigDouble rotateAngle1 = new NDouble("config.ornaments.rotateangle1", 0, 0, 360, true,
      Category.BackTools);
  public final ConfigDouble rotateAnglex1 = new NDouble("config.ornaments.rotateanglex1", 0, 0, 360, true,
      Category.BugFix);
  public final ConfigDouble rotateAngley1 = new NDouble("config.ornaments.rotateangley1", 0, 0, 360, true,
      Category.BugFix);
  public final ConfigDouble xoffset1 = new NDouble("config.ornaments.xoffset1", 0, -2, 2, true, Category.BackTools);
  public final ConfigDouble yoffset1 = new NDouble("config.ornaments.yoffset1", 0, -2, 2, true, Category.BackTools);
  public final ConfigDouble zoffset1 = new NDouble("config.ornaments.zoffset1", 0, -2, 2, true, Category.BugFix);
  public final ConfigDouble size1 = new NDouble("config.ornaments.size1", 2, 0, 5, true, Category.BugFix);

  public final ConfigString backToolType2 = new NString("config.ornaments.backtooltype2", Category.BackTools);
  public final ConfigBoolean enchanted2 = new NBoolean("config.ornaments.enchanted2", Category.BackTools);
  public final ConfigDouble rotateAngle2 = new NDouble("config.ornaments.rotateangle2", 0, 0, 360, true,
      Category.BackTools);
  public final ConfigDouble rotateAnglex2 = new NDouble("config.ornaments.rotateanglex2", 0, 0, 360, true,
      Category.BugFix);
  public final ConfigDouble rotateAngley2 = new NDouble("config.ornaments.rotateangley2", 0, 0, 360, true,
      Category.BugFix);
  public final ConfigDouble xoffset2 = new NDouble("config.ornaments.xoffset2", 0, -2, 2, true, Category.BackTools);
  public final ConfigDouble yoffset2 = new NDouble("config.ornaments.yoffset2", 0, -2, 2, true, Category.BackTools);
  public final ConfigDouble zoffset2 = new NDouble("config.ornaments.zoffset2", 0, -2, 2, true, Category.BugFix);
  public final ConfigDouble size2 = new NDouble("config.ornaments.size2", 2, 0, 5, true, Category.BugFix);

  // Belt Slot
  public final ConfigBoolean show_belt = new NBoolean("config.ornaments.show_belt", Category.BeltSlot);

  public final ConfigString beltslotType1 = new NString("config.ornaments.beltslottype1", Category.BeltSlot);
  public final ConfigBoolean enchanted1_1 = new NBoolean("config.ornaments.enchanted1_1", Category.BeltSlot);
  public final ConfigDouble rotateAngle1_1 = new NDouble("config.ornaments.rotateangle1_1", Category.BeltSlot);
  public final ConfigDouble xoffset1_1 = new NDouble("config.ornaments.xoffset1_1", Category.BeltSlot);
  public final ConfigDouble yoffset1_1 = new NDouble("config.ornaments.yoffset1_1", Category.BeltSlot);
  public final ConfigDouble rotateAnglex1_1 = new NDouble("config.ornaments.rotateanglex1_1", Category.BugFix);
  public final ConfigDouble rotateAngley1_1 = new NDouble("config.ornaments.rotateangley1_1", Category.BugFix);
  public final ConfigDouble zoffset1_1 = new NDouble("config.ornaments.zoffset1_1", Category.BugFix);
  public final ConfigDouble size1_1 = new NDouble("config.ornaments.size1_1", Category.BugFix);

  public final ConfigString beltslotType2 = new NString("config.ornaments.beltslottype2", Category.BeltSlot);
  public final ConfigBoolean enchanted2_1 = new NBoolean("config.ornaments.enchanted2_1", Category.BeltSlot);
  public final ConfigDouble rotateAngle2_1 = new NDouble("config.ornaments.rotateangle2_1", Category.BeltSlot);
  public final ConfigDouble xoffset2_1 = new NDouble("config.ornaments.xoffset2_1", Category.BeltSlot);
  public final ConfigDouble yoffset2_1 = new NDouble("config.ornaments.yoffset2_1", Category.BeltSlot);
  public final ConfigDouble rotateAnglex2_1 = new NDouble("config.ornaments.rotateanglex2_1", Category.BugFix);
  public final ConfigDouble rotateAngley2_1 = new NDouble("config.ornaments.rotateangley2_1", Category.BugFix);
  public final ConfigDouble zoffset2_1 = new NDouble("config.ornaments.zoffset2_1", Category.BugFix);
  public final ConfigDouble size2_1 = new NDouble("config.ornaments.size2_1", Category.BugFix);

  public Configs() {
    for (int i = 0; i < 8; i++) {
      nameList[i] = new NOptionList("config.ornaments.name" + String.valueOf(i + 1), Pattern.BASE, Category.Cape);
      colorList[i] = new NOptionList("config.ornaments.color" + String.valueOf(i + 1), ColorEnum.black,
          Category.Cape);
    }
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
