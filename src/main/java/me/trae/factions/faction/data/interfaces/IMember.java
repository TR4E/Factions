package me.trae.factions.faction.data.interfaces;

import me.trae.factions.faction.data.enums.MemberRole;

import java.util.UUID;

public interface IMember {

    UUID getUUID();

    MemberRole getRole();

    void setRole(final MemberRole memberRole);

    boolean hasRole(final MemberRole memberRole);
}