package me.trae.factions.command.types.subcommand.interfaces;

import me.trae.factions.account.Account;
import org.bukkit.entity.Player;

public interface IPlayerSubCommand {

    void execute(final Player player, final Account account, final String[] args);
}