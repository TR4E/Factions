package me.trae.factions.utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UtilServer {

    public static void callEvent(final Event event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public static List<Player> getOnlinePlayers(final Predicate<Player> predicate) {
        final List<Player> list = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());

        if (predicate != null) {
            list.removeIf(player -> !(predicate.test(player)));
        }

        return list;
    }

    public static List<Player> getOnlinePlayers() {
        return getOnlinePlayers(null);
    }
}