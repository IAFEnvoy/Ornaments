package iafenvoy.ornaments.Util;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import iafenvoy.ornaments.Config.ConfigGUI;
import net.minecraft.client.MinecraftClient;

public class KeyBindHandler implements IHotkeyCallback {
  @Override
  public boolean onKeyAction(KeyAction action, IKeybind key) {
    MinecraftClient client = MinecraftClient.getInstance();
    if (client.currentScreen instanceof ConfigGUI)
      client.currentScreen.onClose();
    else
      client.openScreen(new ConfigGUI());
    return true;
  }
}
