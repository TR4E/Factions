package me.trae.factions.command.types;

import me.trae.factions.client.enums.Rank;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.command.ConsoleCommandSender;

public abstract class ServerCommand<M extends SpigotManager> extends AbstractCommand<M, ConsoleCommandSender> {

    public ServerCommand(final M manager, final String label, final String[] aliases) {
        super(manager, label, aliases, Rank.OWNER);
    }

    @Override
    public Class<ConsoleCommandSender> getClassOfCommandSender() {
        return ConsoleCommandSender.class;
    }
}