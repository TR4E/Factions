package me.trae.factions.faction;

import me.trae.factions.faction.interfaces.IAdminFaction;

public class AdminFaction extends Faction implements IAdminFaction {

    private boolean safe;

    public AdminFaction(final String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Admin Faction";
    }

    @Override
    public boolean isSafe() {
        return this.safe;
    }

    @Override
    public void setSafe(final boolean safe) {
        this.safe = safe;
    }
}