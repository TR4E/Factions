package me.trae.factions.command;

import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.interfaces.ICommandManager;
import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotPlugin;

import java.util.HashMap;
import java.util.Map;

public class CommandManager extends SpigotManager implements ICommandManager {

    private final Map<String, AbstractCommand<?, ?>> COMMANDS = new HashMap<>();

    public CommandManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
    }

    @Override
    public Map<String, AbstractCommand<?, ?>> getCommands() {
        return this.COMMANDS;
    }

    @Override
    public void addCommand(final AbstractCommand<?, ?> command) {
        this.getCommands().put(command.getLabel().toLowerCase(), command);
    }

    @Override
    public void removeCommand(final AbstractCommand<?, ?> command) {
        this.getCommands().remove(command.getLabel().toLowerCase());
    }

    @Override
    public AbstractCommand<?, ?> getCommand(String string) {
        string = string.toLowerCase();

        if (this.getCommands().containsKey(string)) {
            return this.getCommands().get(string);
        }

        for (final AbstractCommand<?, ?> command : this.getCommands().values()) {
            if (!(command.getAliases().contains(string))) {
                continue;
            }

            return command;
        }

        return null;
    }

    @Override
    public boolean isCommand(final String string) {
        return this.getCommand(string) != null;
    }
}