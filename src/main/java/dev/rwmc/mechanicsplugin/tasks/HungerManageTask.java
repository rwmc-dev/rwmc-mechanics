package dev.rwmc.mechanicsplugin.tasks;

import dev.rwmc.mechanicsplugin.RWMCMechanics;
import dev.rwmc.mechanicsplugin.Utilities;
import jdk.jshell.execution.Util;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.Collection;

public class HungerManageTask implements Runnable {

    private final RWMCMechanics plugin;

    public HungerManageTask(RWMCMechanics plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Collection<? extends Player> players = this.plugin.getServer().getOnlinePlayers();
        players.forEach(player -> {
            int food;
            if ((int)Utilities.getPlayerFood(player) == Utilities.getPlayerMaxFood(player))
                food = 20;
                else food = 19;
            player.setFoodLevel(food);
            player.setSaturation((float)food);

            if ((int)Utilities.getPlayerFood(player) > Utilities.getPlayerReqFood(player)) {
                double maxRegen = Utilities.getPlayerFood(player) - Utilities.getPlayerReqFood(player);
                AttributeInstance maxHealth = player.getAttribute(Attribute.MAX_HEALTH);
                assert maxHealth != null;
                this.plugin.getServer().getScheduler().runTaskTimer(plugin, task -> {
                    if (player.getHealth() != maxHealth.getValue() && (int)Utilities.getPlayerFood(player) >= Utilities.getPlayerReqFood(player)) {
                        double toHeal = Math.min(1, maxHealth.getValue() - player.getHealth());
                        toHeal = Math.min(maxRegen, toHeal);

                        player.heal(toHeal, EntityRegainHealthEvent.RegainReason.CUSTOM);
                        Utilities.setPlayerFood(player, Utilities.getPlayerFood(player) - (toHeal * 0.5));
                    } else {
                        task.cancel();
                    }
                }, 0, 80);
            }
        });
    }
}
