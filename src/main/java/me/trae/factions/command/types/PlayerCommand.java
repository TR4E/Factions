package me.trae.factions.command.types;

import me.trae.factions.account.Account;
import me.trae.factions.account.AccountManager;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.types.interfaces.IPlayerCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class PlayerCommand<M extends SpigotManager> extends AbstractCommand<M, Player> implements IPlayerCommand {

    public PlayerCommand(final M manager, final String label, final String[] aliases, final String permission) {
        super(manager, label, aliases, permission);
    }

    @Override
    public Class<Player> getClassOfCommandSender() {
        return Player.class;
    }

    @Override
    public void execute(final Player player, final String[] args) {
        this.execute(player, this.getInstance().getManagerByClass(AccountManager.class).getAccountByPlayer(player), args);
    }

    @Override
    public List<String> onTabCompletion(final Player player, final String[] args) {
        return this.onTabCompletion(player, this.getInstance().getManagerByClass(AccountManager.class).getAccountByPlayer(player), args);
    }

    @Override
    public List<String> onTabCompletion(final Player player, final Account account, final String[] args) {
        return null;
    }
}