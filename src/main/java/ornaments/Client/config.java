package ornaments.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ornaments.Data.GlobalData;
import ornaments.Data.PlayerInfo;

public class config {
  private static String configpath = "\\config\\ornaments.config";

  public static void init() {
    File f = new File("");
    String cf = null;
    try {
      cf = f.getCanonicalPath();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    configpath = cf + configpath;
  }

  public static void readConfig() {
    File file = new File(configpath);
    if (!file.exists())
      return;
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(configpath));
      try {
        try {
          GlobalData.players = new ArrayList<>();
          int cnt = Integer.parseInt(br.readLine());
          for (int i = 0; i < cnt; i++) {
            PlayerInfo player = new PlayerInfo(br.readLine());// Player name
            player.setCape(br.readLine());
            player.setWings(br.readLine());
            player.setColor(Float.parseFloat(br.readLine()), Float.parseFloat(br.readLine()),
                Float.parseFloat(br.readLine()), Float.parseFloat(br.readLine()), Float.parseFloat(br.readLine()),
                Float.parseFloat(br.readLine()));
            player.setHide(Boolean.parseBoolean(br.readLine()));
            GlobalData.players.add(player);
          }
        } catch (NumberFormatException ee) {
          GlobalData.players = new ArrayList<>();
          ee.printStackTrace();
        }
      } catch (IOException e) {
        GlobalData.players = new ArrayList<>();
        e.printStackTrace();
      } finally {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void saveConfig() {
    try {
      File file = new File(configpath);
      if (!file.exists())
        file.createNewFile();
      BufferedWriter bw = new BufferedWriter(new FileWriter(configpath, false));
      StringBuffer sBuffer = new StringBuffer("");
      sBuffer.append(GlobalData.players.size() + "\n");
      for (int i = 0; i < GlobalData.players.size(); i++) {
        sBuffer.append(GlobalData.players.get(i).getPlayerName() + "\n");
        sBuffer.append(GlobalData.players.get(i).getCape() + "\n");
        sBuffer.append(GlobalData.players.get(i).getWingType() + "\n");
        sBuffer.append(GlobalData.players.get(i).getColor("r1") + "\n");
        sBuffer.append(GlobalData.players.get(i).getColor("g1") + "\n");
        sBuffer.append(GlobalData.players.get(i).getColor("b1") + "\n");
        sBuffer.append(GlobalData.players.get(i).getColor("r2") + "\n");
        sBuffer.append(GlobalData.players.get(i).getColor("g2") + "\n");
        sBuffer.append(GlobalData.players.get(i).getColor("b2") + "\n");
        sBuffer.append(GlobalData.players.get(i).getHide() + "\n");
      }
      bw.write(sBuffer.toString());
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
