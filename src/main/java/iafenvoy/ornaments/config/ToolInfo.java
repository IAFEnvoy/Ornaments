package iafenvoy.ornaments.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ToolInfo implements Config {
    public static final ToolInfo leftBackTool = new ToolInfo();
    public static final ToolInfo rightBackTool = new ToolInfo();
    public static final ToolInfo leftBeltTool = new ToolInfo();
    public static final ToolInfo rightBeltTool = new ToolInfo();
    private boolean render = false;
    private String item;
    private boolean enchanted = false;
    private float offsetX = 0, offsetY = 0, offsetZ = 0, rotateX = 0, rotateY = 0, rotateZ = 0;
    private float size = 2;

    public ToolInfo() {
        this.item = "minecraft:air";
    }

    public boolean shouldRender() {
        return render;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isEnchanted() {
        return enchanted;
    }

    public void setEnchanted(boolean enchanted) {
        this.enchanted = enchanted;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetZ(float offsetZ) {
        this.offsetZ = offsetZ;
    }

    public float getRotateX() {
        return rotateX;
    }

    public void setRotateX(float rotateX) {
        this.rotateX = rotateX;
    }

    public float getRotateY() {
        return rotateY;
    }

    public void setRotateY(float rotateY) {
        this.rotateY = rotateY;
    }

    public float getRotateZ() {
        return rotateZ;
    }

    public void setRotateZ(float rotateZ) {
        this.rotateZ = rotateZ;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    @Override
    public void copy(String value) throws JsonSyntaxException {
        ToolInfo info = new Gson().fromJson(value, ToolInfo.class);
        this.render = info.render;
        this.item = info.item;
        this.enchanted = info.enchanted;
        this.offsetX = info.offsetX;
        this.offsetY = info.offsetY;
        this.offsetZ = info.offsetZ;
        this.rotateX = info.rotateX;
        this.rotateY = info.rotateY;
        this.rotateZ = info.rotateZ;
        this.size = info.size;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, ToolInfo.class);
    }
}
