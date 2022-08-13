package iafenvoy.ornaments.renderer.cape.type;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum Pattern implements IConfigOptionListEntry {
    BASE("base"),
    SQUARE_BOTTOM_LEFT("square_bottom_left"),
    SQUARE_BOTTOM_RIGHT("square_bottom_right"),
    SQUARE_TOP_LEFT("square_top_left"),
    SQUARE_TOP_RIGHT("square_top_right"),
    STRIPE_BOTTOM("stripe_bottom"),
    STRIPE_TOP("stripe_top"),
    STRIPE_LEFT("stripe_left"),
    STRIPE_RIGHT("stripe_right"),
    STRIPE_CENTER("stripe_center"),
    STRIPE_MIDDLE("stripe_middle"),
    STRIPE_DOWNRIGHT("stripe_downright"),
    STRIPE_DOWNLEFT("stripe_downleft"),
    STRIPE_SMALL("small_stripes"),
    CROSS("cross"),
    STRAIGHT_CROSS("straight_cross"),
    TRIANGLE_BOTTOM("triangle_bottom"),
    TRIANGLE_TOP("triangle_top"),
    TRIANGLES_BOTTOM("triangles_bottom"),
    TRIANGLES_TOP("triangles_top"),
    DIAGONAL_LEFT("diagonal_left"),
    DIAGONAL_RIGHT("diagonal_up_right"),
    DIAGONAL_LEFT_MIRROR("diagonal_up_left"),
    DIAGONAL_RIGHT_MIRROR("diagonal_right"),
    CIRCLE_MIDDLE("circle"),
    RHOMBUS_MIDDLE("rhombus"),
    HALF_VERTICAL("half_vertical"),
    HALF_HORIZONTAL("half_horizontal"),
    HALF_VERTICAL_MIRROR("half_vertical_right"),
    HALF_HORIZONTAL_MIRROR("half_horizontal_bottom"),
    BORDER("border"),
    CURLY_BORDER("curly_border"),
    GRADIENT("gradient"),
    GRADIENT_UP("gradient_up"),
    BRICKS("bricks"),
    GLOBE("globe"),
    CREEPER("creeper"),
    SKULL("skull"),
    FLOWER("flower"),
    MOJANG("mojang"),
    PIGLIN("piglin"),
    // Pattern from Ice And Fire
//    DRAGON("dragon", true),
//    DRAGON_HEAD("dragon_head", true),

    AMPHITHERE("amphithere", true),
    BIRD("bird", true),
    DREAD("dread", true),
    EYE("eye", true),
    FAE("fae", true),
    FEATHER("feather", true),
    GORGON("gorgon", true),
    HIPPOCAMPUS("hippocampus", true),
    HIPPOGRYPH_HEAD("hippogryph_head", true),
    MERMAID("mermaid", true),
    SEA_SERPENT("sea_serpent", true),
    TROLL("troll", true),
    UNICORN("unicorn", true),
    WEEZER("weezer", true);

    private final String key;
    private final boolean isNew;

    Pattern(String key) {
        this(key, false);
    }

    Pattern(String key, boolean isNew) {
        this.key = key;
        this.isNew = isNew;
    }

    public static Pattern fromStringStatic(String name) {
        for (Pattern val : Pattern.values())
            if (val.key.equalsIgnoreCase(name))
                return val;
        return Pattern.BASE;
    }

    public String getPatternName() {
        return StringUtils.translate("block.minecraft.banner." + this.key + ".red")
                .replace(StringUtils.translate("color.red"), "")
                .replace(StringUtils.translate("color.red").replace("色", ""), "");
    }

    public boolean shouldReg() {
        return this.isNew;
    }

    @Override
    public Pattern cycle(boolean forward) {
        int id = this.ordinal();
        if (forward) {
            if (++id >= values().length)
                id = 0;
        } else if (--id < 0)
            id = values().length - 1;
        return values()[id % values().length];
    }

    public Pattern fromString(String name) {
        return fromStringStatic(name);
    }

    @Override
    public String getDisplayName() {
        return this.getPatternName();
    }

    public String getStringValue() {
        return this.key;
    }
}
