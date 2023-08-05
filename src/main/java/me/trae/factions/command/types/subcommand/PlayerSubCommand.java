package me.trae.factions.command.types.subcommand;

import me.trae.factions.client.ClientManager;
import me.trae.factions.client.enums.Rank;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.abstracts.subcommand.AbstractSubCommand;
import me.trae.factions.command.types.subcommand.interfaces.IPlayerSubCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.entity.Player;

public abstract class PlayerSubCommand<M extends SpigotManager> extends AbstractSubCommand<M, Player> implements IPlayerSubCommand {

    public PlayerSubCommand(final AbstractCommand<M, ?> command, final String label, final Rank requiredRank) {
        super(command, label, requiredRank);
    }

    public PlayerSubCommand(final AbstractCommand<M, ?> command, final String label) {
        super(command, label);
    }

    @Override
    public Class<Player> getClassOfCommandSender() {
        return Player.class;
    }

    @Override
    public void execute(final Player player, final String[] args) {
        this.execute(player, this.getInstance().getManagerByClass(ClientManager.class).getClientByPlayer(player), args);
    }
}