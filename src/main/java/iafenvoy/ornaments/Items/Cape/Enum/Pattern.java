package iafenvoy.ornaments.Items.Cape.Enum;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.OrnamentClient;
import net.minecraft.util.Identifier;

public enum Pattern implements IConfigOptionListEntry {
  none("none"),
  square_bottom_left("square_bottom_left"),
  square_bottom_right("square_bottom_right"),
  square_top_left("square_top_left"),
  square_top_right("square_top_right"),
  stripe_bottom("stripe_bottom"),
  stripe_top("stripe_top"),
  stripe_left("stripe_left"),
  stripe_right("stripe_right"),
  stripe_center("stripe_center"),
  stripe_middle("stripe_middle"),
  stripe_downright("stripe_downright"),
  stripe_downleft("stripe_downleft"),
  small_stripes("small_stripes"),
  cross("cross"),
  triangle_bottom("triangle_bottom"),
  triangle_top("triangle_top"),
  triangles_bottom("triangles_bottom"),
  triangles_top("triangles_top"),
  diagonal_left("diagonal_left"),
  diagonal_right("diagonal_right"),
  diagonal_up_left("diagonal_up_left"),
  diagonal_up_right("diagonal_up_right"),
  circle("circle"),
  rhombus("rhombus"),
  half_vertical("half_vertical"),
  half_horizontal("half_horizontal"),
  half_vertical_right("half_vertical_right"),
  half_horizontal_bottom("half_horizontal_bottom"),
  creeper("creeper"),
  bricks("bricks"),
  gradient("gradient"),
  gradient_up("gradient_up"),
  skull("skull"),
  flower("flower"),
  border("border"),
  curly_border("curly_border"),
  straight_cross("straight_cross");

  private final String key;

  private Pattern(String key) {
    this.key = key;
  }

  public String getPatternName() {
    return StringUtils.translate("block.minecraft.banner." + this.key + ".red")
        .replace(StringUtils.translate("color.red"), "")
        .replace(StringUtils.translate("color.red").replace("色", ""), "");
  }

  public Identifier getTexture() {
    return new Identifier(OrnamentClient.MOD_ID, this.key);
  }

  @Override
  public IConfigOptionListEntry cycle(boolean forward) {
    int id = this.ordinal();
    if (forward) {
      if (++id >= values().length)
        id = 0;
    } else if (--id < 0)
      id = values().length - 1;
    return values()[id % values().length];
  }

  @Override
  public IConfigOptionListEntry fromString(String name) {
    return fromStringStatic(name);
  }

  @Override
  public String getDisplayName() {
    return this.getPatternName();
  }

  @Override
  public String getStringValue() {
    return this.key;
  }

  public static Pattern fromStringStatic(String name) {
    for (Pattern val : Pattern.values())
      if (val.key.equalsIgnoreCase(name))
        return val;
    return Pattern.none;
  }
}
