package me.trae.factions.command.types.subcommand;

import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.abstracts.subcommand.AbstractSubCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.command.ConsoleCommandSender;

public abstract class ServerSubCommand<M extends SpigotManager> extends AbstractSubCommand<M, ConsoleCommandSender> {

    public ServerSubCommand(final AbstractCommand<M, ?> command, final String label, final String permission) {
        super(command, label, permission);
    }

    @Override
    public Class<ConsoleCommandSender> getClassOfCommandSender() {
        return ConsoleCommandSender.class;
    }
}