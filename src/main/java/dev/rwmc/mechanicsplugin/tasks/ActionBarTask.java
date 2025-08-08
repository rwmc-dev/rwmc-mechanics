package dev.rwmc.mechanicsplugin.tasks;

import dev.rwmc.mechanicsplugin.RWMCMechanics;
import dev.rwmc.mechanicsplugin.Utilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.ShadowColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.ARGBLike;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ActionBarTask implements Runnable {

    private final RWMCMechanics plugin;

    public ActionBarTask(RWMCMechanics plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Collection<? extends Player> players = this.plugin.getServer().getOnlinePlayers();
        players.forEach(player -> {
            int karma = Utilities.getPlayerKarma(player);
            int maxKarma = Utilities.getPlayerMaxKarma(player);
            String karmaReference = "";
            int karmaSpacer = 56;
            if (maxKarma <= 4) {
                karmaReference = Character.toString((char)0xE016+karma);
            }

            player.sendActionBar(Component.text(karmaReference+(" ".repeat(karmaSpacer)), Style.style().shadowColor(ShadowColor.shadowColor(NamedTextColor.BLACK, 0)).build()));
        });
    }
}
