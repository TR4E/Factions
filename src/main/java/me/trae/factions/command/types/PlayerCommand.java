package me.trae.factions.command.types;

import me.trae.factions.client.Client;
import me.trae.factions.client.ClientManager;
import me.trae.factions.client.enums.Rank;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.types.interfaces.IPlayerCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class PlayerCommand<M extends SpigotManager> extends AbstractCommand<M, Player> implements IPlayerCommand {

    public PlayerCommand(final M manager, final String label, final String[] aliases, final Rank requiredRank) {
        super(manager, label, aliases, requiredRank);
    }

    @Override
    public Class<Player> getClassOfCommandSender() {
        return Player.class;
    }

    @Override
    public void execute(final Player player, final String[] args) {
        this.execute(player, this.getInstance().getManagerByClass(ClientManager.class).getClientByPlayer(player), args);
    }

    @Override
    public List<String> onTabCompletion(final Player player, final String[] args) {
        return this.onTabCompletion(player, this.getInstance().getManagerByClass(ClientManager.class).getClientByPlayer(player), args);
    }

    @Override
    public List<String> onTabCompletion(final Player player, final Client client, final String[] args) {
        return null;
    }
}