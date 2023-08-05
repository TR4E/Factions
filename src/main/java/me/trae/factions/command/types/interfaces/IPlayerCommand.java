package me.trae.factions.command.types.interfaces;

import me.trae.factions.account.Account;
import org.bukkit.entity.Player;

import java.util.List;

public interface IPlayerCommand {

    void execute(final Player player, final Account account, final String[] args);

    List<String> onTabCompletion(final Player player, final Account account, final String[] args);
}