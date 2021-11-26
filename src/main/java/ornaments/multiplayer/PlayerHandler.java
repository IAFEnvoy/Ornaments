package ornaments.multiplayer;

import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.entity.player.PlayerEntity;
import ornaments.Config.Configs;

public class PlayerHandler {

  private static HashMap<String, PlayerInfo> map = new HashMap<String, PlayerInfo>();

  public static PlayerInfo getInfo(String playername) {
    return map.get(playername);
  }

  public static boolean hasInfo(String playername) {
    if (!map.containsKey(playername))
      return false;
    return map.get(playername) != null;
  }

  public static void onPlayerJoin(PlayerEntity player) {
    Thread thread = new Thread() {
      public void run() {
        try {
          URL url = new URL(
              String.format(Configs.General.CDN.getOptionListValue().getStringValue(), "Data", player.getEntityName() + ".json"));
          JsonParser parser = new JsonParser();
          JsonObject jsons = parser.parse(new String(url.openStream().readAllBytes())).getAsJsonObject();
          map.put(player.getEntityName(), new PlayerInfo(jsons));
          System.out.println("Succeed to download " + player.getEntityName() + "'s ornaments profile.");
        } catch (Exception e) {
          map.put(player.getEntityName(), null);
          System.out.println("Fail to download " + player.getEntityName() + "'s ornaments profile.");
        }
      }
    };
    thread.start();
  }
}
