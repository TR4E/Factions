package me.trae.factions.client.interfaces;

import me.trae.factions.client.enums.Rank;

import java.util.UUID;

public interface IClient {

    UUID getUUID();

    String getName();

    void setName(final String name);

    Rank getRank();

    void setRank(final Rank rank);

    boolean hasRank(final Rank rank);

    long getFirstJoined();

    boolean isAdministrating();

    void setAdministrating(final boolean administrating);
}