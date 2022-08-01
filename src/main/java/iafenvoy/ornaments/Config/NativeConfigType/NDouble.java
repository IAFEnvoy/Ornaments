package iafenvoy.ornaments.Config.NativeConfigType;

import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.Config.ConfigGUI.Category;

public class NDouble extends ConfigDouble {
  public NDouble(String key, Category category) {
    this(key, 0, Double.MIN_VALUE, Double.MAX_VALUE, false, category);
  }

  public NDouble(String key, double defaultValue, double minValue, double maxValue, boolean useSlider,
      Category category) {
    super(StringUtils.translate(key), defaultValue, minValue, maxValue, useSlider,
        StringUtils.translate(key + ".help"));
    category.add(this);
  }
}
