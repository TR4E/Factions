package me.trae.factions.client.enums;

import me.trae.factions.client.enums.interfaces.IRank;
import me.trae.factions.utility.UtilFormat;
import org.bukkit.ChatColor;

public enum Rank implements IRank {

    DEFAULT(ChatColor.YELLOW),
    HELPER(ChatColor.DARK_GREEN),
    MOD(ChatColor.AQUA),
    ADMIN(ChatColor.RED),
    OWNER(ChatColor.DARK_RED);

    private final String name;
    private final ChatColor chatColor;

    Rank(final ChatColor chatColor) {
        this.name = UtilFormat.cleanString(this.name());
        this.chatColor = chatColor;
    }

    public static Rank getByOrdinal(final int ordinal) {
        return values()[ordinal - 1];
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ChatColor getChatColor() {
        return this.chatColor;
    }

    @Override
    public String getTag(final boolean bold) {
        return this.getChatColor().toString() + (bold ? ChatColor.BOLD : "") + this.getName().toUpperCase();
    }

    @Override
    public String getTag() {
        return this.getTag(true);
    }

    @Override
    public String getPrefix() {
        if (this == DEFAULT) {
            return "";
        }

        return this.getTag() + " ";
    }
}