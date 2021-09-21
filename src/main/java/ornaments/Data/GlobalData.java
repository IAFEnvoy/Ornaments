package ornaments.Data;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
  public static List<PlayerInfo> players = new ArrayList<>();
  private static int index;

  public static void setKey(String playername, String key, String data) {
    if (players.size() == 0)
      players.add(new PlayerInfo(playername));
    if (index < 0 || index >= players.size())
      index = 0;
    if (!players.get(index).getPlayerName().equals(playername)) {
      boolean flag = false;
      for (index = 0; index < players.size(); index++) {
        if (players.get(index).getPlayerName().equals(playername)) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        players.add(new PlayerInfo(playername));
        index = players.size() - 1;
      }
    }
    if (key.equals("cape"))
      players.get(index).setCape(data);
    if (key.equals("wing"))
      players.get(index).setWings(data);
    if (key.equals("wr1"))
      players.get(index).setColor(key, Float.parseFloat(data));
    if (key.equals("wg1"))
      players.get(index).setColor(key, Float.parseFloat(data));
    if (key.equals("wb1"))
      players.get(index).setColor(key, Float.parseFloat(data));
    if (key.equals("wr2"))
      players.get(index).setColor(key, Float.parseFloat(data));
    if (key.equals("wg2"))
      players.get(index).setColor(key, Float.parseFloat(data));
    if (key.equals("wb2"))
      players.get(index).setColor(key, Float.parseFloat(data));
    if (key.equals("hide"))
      players.get(index).setHide(Boolean.parseBoolean(data));
    if (key.equals("backitem"))
      players.get(index).setBackItem(data);
  }

  public static String getKey(String playername, String key) {
    if (players.size() == 0)
      players.add(new PlayerInfo(playername));
    if (index < 0 || index >= players.size())
      index = 0;
    if (!players.get(index).getPlayerName().equals(playername)) {
      boolean flag = false;
      for (index = 0; index < players.size(); index++) {
        if (players.get(index).getPlayerName().equals(playername)) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        players.add(new PlayerInfo(playername));
        index = players.size() - 1;
      }
    }

    if (key.equals("name"))
      return players.get(index).getPlayerName();
    if (key.equals("cape"))
      return players.get(index).getCape();
    if (key.equals("wing"))
      return players.get(index).getWingType();
    if (key.equals("wr1"))
      return String.valueOf(players.get(index).getColor("r1"));
    if (key.equals("wg1"))
      return String.valueOf(players.get(index).getColor("g1"));
    if (key.equals("wb1"))
      return String.valueOf(players.get(index).getColor("b1"));
    if (key.equals("wr2"))
      return String.valueOf(players.get(index).getColor("r2"));
    if (key.equals("wg2"))
      return String.valueOf(players.get(index).getColor("g2"));
    if (key.equals("wb2"))
      return String.valueOf(players.get(index).getColor("b2"));
    if (key.equals("hide"))
      return String.valueOf(players.get(index).getHide());
    if (key.equals("backitem"))
      return String.valueOf(players.get(index).getBackItem());
    return null;
  }

  public static boolean hasPlayer(String playername) {
    for (index = 0; index < players.size(); index++)
      if (players.get(index).getPlayerName().equals(playername))
        return true;
    return false;
  }
}
