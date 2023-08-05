package me.trae.factions.faction.events.abstracts;

import me.trae.factions.event.CustomCancellableEvent;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.events.abstracts.interfaces.IFactionEvent;

public class FactionCancellableEvent extends CustomCancellableEvent implements IFactionEvent {

    private final Faction faction;

    public FactionCancellableEvent(final Faction faction) {
        this.faction = faction;
    }

    @Override
    public Faction getFaction() {
        return this.faction;
    }
}