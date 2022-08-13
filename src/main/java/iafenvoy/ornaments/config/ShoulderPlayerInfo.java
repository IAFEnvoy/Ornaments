package iafenvoy.ornaments.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ShoulderPlayerInfo implements Config {
    public static final ShoulderPlayerInfo leftPlayer = new ShoulderPlayerInfo();
    public static final ShoulderPlayerInfo rightPlayer = new ShoulderPlayerInfo();
    private boolean render = false;
    private String playerName;
    private boolean isThinArm = false;

    public ShoulderPlayerInfo() {
        this.playerName = "";
    }

    public boolean shouldRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isThinArm() {
        return isThinArm;
    }

    public void setThinArm(boolean thinArm) {
        isThinArm = thinArm;
    }

    @Override
    public void copy(String value) throws JsonSyntaxException {
        ShoulderPlayerInfo info = new Gson().fromJson(value, ShoulderPlayerInfo.class);
        this.render = info.render;
        this.playerName = info.playerName;
        this.isThinArm = info.isThinArm;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, ShoulderPlayerInfo.class);
    }
}
