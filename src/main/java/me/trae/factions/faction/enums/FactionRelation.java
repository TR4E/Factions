package me.trae.factions.faction.enums;

import me.trae.factions.faction.enums.interfaces.IFactionRelation;
import org.bukkit.ChatColor;

public enum FactionRelation implements IFactionRelation {

    NEUTRAL(ChatColor.GOLD, ChatColor.YELLOW),
    SELF(ChatColor.DARK_AQUA, ChatColor.AQUA),
    ALLIANCE(ChatColor.DARK_GREEN, ChatColor.GREEN),
    TRUSTED_ALLIANCE(ChatColor.GREEN, ChatColor.DARK_GREEN),
    ENEMY(ChatColor.DARK_RED, ChatColor.RED),
    PILLAGE(ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE);

    private final ChatColor prefix, suffix;

    FactionRelation(final ChatColor prefix, final ChatColor suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public ChatColor getPrefix() {
        return this.prefix;
    }

    @Override
    public ChatColor getSuffix() {
        return this.suffix;
    }
}