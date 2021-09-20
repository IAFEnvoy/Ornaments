package ornaments.Data;

import ornaments.Client.config;

public class PlayerInfo {
    private String cape = "";
    private String wing = "";
    private float r1 = 0, g1 = 0, b1 = 0, r2 = 0, g2 = 0, b2 = 0;

    public PlayerInfo(String wing) {
        this.wing = wing;
    }

    public String getWingType() {
        return this.wing;
    }

    public String getCape() {
        return this.cape;
    }

    public void setColor(float r1, float g1, float b1,float r2, float g2, float b2) {
        this.r1 =r1; this.r2 = r2;
        this.g1 =g1; this.g2 = g2;
        this.b1 =b1; this.b2 = b2;
        config.setR1(r1);
        config.setG1(g1);
        config.setB1(b1);
        config.setR2(r2);
        config.setG2(g2);
        config.setB2(b2);
        config.saveConfig();
    }

    public float getColor(String argument) {
        switch (argument) {
            case "r1":
                return r1;
            case "r2":
                return r2;
            case "g1":
                return g1;
            case "g2":
                return g2;
            case "b1":
                return b1;
            case "b2":
                return b2;
            default:
                return 0;
        }
    }

    public void setWings(String wingname) {
        this.wing = wingname;
        config.setWingType(wingname);
        config.saveConfig();
    }

    public void setCape(String playername) {
        this.cape = playername;
        config.setCape(playername);
        config.saveConfig();
    }
}
