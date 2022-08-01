package iafenvoy.ornaments.Config.NativeConfigType;

import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.Config.ConfigGUI.Category;

public class NColor extends ConfigColor {
  public NColor(String key, Category category) {
    this(key, "#FF000000", category);
  }

  public NColor(String key, String defaultValue, Category category) {
    super(StringUtils.translate(key), defaultValue, StringUtils.translate(key + ".help"));
    category.add(this);
  }
}
