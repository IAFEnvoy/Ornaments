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
      case WINGS: {
        if(!Configs.Wings.SHOW_WING.getBooleanValue()) return false;
        if(Configs.Wings.Overwrite_Elytra.getBooleanValue()) return true;
        return !elytra;
      }
      default: {
        return false;
      }
    }
  }

  public enum RenderThings {
    CAPE, ELYTRA, BACKITEM, WINGS;
  }
}
