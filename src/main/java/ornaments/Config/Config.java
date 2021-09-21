package ornaments.Config;

import ornaments.Client.client;
import ornaments.Config.wrappers.*;
import fi.dy.masa.malilib.config.options.*;

public class Config {
    public static final ConfigHotkey MENU_OPEN_KEY;
    public static final ConfigString PLAYERNAME;
    public static final ConfigString CapeUser;
    public static final ConfigString wingType;
    public static final ConfigColor wingcolorl1;
    public static final ConfigColor wingcolorl2;

    static {
      MENU_OPEN_KEY = Category.GENERAL.add(new LocalizedConfigHotkey(client.MOD_ID, "open_menu_key", "F7"));
      PLAYERNAME = Category.GENERAL.add(new LocalizedConfigString(client.MOD_ID, "playername", ""));
      CapeUser = Category.GENERAL.add(new LocalizedConfigString(client.MOD_ID, "capeuser", ""));
      wingType = Category.GENERAL.add(new LocalizedConfigString(client.MOD_ID, "wingtype", ""));
      wingcolorl1=Category.GENERAL.add(new LocalizedConfigColor(client.MOD_ID, "wingcolorl1", "#FFFFFF"));
      wingcolorl2=Category.GENERAL.add(new LocalizedConfigColor(client.MOD_ID, "wingcolorl2", "#FFFFFF"));
    }

}
