package me.efpiem.teleportbow;

import me.efpiem.teleportbow.commands.GiveCommand;
import me.efpiem.teleportbow.listeners.TpBowListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportBow extends JavaPlugin {

    private static TeleportBow plugin;

    public static TeleportBow getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("giveteleportbow").setExecutor(new GiveCommand());
        getServer().getPluginManager().registerEvents(new TpBowListener(), this);
    }


}
