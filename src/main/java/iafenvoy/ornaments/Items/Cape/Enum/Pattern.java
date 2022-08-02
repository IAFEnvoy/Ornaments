package iafenvoy.ornaments.Items.Cape.Enum;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.OrnamentClient;
import net.minecraft.util.Identifier;

public enum Pattern implements IConfigOptionListEntry {
  BASE("base", "b"),
  SQUARE_BOTTOM_LEFT("square_bottom_left", "bl"),
  SQUARE_BOTTOM_RIGHT("square_bottom_right", "br"),
  SQUARE_TOP_LEFT("square_top_left", "tl"),
  SQUARE_TOP_RIGHT("square_top_right", "tr"),
  STRIPE_BOTTOM("stripe_bottom", "bs"),
  STRIPE_TOP("stripe_top", "ts"),
  STRIPE_LEFT("stripe_left", "ls"),
  STRIPE_RIGHT("stripe_right", "rs"),
  STRIPE_CENTER("stripe_center", "cs"),
  STRIPE_MIDDLE("stripe_middle", "ms"),
  STRIPE_DOWNRIGHT("stripe_downright", "drs"),
  STRIPE_DOWNLEFT("stripe_downleft", "dls"),
  STRIPE_SMALL("small_stripes", "ss"),
  CROSS("cross", "cr"),
  STRAIGHT_CROSS("straight_cross", "sc"),
  TRIANGLE_BOTTOM("triangle_bottom", "bt"),
  TRIANGLE_TOP("triangle_top", "tt"),
  TRIANGLES_BOTTOM("triangles_bottom", "bts"),
  TRIANGLES_TOP("triangles_top", "tts"),
  DIAGONAL_LEFT("diagonal_left", "ld"),
  DIAGONAL_RIGHT("diagonal_up_right", "rd"),
  DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud"),
  DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud"),
  CIRCLE_MIDDLE("circle", "mc"),
  RHOMBUS_MIDDLE("rhombus", "mr"),
  HALF_VERTICAL("half_vertical", "vh"),
  HALF_HORIZONTAL("half_horizontal", "hh"),
  HALF_VERTICAL_MIRROR("half_vertical_right", "vhr"),
  HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb"),
  BORDER("border", "bo"),
  CURLY_BORDER("curly_border", "cbo"),
  GRADIENT("gradient", "gra"),
  GRADIENT_UP("gradient_up", "gru"),
  BRICKS("bricks", "bri"),
  GLOBE("globe", "glb"),
  CREEPER("creeper", "cre"),
  SKULL("skull", "sku"),
  FLOWER("flower", "flo"),
  MOJANG("mojang", "moj"),
  PIGLIN("piglin", "pig"),
  // Pattern from Ice And Fire
  AMPHITHERE("amphithere", "am1", true),
  BIRD("bird", "bi1", true),
  DREAD("dread", "dr1", true),
  EYE("eye", "ey1", true),
  FAE("fae", "fa1", true),
  FEATHER("feather", "fe1", true),
  FIRE("fire", "fi1", true),
  FIRE_HEAD("fire_head", "fi2", true),
  GORGON("gorgon", "go1", true),
  HIPPOCAMPUS("hippocampus", "hi1", true),
  HIPPOGRYPH_HEAD("hippogryph_head", "hi2", true),
  ICE("ice", "ic1", true),
  ICE_HEAD("ice_head", "ic2", true),
  LIGHTNING("lightning", "li1", true),
  LIGHTNING_HEAD("lightning_head", "li2", true),
  MERMAID("mermaid", "me1", true),
  SEA_SERPENT("sea_serpent", "se1", true),
  TROLL("troll", "tr1", true),
  UNICORN("unicorn", "un1", true),
  WEEZER("weezer", "we1", true);

  private final String key, id;
  private final boolean isNew;

  private Pattern(String key, String id) {
    this(key, id, false);
  }

  private Pattern(String key, String id, boolean isNew) {
    this.key = key;
    this.id = id;
    this.isNew = isNew;
  }

  public String getPatternName() {
    return StringUtils.translate("block.minecraft.banner." + this.key + ".red")
        .replace(StringUtils.translate("color.red"), "")
        .replace(StringUtils.translate("color.red").replace("色", ""), "");
  }

  public Identifier getTexture() {
    return new Identifier(OrnamentClient.MOD_ID, this.key);
  }

  public String getId() {
    return this.id;
  }

  public boolean shouldReg() {
    return this.isNew;
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
    return Pattern.BASE;
  }

  public static Pattern getById(String id) {
    for (Pattern p : Pattern.values())
      if (p.id.equals(id))
        return p;
    return Pattern.BASE;
  }
}
