package iafenvoy.ornaments.Compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import iafenvoy.ornaments.Client.GuiConfig;

public class ModMenu implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> {
      return new GuiConfig();
    };
  }
}
