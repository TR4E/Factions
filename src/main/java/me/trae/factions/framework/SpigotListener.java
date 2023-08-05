package me.trae.factions.framework;

import org.bukkit.event.Listener;

public class SpigotListener<M extends SpigotManager> extends SpigotModule<M> implements Listener {

    public SpigotListener(final M manager) {
        super(manager);
    }
}