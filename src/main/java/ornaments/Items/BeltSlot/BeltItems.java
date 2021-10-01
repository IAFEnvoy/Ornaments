package ornaments.Items.BeltSlot;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;

public class BeltItems {
  private static HashMap<String,Item> itemlist=new HashMap<String,Item>();

  public static void Init() {
    itemlist.put("bow",Items.BOW);
    itemlist.put("crossbow",Items.CROSSBOW);
    itemlist.put("diamond_axe",Items.DIAMOND_AXE);
    itemlist.put("diamond_pickaxe",Items.DIAMOND_PICKAXE);
    itemlist.put("diamond_shovel",Items.DIAMOND_SHOVEL);
    itemlist.put("diamond_sword",Items.DIAMOND_SWORD);
    itemlist.put("golden_axe",Items.GOLDEN_AXE);
    itemlist.put("golden_pickaxe",Items.GOLDEN_PICKAXE);
    itemlist.put("golden_shovel",Items.GOLDEN_SHOVEL);
    itemlist.put("golden_sword",Items.GOLDEN_SWORD);
    itemlist.put("iron_axe",Items.IRON_AXE);
    itemlist.put("iron_pickaxe",Items.IRON_PICKAXE);
    itemlist.put("iron_shovel",Items.IRON_SHOVEL);
    itemlist.put("iron_sword",Items.IRON_SWORD);
    itemlist.put("netherite_axe",Items.NETHERITE_AXE);
    itemlist.put("netherite_pickaxe",Items.NETHERITE_PICKAXE);
    itemlist.put("netherite_shovel",Items.NETHERITE_SHOVEL);
    itemlist.put("netherite_sword",Items.NETHERITE_SWORD);
    itemlist.put("stone_axe",Items.STONE_AXE);
    itemlist.put("stone_pickaxe",Items.STONE_PICKAXE);
    itemlist.put("stone_shovel",Items.STONE_SHOVEL);
    itemlist.put("stone_sword",Items.STONE_SWORD);
    itemlist.put("trident",Items.TRIDENT);
    itemlist.put("wooden_axe",Items.WOODEN_AXE);
    itemlist.put("wooden_pickaxe",Items.WOODEN_PICKAXE);
    itemlist.put("wooden_shovel",Items.WOODEN_SHOVEL);
    itemlist.put("wooden_sword",Items.WOODEN_SWORD);
    itemlist.put("shears",Items.SHEARS);
    itemlist.put("flintandsteel",Items.FLINT_AND_STEEL);
  }

  public static Item GetItemFromName(String in) {
    return itemlist.get(in);
  }
}
