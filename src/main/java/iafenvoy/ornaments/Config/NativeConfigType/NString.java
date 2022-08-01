package iafenvoy.ornaments.Config.NativeConfigType;

import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.Config.ConfigGUI.Category;

public class NString extends ConfigString {
  public NString(String keyname, Category category) {
    this(keyname, "", category);
  }

  public NString(String key, String defaultValue, Category category) {
    super(StringUtils.translate(key), defaultValue, StringUtils.translate(key + ".help"));
    category.add(this);
  }
}
