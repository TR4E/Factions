package me.trae.factions.client.enums.interfaces;

import org.bukkit.ChatColor;

public interface IRank {

    String getName();

    ChatColor getChatColor();

    String getTag(final boolean bold);

    String getTag();

    String getPrefix();
}