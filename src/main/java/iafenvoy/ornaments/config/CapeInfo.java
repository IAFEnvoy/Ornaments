package iafenvoy.ornaments.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import iafenvoy.ornaments.renderer.cape.type.ColorEnum;
import iafenvoy.ornaments.renderer.cape.type.Pattern;

public class CapeInfo implements Config {
    public static final CapeInfo cape = new CapeInfo();
    public static final CapeInfo leftElytra = new CapeInfo();
    public static final CapeInfo rightElytra = new CapeInfo();
    private final Pattern[] patterns = new Pattern[10];
    private final ColorEnum[] colors = new ColorEnum[10];
    private boolean render = false;
    private ColorEnum baseColor;

    public CapeInfo() {
        this.baseColor = ColorEnum.black;
        for (int i = 0; i < 10; i++) {
            patterns[i] = Pattern.BASE;
            colors[i] = ColorEnum.black;
        }
    }

    public boolean shouldRender() {
        return this.render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public void setPattern(Pattern p, int id) {
        this.patterns[id] = p;
    }

    public void setColor(ColorEnum c, int id) {
        this.colors[id] = c;
    }

    public ColorEnum getBaseColor() {
        return this.baseColor;
    }

    public void setBaseColor(ColorEnum baseColor) {
        this.baseColor = baseColor;
    }

    public Pattern getPatternById(int id) {
        return this.patterns[id];
    }

    public ColorEnum getColorById(int id) {
        return this.colors[id];
    }

    @Override
    public void copy(String value) throws JsonSyntaxException {
        CapeInfo info = new Gson().fromJson(value, CapeInfo.class);
        this.render = info.render;
        this.baseColor = info.baseColor;
        for (int i = 0; i < 10; i++) {
            patterns[i] = info.patterns[i];
            colors[i] = info.colors[i];
        }
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, CapeInfo.class);
    }
}
