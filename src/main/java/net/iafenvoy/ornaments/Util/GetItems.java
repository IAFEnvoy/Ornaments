package net.iafenvoy.ornaments.Util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GetItems {
  public static ItemStack GetItemFromName(String in, boolean enchanted) {
    ItemStack stack = ItemStack.EMPTY;
    if (!in.contains(":"))
      stack = new ItemStack(Registry.ITEM.get(new Identifier("minecraft", in)));
    else
      stack = new ItemStack(Registry.ITEM.get(new Identifier(in.split(":")[0], in.split(":")[1])));
    if (enchanted)
      stack.addEnchantment(Enchantment.byRawId(1), 1);
    return stack;
  }
}
