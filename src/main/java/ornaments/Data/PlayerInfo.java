package ornaments.Data;

import ornaments.Client.config;

public class PlayerInfo {
  private String playername = "";
  private String cape = "";
  private String wing = "";
  private float wr1 = 0, wg1 = 0, wb1 = 0, wr2 = 0, wg2 = 0, wb2 = 0;
  private boolean hidewithe = true;
  private String magic = "";
  private float mr1 = 0, mg1 = 0, mb1 = 0, mr2 = 0, mg2 = 0, mb2 = 0;
  private String backitem="";

  public PlayerInfo(String playername) {
    this.playername = playername;
  }

  public String getWingType() {
    return this.wing;
  }
  public String getBackItem() {
    return this.backitem;
  }

  public String getMagicType() {
    return this.magic;
  }

  public String getCape() {
    return this.cape;
  }

  public boolean getHide() {
    return this.hidewithe;
  }

  public String getPlayerName() {
    return this.playername;
  }

  public void setColorWing(float r1, float g1, float b1, float r2, float g2, float b2) {
    this.wr1 = r1;
    this.wr2 = r2;
    this.wg1 = g1;
    this.wg2 = g2;
    this.wb1 = b1;
    this.wb2 = b2;
    config.saveConfig();
  }

  public void setColorMagic(float r1, float g1, float b1, float r2, float g2, float b2) {
    this.mr1 = r1;
    this.mr2 = r2;
    this.mg1 = g1;
    this.mg2 = g2;
    this.mb1 = b1;
    this.mb2 = b2;
    config.saveConfig();
  }

  public void setColor(String key, float in) {
    if (key.equals("wr1"))
      wr1 = in;
    if (key.equals("wg1"))
      wg1 = in;
    if (key.equals("wb1"))
      wb1 = in;
    if (key.equals("wr2"))
      wr2 = in;
    if (key.equals("wg2"))
      wg2 = in;
    if (key.equals("wb2"))
      wb2 = in;
    if (key.equals("mr1"))
      mr1 = in;
    if (key.equals("mg1"))
      mg1 = in;
    if (key.equals("mb1"))
      mb1 = in;
    if (key.equals("mr2"))
      mr2 = in;
    if (key.equals("mg2"))
      mg2 = in;
    if (key.equals("mb2"))
      mb2 = in;
    config.saveConfig();
  }

  public float getColor(String argument) {
    switch (argument) {
      case "wr1":
        return wr1;
      case "wr2":
        return wr2;
      case "wg1":
        return wg1;
      case "wg2":
        return wg2;
      case "wb1":
        return wb1;
      case "wb2":
        return wb2;
      case "mr1":
        return mr1;
      case "mr2":
        return mr2;
      case "mg1":
        return mg1;
      case "mg2":
        return mg2;
      case "mb1":
        return mb1;
      case "mb2":
        return mb2;
      default:
        return 0;
    }
  }

  public void setWings(String wingname) {
    this.wing = wingname;
    config.saveConfig();
  }

  public void setHide(boolean hidewithe) {
    this.hidewithe = hidewithe;
    config.saveConfig();
  }

  public void setCape(String playername) {
    this.cape = playername;
    config.saveConfig();
  }

  public void setMagicType(String type) {
    this.magic = type;
    config.saveConfig();
  }

  public void setBackItem(String item) {
    this.backitem = item;
    config.saveConfig();
  }

}
