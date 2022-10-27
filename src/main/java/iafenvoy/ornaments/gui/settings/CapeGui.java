package iafenvoy.ornaments.gui.settings;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.ConfigManager;
import iafenvoy.ornaments.config.CapeInfo;
import iafenvoy.ornaments.gui.buttons.BooleanButton;
import iafenvoy.ornaments.gui.buttons.OptionListButton;
import iafenvoy.ornaments.renderer.cape.type.ColorEnum;
import iafenvoy.ornaments.renderer.cape.type.Pattern;

public class CapeGui extends EntityGuiBase {
    public CapeGui(CapeInfo info, String textKey, boolean shouldRenderElytra, GuiBase parent) {
        super(parent, shouldRenderElytra);
        this.title = StringUtils.translate(textKey);
        group.newProperty("config.ornaments.show");
        group.add(new BooleanButton("show", 70, info.shouldRender()));
        if (shouldRenderElytra) {
            group.newProperty("config.ornaments.physical");
            group.add(new BooleanButton("physical", 70, info.isPhysical()));
        }
        group.newProperty("config.ornaments.colorbase");
        group.add(new OptionListButton("basecolor", 70, info.getBaseColor()));
        for (int i = 1; i <= 8; i++) {
            group.newProperty("config.ornaments.name" + i);
            group.add(new OptionListButton("pattern" + i, 70, info.getPatternById(i - 1)));
            group.add(new OptionListButton("color" + i, 70, info.getColorById(i - 1)));
        }
        group.setValueChangeCallback(() -> {
            info.setRender((boolean) group.getButtonById("show").getValue());
            info.setBaseColor((ColorEnum) group.getButtonById("basecolor").getValue());
            for (int i = 1; i <= 8; i++) {
                info.setPattern((Pattern) group.getButtonById("pattern" + i).getValue(), i - 1);
                info.setColor((ColorEnum) group.getButtonById("color" + i).getValue(), i - 1);
            }
            if (shouldRenderElytra)
                info.setPhysical((boolean) group.getButtonById("physical").getValue());
            ConfigManager.INSTANCE.save();
        });
    }
}
