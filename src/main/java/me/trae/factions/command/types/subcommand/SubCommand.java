package me.trae.factions.command.types.subcommand;

import me.trae.factions.client.enums.Rank;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.abstracts.subcommand.AbstractSubCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.command.CommandSender;

public abstract class SubCommand<M extends SpigotManager> extends AbstractSubCommand<M, CommandSender> {

    public SubCommand(final AbstractCommand<M, ?> command, final String label, final Rank requiredRank) {
        super(command, label, requiredRank);
    }

    public SubCommand(final AbstractCommand<M, ?> command, final String label) {
        super(command, label);
    }

    @Override
    public Class<CommandSender> getClassOfCommandSender() {
        return CommandSender.class;
    }
}