package me.trae.factions.faction.data;

import me.trae.factions.faction.Faction;
import me.trae.factions.faction.data.interfaces.IPillage;

public class Pillage implements IPillage {

    private final String name;
    private final long systemTime;

    public Pillage(final String name, final long systemTime) {
        this.name = name;
        this.systemTime = systemTime;
    }

    public Pillage(final Faction faction) {
        this(faction.getName(), System.currentTimeMillis());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getSystemTime() {
        return this.systemTime;
    }
}