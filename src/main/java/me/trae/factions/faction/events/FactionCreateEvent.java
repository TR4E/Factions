package me.trae.factions.faction.events;

import me.trae.factions.event.interfaces.IPlayerEvent;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.events.abstracts.FactionCancellableEvent;
import org.bukkit.entity.Player;

public class FactionCreateEvent extends FactionCancellableEvent implements IPlayerEvent {

    private final Player player;

    public FactionCreateEvent(final Faction faction, final Player player) {
        super(faction);

        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}