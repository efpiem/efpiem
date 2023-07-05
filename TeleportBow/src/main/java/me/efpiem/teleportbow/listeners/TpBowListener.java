package me.efpiem.teleportbow.listeners;

import me.efpiem.teleportbow.TeleportBow;
import me.efpiem.teleportbow.utils.BowUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class TpBowListener implements Listener {

    @EventHandler
    public void onArrowLand(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player p) {
            ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
            if (itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase
                    (ChatColor.WHITE + "" + ChatColor.BOLD + TeleportBow.getPlugin().getConfig().getString("bow-name"))) {
                Location location = e.getEntity().getLocation();
                p.teleport(location);
                e.getEntity().remove();
                p.sendMessage(ChatColor.DARK_GREEN + TeleportBow.getPlugin().getConfig().getString("teleport-message"));
                p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (TeleportBow.getPlugin().getConfig().getBoolean("give-on-join")){
            Player p = e.getPlayer();
            if (p.getInventory().containsAtLeast(BowUtils.createTpBow(), 1)){
                p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + TeleportBow.getPlugin().getConfig().getString("welcome-message") + p.getDisplayName() + " !");
            } else{
                p.getInventory().addItem(BowUtils.createTpBow());
                p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + TeleportBow.getPlugin().getConfig().getString("welcome-tp-bow-message"));
            }
        }
    }
}