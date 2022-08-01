package iafenvoy.ornaments.Config;

import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.util.StringUtils;
import iafenvoy.ornaments.OrnamentClient;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class ConfigGUI extends GuiConfigsBase {
  private static Category currentTab = Category.General;

  public ConfigGUI() {
    super(10, 50, OrnamentClient.MOD_ID, null, "config." + OrnamentClient.MOD_ID + ".title");
  }

  @Override
  public void initGui() {
    super.initGui();
    this.clearOptions();
    int x = 10, y = 26;
    // tab buttons are set
    for (Category category : Category.values()) {
      ButtonGeneric tabButton = new TabButton(category, x, y, -1, 20, StringUtils.translate(category.getKey()));
      tabButton.setEnabled(true);
      this.addButton(tabButton, (buttonBase, i) -> {
        this.onSettingsChanged();
        // reload the GUI when tab button is clicked
        currentTab = ((TabButton) buttonBase).category;
        this.reCreateListWidget();
        this.getListWidget().resetScrollbarPosition();
        this.initGui();
      });
      x += tabButton.getWidth() + 2;
    }
  }

  @Override
  public List<ConfigOptionWrapper> getConfigs() {
    return ConfigOptionWrapper.createFor(currentTab.getConfigs());
  }

  public static class TabButton extends ButtonGeneric {
    private final Category category;

    public TabButton(Category category, int x, int y, int width, int height, String text, String... hoverStrings) {
      super(x, y, width, height, text, hoverStrings);
      this.category = category;
    }
  }

  public enum Category {
    General("config.ornaments.general"),
    Cape("config.ornaments.cape"),
    Wings("config.ornaments.wings"),
    BackTools("config.ornaments.backtools"),
    BeltSlot("config.ornaments.sideitem"),
    BugFix("config.ornaments.bugfix");
  
    private final String key;
    private final List<IConfigBase> configs;
  
    Category(String key) {
      this.key = key;
      configs = new ArrayList<>();
    }
  
    public <T extends IConfigBase> T add(T config) {
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
}
