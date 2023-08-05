package me.trae.factions.command.events;

import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.events.interfaces.ICommandExecuteEvent;
import me.trae.factions.event.CustomCancellableEvent;
import org.bukkit.command.CommandSender;

public class CommandExecuteEvent extends CustomCancellableEvent implements ICommandExecuteEvent {

    private final AbstractCommand<?, ?> command;
    private final String[] arguments;
    private final CommandSender sender;

    public CommandExecuteEvent(final AbstractCommand<?, ?> command, final String[] arguments, final CommandSender sender) {
        this.command = command;
        this.arguments = arguments;
        this.sender = sender;
    }

    @Override
    public AbstractCommand<?, ?> getCommand() {
        return this.command;
    }

    @Override
    public String[] getArguments() {
        return this.arguments;
    }

    @Override
    public CommandSender getSender() {
        return this.sender;
    }
}