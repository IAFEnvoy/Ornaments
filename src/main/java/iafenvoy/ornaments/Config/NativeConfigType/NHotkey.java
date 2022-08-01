package iafenvoy.ornaments.Config.NativeConfigType;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.Config.ConfigGUI.Category;

public class NHotkey extends ConfigHotkey {
  public NHotkey(String key, Category category) {
    this(key, "", category);
  }

  public NHotkey(String key, String defaultHotkey, Category category) {
    super(StringUtils.translate(key), defaultHotkey,
        KeybindSettings.DEFAULT, StringUtils.translate(key + ".help"));
    category.add(this);
  }
}
