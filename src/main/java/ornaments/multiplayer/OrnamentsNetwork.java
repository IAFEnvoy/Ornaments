package ornaments.multiplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ornaments.Config.Configs;

public class OrnamentsNetwork {
  private static Logger LOGGER = LogManager.getLogger();

  private static HashMap<String, PlayerInfo> map = new HashMap<String, PlayerInfo>();
  private static List<String> queue = new ArrayList<>();
  private static Timer timer = new Timer("OrnamentsNetwork");
  private static TimerTask task = new TimerTask() {
    public void run() {
      while (!queue.isEmpty()) {
        String name = queue.get(0);
        log(Level.INFO, "Start to download " + name + "'s ornaments profile......");
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
        queue.remove(0);
      }
    }
  };

  public static PlayerInfo getInfo(String playername) {
    return map.get(playername);
  }

  public static boolean hasInfo(String playername) {
    if (!map.containsKey(playername))
      return false;
    return map.get(playername) != null;
  }

  public static void start() {
    timer.schedule(task, 0, 1000);// 1s载入一次
  }

  public static void addToQueue(String name) {
    if (map.containsKey(name))
      return;
    if (queue.contains(name))
      return;
    map.put(name, null);// 临时存放
    Thread thread = new Thread() {
      public void run() {
        try {
          URL url = new URL(
              String.format(Configs.General.CDN.getOptionListValue().getStringValue(), "Data", name + ".json"));
          url.openConnection().getInputStream();
          queue.add(name);
        } catch (Exception e) {
        }
      }
    };
    thread.setName("OrnamentsNetwork " + name);
    thread.start();
  }

  public static void clear() {
    map.clear();
    queue.clear();
  }

  public static void log(Level level, String message) {
    LOGGER.log(level, message);
  }
}