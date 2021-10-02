package ornaments.Client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ornaments.Config.Configs;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;

@Environment(EnvType.CLIENT)
public class client implements ClientModInitializer {
  public static Logger LOGGER = LogManager.getLogger();

  public static final String MOD_ID = "ornaments";
  public static final String MOD_NAME = "Ornaments";

  @Override
  public void onInitializeClient() {
    log(Level.INFO, "Registering...");
    Commands.init();

    ConfigManager.getInstance().registerConfigHandler(MOD_ID, new Configs());
    Configs.loadFile();
    InputEventHandler.getKeybindManager().registerKeybindProvider(new KeybindProvider());
    Configs.General.MENU_OPEN_KEY.getKeybind().setCallback(new KeyBindHandler());
    Configs.Init();
  }

  public static void log(Level level, String message) {
    LOGGER.log(level, "[" + MOD_NAME + "] " + message);
  }
}
