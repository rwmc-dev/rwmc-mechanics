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

            double food = Utilities.getPlayerFood(player);
            int maxFood = Utilities.getPlayerMaxFood(player);
            int reqFood = Utilities.getPlayerReqFood(player);

            String extra = "";
            if (food%1 != 0) {
                int offset = (int) (food%1 * 3);
                extra = Character.toString((char)0xE015-offset);
            }
            String foodBar = "\uE010".repeat((int) Math.floor(food))+extra+"\uE012".repeat(Math.max(0, maxFood-(int) Math.ceil(food)));
            foodBar = foodBar.substring(0,reqFood)+"\uE011"+foodBar.substring(reqFood);
            int spacesBetween = 0;
            spacesBetween = switch (Utilities.getPlayerSlugcat(player)) {
                case "survivor" -> 29;
                case "monk" -> 34;
                case "hunter" -> 24;
                default -> spacesBetween;
            };

            player.sendActionBar(Component.text(karmaReference+(" ".repeat(spacesBetween)+foodBar+"          "), Style.style().shadowColor(ShadowColor.shadowColor(NamedTextColor.BLACK, 0)).build()));
        });
    }
}
