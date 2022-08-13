package iafenvoy.ornaments;

import iafenvoy.ornaments.registry.KeyBind;
import iafenvoy.ornaments.registry.Textures;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrnamentClient implements ClientModInitializer {
    public static final String MOD_ID = "ornaments";
    public static final String MOD_NAME = "Ornaments";
    public static final Logger logger = LogManager.getLogger();


    @Override
    public void onInitializeClient() {
        if (!FabricLoader.getInstance().isModLoaded("malilib")) {
            logger.fatal("Malilib is not loaded! Please install Malilib to use Ornaments mod!");
            return;
        }
        logger.info("Initializing Ornament Client");
        Textures.register();
        KeyBind.register();
        ConfigManager.INSTANCE.load();
    }
}
