package dev.rwmc.mechanicsplugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class RWMCMechanics extends JavaPlugin {

    public Logger Logger;

    @Override
    public void onEnable() {
        Logger = this.getLogger();
        Logger.log(Level.INFO, "RWMC Mechanics is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
