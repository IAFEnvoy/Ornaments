package ornaments.Util;

import net.minecraft.entity.player.PlayerEntity;
import ornaments.Config.Configs;

public class RenderInfo {
  public static boolean elytra = false;

  public static boolean ifRender(RenderThings thing, PlayerEntity player) {
    if (player.isSpectator() || player.isInvisible() || !player.isAlive())
      return false;
    switch (thing) {
      case BACKITEM: {
        return Configs.BackTools.show_back_tool.getBooleanValue() && !elytra;
      }
      case CAPE: {
        if (elytra)
          return Configs.Cape.Render_With_Elytra.getBooleanValue();
        return Configs.Cape.SHOW_CAPE.getBooleanValue();
      }
      case ELYTRA: {
        return !Configs.Wings.Overwrite_Elytra.getBooleanValue();
      }
      case WINGS: {
        if (!Configs.Wings.SHOW_WING.getBooleanValue())
          return false;
        if (Configs.Wings.Overwrite_Elytra.getBooleanValue())
          return true;
        return !elytra;
      }
      case MAGIC: {
        if (player.isFallFlying() || player.isSneaking() || player.isSleeping() || player.isInSwimmingPose())
          return false;
        return Configs.Magic.show_magic.getBooleanValue();
      }
      case BELTSLOT: {
        return Configs.BeltSlot.show_belt.getBooleanValue();
      }
      default: {
        return false;
      }
    }
  }

  public enum RenderThings {
    CAPE, ELYTRA, BACKITEM, WINGS, MAGIC, BELTSLOT;
  }
}
