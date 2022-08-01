package iafenvoy.ornaments.Util;

import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import iafenvoy.ornaments.Config.Configs;

public class KeybindProvider implements IKeybindProvider {
    @Override
    public void addKeysToMap(IKeybindManager manager) {
        manager.addKeybindToMap(Configs.INSTANCE.MENU_OPEN_KEY.getKeybind());
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        // Not necessary
    }
}