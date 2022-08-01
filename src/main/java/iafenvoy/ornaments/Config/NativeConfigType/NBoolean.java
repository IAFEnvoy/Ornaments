package iafenvoy.ornaments.Config.NativeConfigType;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.Config.ConfigGUI.Category;

public class NBoolean extends ConfigBoolean {
  public NBoolean(String key, Category category) {
    super(StringUtils.translate(key), false, StringUtils.translate(key + ".help"));
    category.add(this);
  }
}
