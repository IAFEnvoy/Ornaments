package ornaments.multiplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.MinecraftClient;
import ornaments.Config.Configs;

public class OrnamentsNetwork {
  public static Logger LOGGER = LogManager.getLogger();

  private static HashMap<String, PlayerInfo> map = new HashMap<String, PlayerInfo>();

  public static PlayerInfo getInfo(String playername) {
    return map.get(playername);
  }

  public static boolean hasInfo(String playername) {
    if (!map.containsKey(playername))
      return false;
    return map.get(playername) != null;
  }

  public static void start() {
    Timer timer = new Timer("OrnamentsNetwork");
    TimerTask task = new TimerTask() {
      public void run() {
        if (MinecraftClient.getInstance().getServer() == null) {
          Thread.currentThread().interrupt();
          return;
        }
        String[] playernames = MinecraftClient.getInstance().getServer().getPlayerNames();
        for (String name : playernames) {
          if (!map.containsKey(name))
            try {
              StringBuilder data = new StringBuilder();
              URLConnection uc = new URL(
                  String.format(Configs.General.CDN.getOptionListValue().getStringValue(), "Data", name + ".json"))
                      .openConnection();
              BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
              String inputLine = null;
              while ((inputLine = in.readLine()) != null) 
                data.append(inputLine);
              in.close();
              JsonObject jsons = new JsonParser().parse(data.toString()).getAsJsonObject();
              map.put(name, new PlayerInfo(jsons));
              log(Level.INFO, "Succeed to download " + name + "'s ornaments profile.");
            } catch (Exception e) {
              map.put(name, null);
              log(Level.INFO, "Fail to download " + name + "'s ornaments profile.");
            }
        }
      }
    };
    timer.schedule(task, 0, 1000);// 1s载入一次
  }

  public static void log(Level level, String message) {
    LOGGER.log(level, message);
  }
}