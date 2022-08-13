package iafenvoy.ornaments.renderer.cape.type;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.util.DyeColor;

public enum ColorEnum implements IConfigOptionListEntry {
    black(DyeColor.BLACK),
    red(DyeColor.RED),
    green(DyeColor.GREEN),
    brown(DyeColor.BROWN),
    blue(DyeColor.BLUE),
    purple(DyeColor.PURPLE),
    cyan(DyeColor.CYAN),
    light_gray(DyeColor.LIGHT_GRAY),
    gray(DyeColor.GRAY),
    pink(DyeColor.PINK),
    lime(DyeColor.LIME),
    yellow(DyeColor.YELLOW),
    light_blue(DyeColor.LIGHT_BLUE),
    magenta(DyeColor.MAGENTA),
    orange(DyeColor.ORANGE),
    white(DyeColor.WHITE);

    private final DyeColor dyeColor;

    ColorEnum(DyeColor dyeColor) {
        this.dyeColor = dyeColor;
    }

    public static ColorEnum fromStringStatic(String name) {
        for (ColorEnum val : ColorEnum.values())
            if (val.dyeColor.getName().equalsIgnoreCase(name))
                return val;
        return ColorEnum.black;
    }

    public DyeColor getDyeColor() {
        return this.dyeColor;
    }

    @Override
    public ColorEnum cycle(boolean forward) {
        int id = this.ordinal();
        if (forward) {
            if (++id >= values().length)
                id = 0;
        } else if (--id < 0)
            id = values().length - 1;
        return values()[id % values().length];
    }

    public ColorEnum fromString(String name) {
        return fromStringStatic(name);
    }

    @Override
    public String getDisplayName() {
        return StringUtils.translate("color." + this.dyeColor.getName());
    }

    public String getStringValue() {
        return this.dyeColor.getName();
    }

// --注释掉检查 START (2022/8/9 23:45):
//    public static ColorEnum getById(int id) {
//        for (ColorEnum val : ColorEnum.values())
//            if (val.dyeColor.getId() == id)
//                return val;
//        return ColorEnum.black;
//    }
// --注释掉检查 STOP (2022/8/9 23:45)
}
