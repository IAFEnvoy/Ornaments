package ornaments.Client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ornaments.Commands.Commands;
import ornaments.Data.PlayerInfo;

@Environment(EnvType.CLIENT)
public class client implements ClientModInitializer {
	public static Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "ornaments";
	public static final String MOD_NAME = "Ornaments";

	public static PlayerInfo player = new PlayerInfo("");

	@Override
	public void onInitializeClient() {
		log(Level.INFO,"Registering commands...");
		Commands.init();
		config.init();
		config.readConfig();
		player.setColor(config.getR(), config.getG(), config.getB());
		player.setWings(config.getWingType());
		player.setCape(config.getCape());
    config.saveConfig();
	}
	public static void log(Level level, String message) {
		LOGGER.log(level, "[" + MOD_NAME + "] " + message);
	}
}
