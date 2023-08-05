package me.trae.factions.account.interfaces;

import me.trae.factions.account.Account;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public interface IAccountManager {

    Map<UUID, Account> getAccounts();

    void addAccount(final Account account);

    void removeAccount(final Account account);

    Account getAccountByUUID(final UUID uuid);

    Account getAccountByPlayer(final Player player);

    Map<Player, Account> getOnlineAccounts(final Predicate<Account> predicate);

    Map<Player, Account> getOnlineAccounts();
}