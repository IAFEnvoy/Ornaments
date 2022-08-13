package iafenvoy.ornaments.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import fi.dy.masa.malilib.util.Color4f;
import iafenvoy.ornaments.renderer.wings.WingType;

public class WingInfo implements Config {
    public static final WingInfo wing = new WingInfo();
    private boolean render = false;
    private WingType type;
    private Color4f left1, left2, right1, right2;

    public WingInfo() {
        this.type = WingType.None;
        this.left1 = this.left2 = this.right1 = this.right2 = new Color4f(0, 0, 0);
    }

    public WingType getType() {
        return type;
    }

    public void setType(WingType type) {
        this.type = type;
    }

    public Color4f getLeft1() {
        return left1;
    }

    public void setLeft1(Color4f left1) {
        this.left1 = left1;
    }

    public Color4f getLeft2() {
        return left2;
    }

    public void setLeft2(Color4f left2) {
        this.left2 = left2;
    }

    public Color4f getRight1() {
        return right1;
    }

    public void setRight1(Color4f right1) {
        this.right1 = right1;
    }

    public Color4f getRight2() {
        return right2;
    }

    public void setRight2(Color4f right2) {
        this.right2 = right2;
    }

    public boolean shouldRender() {
        return this.render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    @Override
    public void copy(String value) throws JsonSyntaxException {
        WingInfo info = new Gson().fromJson(value, WingInfo.class);
        this.render = info.render;
        this.type = info.type;
        this.left1 = info.left1;
        this.left2 = info.left2;
        this.right1 = info.right1;
        this.right2 = info.right2;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, WingInfo.class);
    }
}
