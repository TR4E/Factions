package me.trae.factions.faction.data.enums;

import me.trae.factions.faction.data.enums.interfaces.IMemberRole;

public enum MemberRole implements IMemberRole {

    RECRUIT, MEMBER, ADMIN, LEADER;

    private final String name;

    MemberRole() {
        this.name = this.name();
    }

    @Override
    public String getName() {
        return this.name;
    }
}