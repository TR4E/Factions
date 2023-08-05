package me.trae.factions.command.interfaces;

import me.trae.factions.command.abstracts.AbstractCommand;

import java.util.Map;

public interface ICommandManager {

    Map<String, AbstractCommand<?, ?>> getCommands();

    void addCommand(final AbstractCommand<?, ?> command);

    void removeCommand(final AbstractCommand<?, ?> command);

    AbstractCommand<?, ?> getCommand(final String string);

    boolean isCommand(final String string);
}