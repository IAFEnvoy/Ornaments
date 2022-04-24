package iafenvoy.ornaments.Config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.IConfigBase;

import java.util.ArrayList;
import java.util.List;

public enum Category {
  GENERAL("config.ornaments.general"), Cape("config.ornaments.cape"), Wings("config.ornaments.wings"),
  BackTools("config.ornaments.backtools"), BeltSlot("config.ornaments.sideitem"),BugFix("config.ornaments.bugfix")
  // Magic("config.ornaments.magic")
  ;

  private final String key;
  private final List<IConfigBase> configs;

  Category(String key) {
    this.key = key;
    configs = new ArrayList<>();
  }

  protected <T extends IConfigBase> T add(T config) {
    this.configs.add(config);
    return config;
  }

  public List<IConfigBase> getConfigs() {
    return ImmutableList.copyOf(this.configs);
  }

  public String getKey() {
    return this.key;
  }
}
