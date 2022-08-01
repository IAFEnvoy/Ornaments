package iafenvoy.ornaments.Util;

import iafenvoy.ornaments.Config.Configs;
import net.minecraft.entity.player.PlayerEntity;

public class RenderInfo {
  public static boolean elytra = false;

  public static boolean ifRender(RenderThings thing, PlayerEntity player) {
    if (player.isSpectator() || player.isInvisible() || !player.isAlive())
      return false;
    switch (thing) {
    case BACKITEM: {
      return Configs.INSTANCE.show_back_tool.getBooleanValue() && !elytra;
    }
    case CAPE: {
      // if (elytra)
      // return Configs.Cape.Render_With_Elytra.getBooleanValue();
      return Configs.INSTANCE.SHOW_CAPE.getBooleanValue();
    }
    case ELYTRA: {
      if (player.getEntityName().equals(Configs.INSTANCE.User.getStringValue()))
        return !Configs.INSTANCE.Overwrite_Elytra.getBooleanValue();
      else
        return true;
    }
    case BELTSLOT: {
      return Configs.INSTANCE.show_belt.getBooleanValue();
    }
    default: {
      return false;
    }
    }
  }

  public enum RenderThings {
    CAPE, ELYTRA, BACKITEM, MAGIC, BELTSLOT;
  }
}
