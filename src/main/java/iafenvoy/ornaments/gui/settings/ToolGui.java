package iafenvoy.ornaments.gui.settings;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.config.ConfigManager;
import iafenvoy.ornaments.config.ToolInfo;
import iafenvoy.ornaments.gui.buttons.BooleanButton;
import iafenvoy.ornaments.gui.buttons.TextField;
import iafenvoy.ornaments.utils.MathUtil;

public class ToolGui extends EntityGuiBase {
    public ToolGui(ToolInfo info, String textKey, GuiBase parent) {
        super(parent, false);
        this.title = StringUtils.translate(textKey);
        group.newProperty("config.ornaments.show");
        group.add(new BooleanButton("show", 70, info.shouldRender()));
        group.newProperty("config.ornaments.itemtype");
        group.add(new TextField("item", 150, info.getItem()));
        group.newProperty("config.ornaments.enchanted");
        group.add(new BooleanButton("enchanted", 70, info.isEnchanted()));
        group.newProperty("config.ornaments.xoffset");
        group.add(new TextField("xoffset", 70, String.valueOf(info.getOffsetX())));
        group.newProperty("config.ornaments.yoffset");
        group.add(new TextField("yoffset", 70, String.valueOf(info.getOffsetY())));
        group.newProperty("config.ornaments.rotatez");
        group.add(new TextField("rotatez", 70, String.valueOf(info.getRotateZ())));
        group.newProperty("config.ornaments.size");
        group.add(new TextField("size", 70, String.valueOf(info.getSize())));
        group.setValueChangeCallback(() -> {
            info.setRender((boolean) group.getButtonById("show").getValue());
            info.setItem((String) group.getButtonById("item").getValue());
            info.setEnchanted((boolean) group.getButtonById("enchanted").getValue());
            info.setOffsetX(MathUtil.parseToFloat(group.getButtonById("xoffset")));
            info.setOffsetY(MathUtil.parseToFloat(group.getButtonById("yoffset")));
            info.setRotateZ(MathUtil.parseToFloat(group.getButtonById("rotatez")));
            info.setSize(MathUtil.parseToFloat(group.getButtonById("size")));
            ConfigManager.INSTANCE.save();
        });
    }
}
