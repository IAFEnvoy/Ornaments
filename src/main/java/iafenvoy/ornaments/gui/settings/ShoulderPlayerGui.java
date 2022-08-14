package iafenvoy.ornaments.gui.settings;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.ConfigManager;
import iafenvoy.ornaments.config.ShoulderPlayerInfo;
import iafenvoy.ornaments.gui.buttons.BooleanButton;
import iafenvoy.ornaments.gui.buttons.TextField;

public class ShoulderPlayerGui extends EntityGuiBase {
    public ShoulderPlayerGui(ShoulderPlayerInfo info, String textKey, GuiBase parent) {
        super(parent, false);
        this.title = StringUtils.translate(textKey);
        group.newProperty("config.ornaments.show");
        group.add(new BooleanButton("show", 70, info.shouldRender()));
        group.newProperty("config.ornaments.player_name");
        group.add(new TextField("skin", 70, info.getPlayerName()));
        group.newProperty("config.ornaments.is_thin_arm");
        group.add(new BooleanButton("arm", 70, info.isThinArm()));
        group.setValueChangeCallback(() -> {
            info.setRender((boolean) group.getButtonById("show").getValue());
            info.setPlayerName((String) group.getButtonById("skin").getValue());
            info.setThinArm((boolean) group.getButtonById("arm").getValue());
            ConfigManager.INSTANCE.save();
        });
    }
}
