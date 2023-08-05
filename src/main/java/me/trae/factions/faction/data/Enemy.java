package me.trae.factions.faction.data;

import me.trae.factions.faction.data.interfaces.IEnemy;

public class Enemy implements IEnemy {

    private final String name;

    private int dominancePoints;

    public Enemy(final String name, final int dominancePoints) {
        this.name = name;
        this.dominancePoints = dominancePoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDominancePoints() {
        return this.dominancePoints;
    }

    @Override
    public void setDominancePoints(final int dominancePoints) {
        this.dominancePoints = dominancePoints;
    }
}