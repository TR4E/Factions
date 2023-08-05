package me.trae.factions.framework.interfaces.containers;

import me.trae.factions.event.CustomEvent;
import me.trae.factions.utility.UtilServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public interface EventContainer<E extends CustomEvent> extends Listener {

    Class<E> getClassOfEvent();

    void onEvent(final E event);

    default void callEvent(final E event) {
        UtilServer.callEvent(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    default void onCustomEvent(final E event) {
        this.onEvent(event);
    }
}