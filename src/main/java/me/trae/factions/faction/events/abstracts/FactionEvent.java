package me.trae.factions.faction.events.abstracts;

import me.trae.factions.event.CustomEvent;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.events.abstracts.interfaces.IFactionEvent;

public class FactionEvent extends CustomEvent implements IFactionEvent {

    private final Faction faction;

    public FactionEvent(final Faction faction) {
        this.faction = faction;
    }

    @Override
    public Faction getFaction() {
        return this.faction;
    }
}