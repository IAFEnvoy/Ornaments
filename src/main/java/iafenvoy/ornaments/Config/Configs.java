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
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.JsonUtils;
import iafenvoy.ornaments.Items.Cape.ImageSize;
import iafenvoy.ornaments.Items.Wings.WingType;
import net.minecraft.text.TranslatableText;

public class Configs implements IConfigHandler {
  private static final String FILE_PATH = "./config/ornaments.json";
  private static final File CONFIG_DIR = new File("./config");

  public static class General {
    public static final ConfigHotkey MENU_OPEN_KEY = new ConfigHotkey(
        new TranslatableText("config.ornaments.open_menu_key").getString(), "F7", KeybindSettings.DEFAULT,
        new TranslatableText("config.ornaments.open_menu_key.help").getString());
    public static final ConfigString User = new ConfigString(
        new TranslatableText("config.ornaments.user").getString(), "",
        new TranslatableText("config.ornaments.user.help").getString());
    public static final ConfigString CDN = new ConfigString(
        new TranslatableText("config.ornaments.cdn").getString(),
        "https://cdn.jsdelivr.net/gh/IAFEnvoy/OrnamentsStorage@latest/%s/%s",
        new TranslatableText("config.ornaments.cdn.help").getString());

    public static void Init() {
      Category.GENERAL.add(MENU_OPEN_KEY);
      Category.GENERAL.add(User);
      Category.GENERAL.add(CDN);
    }
  }

