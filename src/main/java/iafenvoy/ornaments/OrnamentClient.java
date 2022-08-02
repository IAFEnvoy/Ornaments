package iafenvoy.ornaments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import iafenvoy.ornaments.Config.Configs;
import iafenvoy.ornaments.Registry.Commands;
import iafenvoy.ornaments.Registry.Textures;
import iafenvoy.ornaments.Util.KeyBindHandler;
import iafenvoy.ornaments.Util.KeybindProvider;

@Environment(EnvType.CLIENT)
public class OrnamentClient implements ClientModInitializer {
  public static final String MOD_ID = "ornaments";
  public static final String MOD_NAME = "Ornaments";
  public static Logger logger = LogManager.getLogger();

  @Override
  public void onInitializeClient() {
    if (!FabricLoader.getInstance().isModLoaded("malilib")) {
      logger.error("Malilib is not loaded! Please install Malilib to use OrnamentClient!");
      return;
    }
    logger.info("Initializing Ornament Client");
    ConfigManager.getInstance().registerConfigHandler(MOD_ID, Configs.INSTANCE);
    Configs.loadFile();
    InputEventHandler.getKeybindManager().registerKeybindProvider(new KeybindProvider());
    Configs.INSTANCE.MENU_OPEN_KEY.getKeybind().setCallback(new KeyBindHandler());

    Textures.register();
    Commands.register();
  }
}
