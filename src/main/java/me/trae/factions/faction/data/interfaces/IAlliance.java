package me.trae.factions.faction.data.interfaces;

public interface IAlliance {

    String getName();

    boolean isTrusted();

    void setTrusted(final boolean trusted);
}