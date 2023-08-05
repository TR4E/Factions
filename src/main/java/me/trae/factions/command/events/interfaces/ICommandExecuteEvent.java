package me.trae.factions.command.events.interfaces;

import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.event.interfaces.ISenderEvent;

public interface ICommandExecuteEvent extends ISenderEvent {

    AbstractCommand<?, ?> getCommand();

    String[] getArguments();
}