package me.trae.factions.utility;

import org.bukkit.entity.Player;

public class UtilPlayer {

    public static boolean hasPermission(final Player player, final String permission) {
        return player.isOp() || player.hasPermission(permission);
    }
}