  public static class Cape {
    public static final ConfigBoolean SHOW_CAPE = new ConfigBoolean(
        new TranslatableText("config.ornaments.show_cape").getString(), false,
        new TranslatableText("config.ornaments.show_cape.help").getString());
    public static final ConfigBoolean UseImage = new ConfigBoolean(
        new TranslatableText("config.ornaments.useimage").getString(), false,
        new TranslatableText("config.ornaments.useimage.help").getString());
    public static final ConfigString ImageName = new ConfigString(
        new TranslatableText("config.ornaments.imagename").getString(), "",
        new TranslatableText("config.ornaments.imagename.help").getString());
    public static final ConfigOptionList showoption = new ConfigOptionList(
        new TranslatableText("config.ornaments.showoption").getString(), ImageSize.S8_7,
        new TranslatableText("config.ornaments.showoption.help").getString());
    public static final ConfigColor colorbase = new ConfigColor(
        new TranslatableText("config.ornaments.colorbase").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.colorbase.help").getString());
    public static final ConfigString name1 = new ConfigString(
        new TranslatableText("config.ornaments.name1").getString(), "",
        new TranslatableText("config.ornaments.name1.help").getString());
    public static final ConfigColor color1 = new ConfigColor(
        new TranslatableText("config.ornaments.color1").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color1.help").getString());
    public static final ConfigString name2 = new ConfigString(
        new TranslatableText("config.ornaments.name2").getString(), "",
        new TranslatableText("config.ornaments.name2.help").getString());
    public static final ConfigColor color2 = new ConfigColor(
        new TranslatableText("config.ornaments.color2").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color2.help").getString());
    public static final ConfigString name3 = new ConfigString(
        new TranslatableText("config.ornaments.name3").getString(), "",
        new TranslatableText("config.ornaments.name3.help").getString());
    public static final ConfigColor color3 = new ConfigColor(
        new TranslatableText("config.ornaments.color3").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color3.help").getString());
    public static final ConfigString name4 = new ConfigString(
        new TranslatableText("config.ornaments.name4").getString(), "",
        new TranslatableText("config.ornaments.name4.help").getString());
    public static final ConfigColor color4 = new ConfigColor(
        new TranslatableText("config.ornaments.color4").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color4.help").getString());
    public static final ConfigString name5 = new ConfigString(
        new TranslatableText("config.ornaments.name5").getString(), "",
        new TranslatableText("config.ornaments.name5.help").getString());
    public static final ConfigColor color5 = new ConfigColor(
        new TranslatableText("config.ornaments.color5").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color5.help").getString());
    public static final ConfigString name6 = new ConfigString(
        new TranslatableText("config.ornaments.name6").getString(), "",
        new TranslatableText("config.ornaments.name6.help").getString());
    public static final ConfigColor color6 = new ConfigColor(
        new TranslatableText("config.ornaments.color6").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color6.help").getString());
    public static final ConfigString name7 = new ConfigString(
        new TranslatableText("config.ornaments.name7").getString(), "",
        new TranslatableText("config.ornaments.name7.help").getString());
    public static final ConfigColor color7 = new ConfigColor(
        new TranslatableText("config.ornaments.color7").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color7.help").getString());
    public static final ConfigString name8 = new ConfigString(
        new TranslatableText("config.ornaments.name8").getString(), "",
        new TranslatableText("config.ornaments.name8.help").getString());
    public static final ConfigColor color8 = new ConfigColor(
        new TranslatableText("config.ornaments.color8").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color8.help").getString());

    public static void Init() {
      Category.Cape.add(SHOW_CAPE);
      Category.Cape.add(showoption);
      Category.Cape.add(UseImage);
      Category.Cape.add(ImageName);
      Category.Cape.add(colorbase);
      Category.Cape.add(name1);
      Category.Cape.add(color1);
      Category.Cape.add(name2);
      Category.Cape.add(color2);
      Category.Cape.add(name3);
      Category.Cape.add(color3);
      Category.Cape.add(name4);
      Category.Cape.add(color4);
      Category.Cape.add(name5);
      Category.Cape.add(color5);
      Category.Cape.add(name6);
      Category.Cape.add(color6);
      Category.Cape.add(name7);
      Category.Cape.add(color7);
      Category.Cape.add(name8);
      Category.Cape.add(color8);
    }
  }

  public static class Wings {
    public static final ConfigBoolean SHOW_WING = new ConfigBoolean(
        new TranslatableText("config.ornaments.show_wing").getString(), false,
        new TranslatableText("config.ornaments.show_wing.help").getString());
    public static final ConfigBoolean Overwrite_Elytra = new ConfigBoolean(
        new TranslatableText("config.ornaments.overwrite_elytra").getString(), false,
        new TranslatableText("config.ornaments.overwrite_elytra.help").getString());
    public static final ConfigOptionList wingtype = new ConfigOptionList(
        new TranslatableText("config.ornaments.wingtype").getString(), WingType.None,
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
      Category.Wings.add(wingtype);
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
    public static final ConfigString backToolType1 = new ConfigString(
        new TranslatableText("config.ornaments.backtooltype1").getString(), "",
        new TranslatableText("config.ornaments.backtooltype1.help").getString());
    public static final ConfigBoolean enchanted1 = new ConfigBoolean(
        new TranslatableText("config.ornaments.enchanted1").getString(), false,
        new TranslatableText("config.ornaments.enchanted1.help").getString());
    public static final ConfigDouble rotateAngle1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangle1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangle1.help").getString());
    public static final ConfigDouble rotateAnglex1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateanglex1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateanglex1.help").getString());
    public static final ConfigDouble rotateAngley1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangley1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangley1.help").getString());
    public static final ConfigDouble xoffset1 = new ConfigDouble(
        new TranslatableText("config.ornaments.xoffset1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.xoffset1.help").getString());
    public static final ConfigDouble yoffset1 = new ConfigDouble(
        new TranslatableText("config.ornaments.yoffset1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.yoffset1.help").getString());
    public static final ConfigDouble zoffset1 = new ConfigDouble(
        new TranslatableText("config.ornaments.zoffset1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.zoffset1.help").getString());
    public static final ConfigString backToolType2 = new ConfigString(
        new TranslatableText("config.ornaments.backtooltype2").getString(), "",
        new TranslatableText("config.ornaments.backtooltype2.help").getString());
    public static final ConfigBoolean enchanted2 = new ConfigBoolean(
        new TranslatableText("config.ornaments.enchanted2").getString(), false,
        new TranslatableText("config.ornaments.enchanted2.help").getString());
    public static final ConfigDouble rotateAngle2 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangle2").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangle2.help").getString());
    public static final ConfigDouble xoffset2 = new ConfigDouble(
        new TranslatableText("config.ornaments.xoffset2").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.xoffset2.help").getString());
    public static final ConfigDouble yoffset2 = new ConfigDouble(
        new TranslatableText("config.ornaments.yoffset2").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.yoffset2.help").getString());
    public static final ConfigDouble zoffset2 = new ConfigDouble(
        new TranslatableText("config.ornaments.zoffset2").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.zoffset2.help").getString());
    public static final ConfigDouble rotateAnglex2 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateanglex2").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateanglex2.help").getString());
    public static final ConfigDouble rotateAngley2 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangley2").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangley2.help").getString());
    public static final ConfigDouble size1 = new ConfigDouble(
        new TranslatableText("config.ornaments.size1").getString(), 2, 0, 5, true,
        new TranslatableText("config.ornaments.size1.help").getString());
    public static final ConfigDouble size2 = new ConfigDouble(
        new TranslatableText("config.ornaments.size2").getString(), 2, 0, 5, true,
        new TranslatableText("config.ornaments.size2.help").getString());

    public static void Init() {
      Category.BackTools.add(show_back_tool);
      Category.BackTools.add(backToolType1);
      Category.BackTools.add(enchanted1);
      Category.BackTools.add(rotateAngle1);
      Category.BackTools.add(xoffset1);
      Category.BackTools.add(yoffset1);
      Category.BackTools.add(backToolType2);
      Category.BackTools.add(enchanted2);
      Category.BackTools.add(rotateAngle2);
      Category.BackTools.add(xoffset2);
      Category.BackTools.add(yoffset2);

      Category.BugFix.add(rotateAnglex1);
      Category.BugFix.add(rotateAngley1);
      Category.BugFix.add(zoffset1);
      Category.BugFix.add(size1);
      Category.BugFix.add(rotateAnglex2);
      Category.BugFix.add(rotateAngley2);
      Category.BugFix.add(zoffset2);
      Category.BugFix.add(size2);
    }
  }

  public static class BeltSlot {
    public static final ConfigBoolean show_belt = new ConfigBoolean(
        new TranslatableText("config.ornaments.show_belt").getString(), false,
        new TranslatableText("config.ornaments.show_belt.help").getString());
    public static final ConfigString beltslotType1 = new ConfigString(
        new TranslatableText("config.ornaments.beltslottype1").getString(), "",
        new TranslatableText("config.ornaments.beltslottype1.help").getString());
    public static final ConfigBoolean enchanted1_1 = new ConfigBoolean(
        new TranslatableText("config.ornaments.enchanted1_1").getString(), false,
        new TranslatableText("config.ornaments.enchanted1_1.help").getString());
    public static final ConfigDouble rotateAngle1_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangle1_1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangle1_1.help").getString());
    public static final ConfigDouble xoffset1_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.xoffset1_1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.xoffset1_1.help").getString());
    public static final ConfigDouble yoffset1_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.yoffset1_1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.yoffset1_1.help").getString());
    public static final ConfigString beltslotType2 = new ConfigString(
        new TranslatableText("config.ornaments.beltslottype2").getString(), "",
        new TranslatableText("config.ornaments.beltslottype2.help").getString());
    public static final ConfigBoolean enchanted2_1 = new ConfigBoolean(
        new TranslatableText("config.ornaments.enchanted2_1").getString(), false,
        new TranslatableText("config.ornaments.enchanted2_1.help").getString());
    public static final ConfigDouble rotateAngle2_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangle2_1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangle2_1.help").getString());
    public static final ConfigDouble xoffset2_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.xoffset2_1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.xoffset2_1.help").getString());
    public static final ConfigDouble yoffset2_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.yoffset2_1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.yoffset2_1.help").getString());

    public static final ConfigDouble rotateAnglex1_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateanglex1_1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateanglex1_1.help").getString());
    public static final ConfigDouble rotateAngley1_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangley1_1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangley1_1.help").getString());
    public static final ConfigDouble zoffset1_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.zoffset1_1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.zoffset1_1.help").getString());
    public static final ConfigDouble rotateAnglex2_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateanglex2_1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateanglex2_1.help").getString());
    public static final ConfigDouble rotateAngley2_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.rotateangley2_1").getString(), 0, 0, 360, true,
        new TranslatableText("config.ornaments.rotateangley2_1.help").getString());
    public static final ConfigDouble zoffset2_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.zoffset2_1").getString(), 0, -2, 2, true,
        new TranslatableText("config.ornaments.zoffset2_1.help").getString());
    public static final ConfigDouble size1_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.size1_1").getString(), 2, 0, 5, true,
        new TranslatableText("config.ornaments.size1_1.help").getString());
    public static final ConfigDouble size2_1 = new ConfigDouble(
        new TranslatableText("config.ornaments.size2_1").getString(), 2, 0, 5, true,
        new TranslatableText("config.ornaments.size2_1.help").getString());

    public static void Init() {
      Category.BeltSlot.add(show_belt);
      Category.BeltSlot.add(beltslotType1);
      Category.BeltSlot.add(enchanted1_1);
      Category.BeltSlot.add(rotateAngle1_1);
      Category.BeltSlot.add(xoffset1_1);
      Category.BeltSlot.add(yoffset1_1);
      Category.BeltSlot.add(beltslotType2);
      Category.BeltSlot.add(enchanted2_1);
      Category.BeltSlot.add(rotateAngle2_1);
      Category.BeltSlot.add(xoffset2_1);
      Category.BeltSlot.add(yoffset2_1);

      Category.BugFix.add(rotateAnglex1_1);
      Category.BugFix.add(rotateAngley1_1);
      Category.BugFix.add(zoffset1_1);
      Category.BugFix.add(size1_1);
      Category.BugFix.add(rotateAnglex2_1);
      Category.BugFix.add(rotateAngley2_1);
      Category.BugFix.add(zoffset2_1);
      Category.BugFix.add(size2_1);
    }
  }

  public static class Magic {
    public static final ConfigBoolean show_magic = new ConfigBoolean(
        new TranslatableText("config.ornaments.show_magic").getString(), false,
        new TranslatableText("config.ornaments.show_magic.help").getString());
    public static final ConfigString type1 = new ConfigString(
        new TranslatableText("config.ornaments.type1").getString(), "",
        new TranslatableText("config.ornaments.type1.help").getString());
    public static final ConfigColor color1 = new ConfigColor(
        new TranslatableText("config.ornaments.color1").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color1.help").getString());
    public static final ConfigString type2 = new ConfigString(
        new TranslatableText("config.ornaments.type2").getString(), "",
        new TranslatableText("config.ornaments.type2.help").getString());
    public static final ConfigColor color2 = new ConfigColor(
        new TranslatableText("config.ornaments.color2").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color2.help").getString());
    public static final ConfigString type3 = new ConfigString(
        new TranslatableText("config.ornaments.type3").getString(), "",
        new TranslatableText("config.ornaments.type3.help").getString());
    public static final ConfigColor color3 = new ConfigColor(
        new TranslatableText("config.ornaments.color3").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color3.help").getString());
    public static final ConfigString type4 = new ConfigString(
        new TranslatableText("config.ornaments.type4").getString(), "",
        new TranslatableText("config.ornaments.type4.help").getString());
    public static final ConfigColor color4 = new ConfigColor(
        new TranslatableText("config.ornaments.color4").getString(), "0xF0FFFFFF",
        new TranslatableText("config.ornaments.color4.help").getString());

    public static void Init() {
      // Category.Magic.add(show_magic);
      // Category.Magic.add(type1);
      // Category.Magic.add(color1);
      // Category.Magic.add(type2);
      // Category.Magic.add(color2);
      // Category.Magic.add(type3);
      // Category.Magic.add(color3);
      // Category.Magic.add(type4);
      // Category.Magic.add(color4);
    }
  }

  public static void Init() {
    General.Init();
    Cape.Init();
    Wings.Init();
    BackTools.Init();
    BeltSlot.Init();
    // Magic.Init();
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
