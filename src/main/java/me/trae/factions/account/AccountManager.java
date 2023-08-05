package me.trae.factions.account;

import me.trae.factions.account.interfaces.IAccountManager;
import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotPlugin;
import me.trae.factions.utility.UtilServer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class AccountManager extends SpigotManager implements IAccountManager {

    private final Map<UUID, Account> ACCOUNTS = new HashMap<>();

    public AccountManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
        addModule(new AdminModeCommand(this));
    }

    @Override
    public Map<UUID, Account> getAccounts() {
        return this.ACCOUNTS;
    }

    @Override
    public void addAccount(final Account account) {
        this.getAccounts().put(account.getUUID(), account);
    }

    @Override
    public void removeAccount(final Account account) {
        this.getAccounts().remove(account.getUUID());
    }

    @Override
    public Account getAccountByUUID(final UUID uuid) {
        return this.getAccounts().getOrDefault(uuid, null);
    }

    @Override
    public Account getAccountByPlayer(final Player player) {
        return this.getAccountByUUID(player.getUniqueId());
    }

    @Override
    public Map<Player, Account> getOnlineAccounts(final Predicate<Account> predicate) {
        final Map<Player, Account> map = new HashMap<>();

        for (final Player player : UtilServer.getOnlinePlayers()) {
            final Account account = this.getAccountByPlayer(player);
            if (account == null) {
                continue;
            }

            map.put(player, account);
        }

        return map;
    }

    @Override
    public Map<Player, Account> getOnlineAccounts() {
        return this.getOnlineAccounts(null);
    }
}