package me.efpiem.teleportbow.utils;

import me.efpiem.teleportbow.TeleportBow;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BowUtils {

    public static ItemStack createTpBow(){
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + TeleportBow.getPlugin().getConfig().getString("bow-name"));
        List<String> lore = new ArrayList<>();
        lore.add(TeleportBow.getPlugin().getConfig().getString("bow-description"));
        bowMeta.setLore(lore);
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        bow.setItemMeta(bowMeta);

        return bow;
    }
}
