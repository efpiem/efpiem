package me.efpiem.teleportbow.commands;

import me.efpiem.teleportbow.TeleportBow;
import me.efpiem.teleportbow.utils.BowUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p){
            if (p.hasPermission("tpbow.give")){
                if (strings.length == 0){ //we want to give the tp bow to ourselves
                    ItemStack bow = BowUtils.createTpBow();
                    p.getInventory().addItem(bow);
                    p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                    p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + TeleportBow.getPlugin().getConfig().getString("given-yourself-message"));
                } else{ //we want to give the tp bow to someone else
                    Player target = Bukkit.getPlayerExact(strings[0]);
                    if (target == null){
                        p.sendMessage(ChatColor.RED + TeleportBow.getPlugin().getConfig().getString("target-player-error-message"));
                        return true;
                    }
                    ItemStack bow = BowUtils.createTpBow();
                    target.getInventory().addItem(bow);
                    target.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                    target.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + p.getDisplayName() + TeleportBow.getPlugin().getConfig().getString("someone-else-given-message"));
                }
            } else{
                p.sendMessage(ChatColor.RED + TeleportBow.getPlugin().getConfig().getString("permission-error-message"));
            }
        }
        return true;
    }
}
