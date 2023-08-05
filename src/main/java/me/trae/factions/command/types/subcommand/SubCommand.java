package me.trae.factions.command.types.subcommand;

import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.abstracts.subcommand.AbstractSubCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.command.CommandSender;

public abstract class SubCommand<M extends SpigotManager> extends AbstractSubCommand<M, CommandSender> {

    public SubCommand(final AbstractCommand<M, ?> command, final String label, final String permission) {
        super(command, label, permission);
    }

    @Override
    public Class<CommandSender> getClassOfCommandSender() {
        return CommandSender.class;
    }
}