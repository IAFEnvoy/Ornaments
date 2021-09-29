package ornaments.Data;

import ornaments.Config.Configs;

public class RenderInfo {
  public static boolean elytra = false;

  public static boolean ifRender(RenderThings thing) {
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
      case ELYTRAOVERWRITE: {
        return false;
      }
      case WINGS: {
        if (elytra)
          return Configs.Wings.SHOW_WITH_ELYTRA.getBooleanValue();
        return Configs.Wings.SHOW_WING.getBooleanValue();
      }
      default: {
        return false;
      }
    }
  }

  public enum RenderThings {
    CAPE, ELYTRA, BACKITEM, WINGS, ELYTRAOVERWRITE;
  }
}
