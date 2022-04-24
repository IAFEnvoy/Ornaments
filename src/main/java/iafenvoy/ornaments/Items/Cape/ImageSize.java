package iafenvoy.ornaments.Items.Cape;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum ImageSize implements IConfigOptionListEntry {
  S8_7("s8_7", "ornaments.capesize.s8_7"), S8_8("s8_8", "ornaments.capesize.s8_8");

  private final String configString;
  private final String translationKey;

  private ImageSize(String configString, String translationKey) {
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
  public ImageSize cycle(boolean forward) {
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
  public ImageSize fromString(String name) {
    return fromStringStatic(name);
  }

  public static ImageSize fromStringStatic(String name) {
    for (ImageSize val : ImageSize.values()) {
      if (val.configString.equalsIgnoreCase(name)) {
        return val;
      }
    }

    return ImageSize.S8_7;
  }
}
