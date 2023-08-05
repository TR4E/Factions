package me.trae.factions.command.types.subcommand.interfaces;

import me.trae.factions.client.Client;
import org.bukkit.entity.Player;

public interface IPlayerSubCommand {

    void execute(final Player player, final Client client, final String[] args);
}