package iafenvoy.ornaments.Config.NativeConfigType;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.Config.ConfigGUI.Category;

public class NOptionList extends ConfigOptionList {
  public NOptionList(String key, IConfigOptionListEntry defaultValue, Category category) {
    super(StringUtils.translate(key), defaultValue, StringUtils.translate(key + ".help"));
    category.add(this);
  }
}
