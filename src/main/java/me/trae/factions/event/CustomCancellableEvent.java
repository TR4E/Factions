package me.trae.factions.event;

import org.bukkit.event.Cancellable;

public class CustomCancellableEvent extends CustomEvent implements Cancellable {

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}