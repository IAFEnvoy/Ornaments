package iafenvoy.ornaments.gui.settings;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.config.ConfigManager;
import iafenvoy.ornaments.config.WingInfo;
import iafenvoy.ornaments.gui.buttons.BooleanButton;
import iafenvoy.ornaments.gui.buttons.OptionListButton;
import iafenvoy.ornaments.gui.buttons.TextField;
import iafenvoy.ornaments.renderer.wings.WingType;
import iafenvoy.ornaments.utils.MathUtil;

public class WingGui extends EntityGuiBase {
    public WingGui(WingInfo info, String textKey, GuiBase parent) {
        super(parent, false);
        this.title = StringUtils.translate(textKey);
        group.newProperty("config.ornaments.show");
        group.add(new BooleanButton("show", 70, info.shouldRender()));
        group.newProperty("config.ornaments.wingtype");
        group.add(new OptionListButton("wingtype", 70, info.getType()));
        group.newProperty("config.ornaments.lwingcolor");
        group.add(new TextField("lwingcolor1", 70, MathUtil.toString(info.getLeft1())));
        group.add(new TextField("lwingcolor2", 70, MathUtil.toString(info.getLeft2())));
        group.newProperty("config.ornaments.rwingcolor");
        group.add(new TextField("rwingcolor1", 70, MathUtil.toString(info.getRight1())));
        group.add(new TextField("rwingcolor2", 70, MathUtil.toString(info.getRight2())));
        group.setValueChangeCallback(() -> {
            info.setRender((boolean) group.getButtonById("show").getValue());
            info.setType((WingType) group.getButtonById("wingtype").getValue());
            try {
                info.setLeft1(MathUtil.parse((String) group.getButtonById("lwingcolor1").getValue()));
                info.setLeft2(MathUtil.parse((String) group.getButtonById("lwingcolor2").getValue()));
                info.setRight1(MathUtil.parse((String) group.getButtonById("rwingcolor1").getValue()));
                info.setRight2(MathUtil.parse((String) group.getButtonById("rwingcolor2").getValue()));
            } catch (NumberFormatException ignored) {
            }
            ConfigManager.INSTANCE.save();
        });
    }
}
