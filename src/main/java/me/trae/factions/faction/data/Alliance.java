package me.trae.factions.faction.data;

import me.trae.factions.faction.Faction;
import me.trae.factions.faction.data.interfaces.IAlliance;

public class Alliance implements IAlliance {

    private final String name;

    private boolean trusted;

    public Alliance(final String name, final boolean trusted) {
        this.name = name;
        this.trusted = trusted;
    }

    public Alliance(final Faction faction) {
        this(faction.getName(), false);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isTrusted() {
        return this.trusted;
    }

    @Override
    public void setTrusted(final boolean trusted) {
        this.trusted = trusted;
    }
}