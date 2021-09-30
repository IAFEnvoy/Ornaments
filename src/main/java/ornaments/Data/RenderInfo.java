package ornaments.Data;

import net.minecraft.entity.player.PlayerEntity;
import ornaments.Config.Configs;

public class RenderInfo {
  public static boolean elytra = false;

  public static boolean ifRender(RenderThings thing,PlayerEntity player) {
    if(player.isSpectator()||player.isInvisible()||!player.isAlive()) return false;
    switch (thing) {
      case BACKITEM: {
        if(player.isFallFlying()||player.isSneaking()||player.isSleeping()) return false;
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
        if(!Configs.Wings.SHOW_WING.getBooleanValue()) return false;
        if(Configs.Wings.Overwrite_Elytra.getBooleanValue()) return true;
        return !elytra;
      }
      case MAGIC: {
        if(player.isFallFlying()||player.isSneaking()||player.isSleeping()) return false;
        return Configs.Magic.show_magic.getBooleanValue();
      }
      case HAT: {
        return true;
      }
      case MASK: {
        return true;
      }
      default: {
        return false;
      }
    }
  }

  public enum RenderThings {
    CAPE, ELYTRA, BACKITEM, WINGS,MAGIC,HAT,MASK;
  }
}
