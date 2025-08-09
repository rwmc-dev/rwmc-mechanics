package dev.rwmc.mechanicsplugin.tasks;

import dev.rwmc.mechanicsplugin.RWMCMechanics;
import dev.rwmc.mechanicsplugin.Utilities;
import org.bukkit.entity.Player;

import java.util.Collection;

public class HungerLockTask implements Runnable {

    private final RWMCMechanics plugin;

    public HungerLockTask(RWMCMechanics plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Collection<? extends Player> players = this.plugin.getServer().getOnlinePlayers();
        players.forEach(player -> {
            int food;
            if (Utilities.getPlayerFood(player) == Utilities.getPlayerMaxFood(player))
                food = 20;
                else food = 19;
            player.setFoodLevel(food);
            player.setSaturation((float)food);
        });
    }
}
