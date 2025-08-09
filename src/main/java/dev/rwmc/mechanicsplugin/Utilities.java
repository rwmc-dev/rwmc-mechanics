package dev.rwmc.mechanicsplugin;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class Utilities {
    public static String getPlayerSlugcat(Player player) {
        String playerSlugcat = player.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("rwmc:slugcat")), PersistentDataType.STRING);
        if (playerSlugcat != null) { return playerSlugcat; } else { return "survivor"; }
    }
    public static int getPlayerMaxFood(Player player) {
        Integer value = player.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("rwmc:max_food")), PersistentDataType.INTEGER);
        if (value != null) { return value; } else { return 0; }
    }
    public static int getPlayerReqFood(Player player) {
        Integer value = player.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("rwmc:req_food")), PersistentDataType.INTEGER);
        if (value != null) { return value; } else { return 0; }
    }
    public static double getPlayerFood(Player player) {
        Double value = player.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("rwmc:food")), PersistentDataType.DOUBLE);
        if (value != null) { return value; } else { return 0; }
    }
    public static boolean getPlayerStarving(Player player) {
        Boolean value = player.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("rwmc:is_starving")), PersistentDataType.BOOLEAN);
        if (value != null) { return value; } else { return false; }
    }
    public static int getPlayerKarma(Player player) {
        Integer value = player.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("rwmc:karma")), PersistentDataType.INTEGER);
        if (value != null) { return value; } else { return 0; }
    }
    public static int getPlayerMaxKarma(Player player) {
        Integer value = player.getPersistentDataContainer().get(Objects.requireNonNull(NamespacedKey.fromString("rwmc:max_karma")), PersistentDataType.INTEGER);
        if (value != null) { return value; } else { return 4; }
    }

    public static void setPlayerMaxFood(Player player, int maxFood) {
        player.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("rwmc:max_food")), PersistentDataType.INTEGER, maxFood);
    }
    public static void setPlayerReqFood(Player player, int reqFood) {
        player.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("rwmc:req_food")), PersistentDataType.INTEGER, reqFood);
    }
    public static void setPlayerFood(Player player, double food) {
        player.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("rwmc:food")), PersistentDataType.DOUBLE, food);
    }
    public static void setPlayerStarving(Player player, boolean isStarving) {
        player.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("rwmc:is_starving")), PersistentDataType.BOOLEAN, isStarving);
    }
    public static void setPlayerKarma(Player player, int karma) {
        int adjKarma = Math.min(getPlayerMaxKarma(player), Math.max(0, karma));
        player.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("rwmc:karma")), PersistentDataType.INTEGER, adjKarma);
    }
    public static void setPlayerMaxKarma(Player player, int maxKarma) {
        player.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("rwmc:max_karma")), PersistentDataType.INTEGER, maxKarma);
    }

}
