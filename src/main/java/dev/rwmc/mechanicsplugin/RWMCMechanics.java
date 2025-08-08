package dev.rwmc.mechanicsplugin;

import dev.rwmc.mechanicsplugin.tasks.ActionBarTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class RWMCMechanics extends JavaPlugin {

    public Logger Logger;

    @Override
    public void onEnable() {
        Logger = this.getLogger();
        Logger.log(Level.INFO, "RWMC Mechanics is enabled!");

        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.runTaskTimer(this, new ActionBarTask(this), 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
