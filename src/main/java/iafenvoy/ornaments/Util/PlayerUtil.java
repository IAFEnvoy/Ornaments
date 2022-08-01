package iafenvoy.ornaments.Util;

import iafenvoy.ornaments.Config.Configs;
import net.minecraft.client.MinecraftClient;

public class PlayerUtil {
  private static final MinecraftClient client = MinecraftClient.getInstance();

  public static String getRenderPlayer() {
    String s1 = Configs.INSTANCE.User.getStringValue();
    String s2 = client.player.getName().getString();
    return s1.isBlank() ? s2 : s1;
  }
}
