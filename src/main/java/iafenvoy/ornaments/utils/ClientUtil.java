package iafenvoy.ornaments.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ClientUtil {
    public static String getRenderPlayer() {
        return MinecraftClient.getInstance().getSession().getUsername();
    }

    public static ItemStack GetItemFromName(String in, boolean enchanted) {
        try {
            ItemStack stack;
            if (!in.contains(":"))
                stack = new ItemStack(Registry.ITEM.get(new Identifier("minecraft", in)));
            else
                stack = new ItemStack(Registry.ITEM.get(new Identifier(in.split(":")[0], in.split(":")[1])));
            if (enchanted)
                stack.addEnchantment(Enchantment.byRawId(1), 1);
            return stack;
        } catch (Exception e) {
            e.printStackTrace();
            return new ItemStack(Items.AIR);
        }
    }
}
