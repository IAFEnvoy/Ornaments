package ornaments.Config;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum WingType implements IConfigOptionListEntry {
  None("none", "ornament.wingtype.none"),
  Feathered("feathered", "ornament.wingtype.feathered"),
  Dragon("dragon", "ornament.wingtype.dragon"),
  Light("light", "ornament.wingtype.light"),
  Tech("tech", "ornament.wingtype.tech"),
  Zanzas("zanzas", "ornament.wingtype.zanzas");

  private final String configString;
  private final String translationKey;

  WingType(String configString, String translationKey) {
    this.configString = configString;
    this.translationKey = translationKey;
  }

  @Override
  public String getStringValue() {
    return this.configString;
  }

  @Override
  public String getDisplayName() {
    return StringUtils.translate(this.translationKey);
  }

  @Override
  public IConfigOptionListEntry cycle(boolean forward) {
    int id = this.ordinal();
    if (forward) {
      if (++id >= values().length) {
        id = 0;
      }
    } else {
      if (--id < 0) {
        id = values().length - 1;
      }
    }
    return values()[id % values().length];
  }

  @Override
  public WingType fromString(String name) {
    return fromStringStatic(name);
  }

  public static WingType fromStringStatic(String name) {
    for (WingType val : WingType.values()) {
      if (val.configString.equalsIgnoreCase(name)) {
        return val;
      }
    }
    return WingType.None;
  }
}