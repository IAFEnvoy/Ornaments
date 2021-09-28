package ornaments.Items.BackItems;

import java.util.List;
import java.util.ArrayList;

public class BackItems {
  private static List<String> items = new ArrayList<>();

  public static void Init() {
    items.add("bow");
    items.add("crossbow");
    items.add("diamond_axe");
    items.add("diamond_pickaxe");
    items.add("diamond_shovel");
    items.add("diamond_sword");
    items.add("golden_axe");
    items.add("golden_pickaxe");
    items.add("golden_shovel");
    items.add("golden_sword");
    items.add("iron_axe");
    items.add("iron_pickaxe");
    items.add("iron_shovel");
    items.add("iron_sword");
    items.add("netherite_axe");
    items.add("netherite_pickaxe");
    items.add("netherite_shovel");
    items.add("netherite_sword");
    items.add("stone_axe");
    items.add("stone_pickaxe");
    items.add("stone_shovel");
    items.add("stone_sword");
    items.add("trident");
    items.add("wooden_axe");
    items.add("wooden_pickaxe");
    items.add("wooden_shovel");
    items.add("wooden_sword");
  }

  public static boolean InsideIt(String in) {
    for (String s : items)
      if (s.equals(in))
        return true;
    return false;
  }
}
