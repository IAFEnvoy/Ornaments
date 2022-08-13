package iafenvoy.ornaments.registry;

import iafenvoy.ornaments.OrnamentClient;
import iafenvoy.ornaments.gui.MainGui;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBind {
    private static KeyBinding guiKey;

    public static void register() {
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("config.ornaments.open_menu_key", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, OrnamentClient.MOD_NAME));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (guiKey.isPressed())
                client.openScreen(new MainGui());
        });
    }
}
