package me.trae.factions.account.interfaces;

import java.util.UUID;

public interface IAccount {

    UUID getUUID();

    String getName();

    void setName(final String name);

    long getFirstJoined();

    boolean isAdministrating();

    void setAdministrating(final boolean administrating);
}