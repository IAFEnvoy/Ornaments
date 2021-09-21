package ornaments.Client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import ornaments.Config.ConfigHandler;
import ornaments.Config.Config;

import ornaments.Commands.Commands;

@Environment(EnvType.CLIENT)
public class client implements ClientModInitializer {
  public static Logger LOGGER = LogManager.getLogger();

  public static final String MOD_ID = "ornaments";
  public static final String MOD_NAME = "Ornaments";

  @Override
  public void onInitializeClient() {
    log(Level.INFO, "Registering commands...");
    Commands.init();
    config.init();
    config.readConfig();
    config.saveConfig();

    ConfigManager.getInstance().registerConfigHandler(MOD_ID, new ConfigHandler());
    // noinspection InstantiationOfUtilityClass
    new Config(); // just load the class and run static code block
    ConfigHandler.loadFile();
    InputEventHandler.getKeybindManager().registerKeybindProvider(new KeybindProvider());
    Config.MENU_OPEN_KEY.getKeybind().setCallback(new KeyBindHandler());
  }

  public static void log(Level level, String message) {
    LOGGER.log(level, "[" + MOD_NAME + "] " + message);
  }
}
