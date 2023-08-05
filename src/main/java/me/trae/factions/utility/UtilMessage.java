package me.trae.factions.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class UtilMessage {

    private static String getPrefix(final ChatColor chatColor, final String prefix) {
        if (prefix == null) {
            return "";
        }

        return chatColor + (prefix + ">") + " " + ChatColor.GRAY;
    }

    public static void message(final CommandSender sender, final String prefix, final String message) {
        sender.sendMessage(getPrefix(ChatColor.BLUE, prefix) + message);
    }

    public static void message(final CommandSender sender, final String message) {
        message(sender, null, message);
    }

    public static void message(final List<Player> players, final String prefix, final String message, final Predicate<Player> predicate, final List<UUID> ignore) {
        for (final Player player : players) {
            if (ignore != null && ignore.contains(player.getUniqueId())) {
                continue;
            }

            if (predicate != null && !(predicate.test(player))) {
                continue;
            }

            message(player, prefix, message);
        }
    }

    public static void broadcast(final String prefix, final String message, final List<UUID> ignore) {
        message(UtilServer.getOnlinePlayers(), prefix, message, null, ignore);
        log(prefix, message);
    }

    public static void broadcast(final String message, final List<UUID> ignore) {
        broadcast(null, message, ignore);
    }

    public static void broadcast(final String prefix, final String message) {
        broadcast(prefix, message, null);
    }

    public static void broadcast(final String message) {
        broadcast(null, message, null);
    }

    public static void log(final String prefix, final String message) {
        message(Bukkit.getServer().getConsoleSender(), prefix, message);
    }

    public static void log(final String message) {
        log(null, message);
    }

    public static void usageMessage(final CommandSender sender, final String usage, final String description, final ChatColor chatColor) {
        message(sender, chatColor + usage + ChatColor.WHITE + " * " + ChatColor.GRAY + description);
    }
}