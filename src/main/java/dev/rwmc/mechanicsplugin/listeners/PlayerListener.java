package dev.rwmc.mechanicsplugin.listeners;

import dev.rwmc.mechanicsplugin.Utilities;
import jdk.jshell.execution.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemType;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class PlayerListener implements Listener {
    @EventHandler
    @SuppressWarnings("UnstableApiUsage")
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) { return; }
        String playerSlugcat = Utilities.getPlayerSlugcat(event.getPlayer());

        Map<String, String> slugcatDiets = Map.of(
          "survivor", "all",
          "monk", "all",
          "hunter", "meat"
        );
        Map<String, ItemType[]> dietTypes = Map.of(
                "meat", new ItemType[]{
                        ItemType.BEEF, ItemType.PORKCHOP, ItemType.MUTTON, ItemType.CHICKEN, ItemType.RABBIT,
                        ItemType.COD, ItemType.SALMON, ItemType.TROPICAL_FISH, ItemType.PUFFERFISH, ItemType.ROTTEN_FLESH,
                        ItemType.SPIDER_EYE, ItemType.RABBIT_STEW, ItemType.COOKED_BEEF, ItemType.COOKED_PORKCHOP, ItemType.COOKED_MUTTON,
                        ItemType.COOKED_CHICKEN, ItemType.COOKED_RABBIT, ItemType.COOKED_COD, ItemType.COOKED_SALMON
                },
                "plant", new ItemType[]{
                        ItemType.APPLE, ItemType.GOLDEN_APPLE, ItemType.ENCHANTED_GOLDEN_APPLE, ItemType.MELON_SLICE, ItemType.SWEET_BERRIES,
                        ItemType.GLOW_BERRIES, ItemType.CHORUS_FRUIT, ItemType.CARROT, ItemType.GOLDEN_CARROT, ItemType.POTATO,
                        ItemType.BAKED_POTATO, ItemType.POISONOUS_POTATO, ItemType.BEETROOT, ItemType.DRIED_KELP, ItemType.BREAD,
                        ItemType.COOKIE, ItemType.CAKE, ItemType.PUMPKIN_PIE, ItemType.MUSHROOM_STEW, ItemType.BEETROOT_SOUP,
                        ItemType.SUSPICIOUS_STEW
                }
        );

        String playerDiet = slugcatDiets.get(playerSlugcat);
        Player player = event.getPlayer();
        ItemType consumedItem = Objects.requireNonNull(event.getItem()).getType().asItemType();
        boolean fail = false;
        if (!Objects.equals(playerDiet, "all")) {
            if (Objects.equals(playerDiet, "meat") && Arrays.stream(dietTypes.get("plant")).toList().contains(consumedItem)) {
                fail = true;
                event.setCancelled(true);
            }
            if (Objects.equals(playerDiet, "plant") && Arrays.stream(dietTypes.get("meat")).toList().contains(consumedItem)) {
                fail = true;
                event.setCancelled(true);
            }
        }
        if (fail) {
            player.playSound(player, Sound.ITEM_SHIELD_BREAK, 1f, 1f);
        }
    }

}
