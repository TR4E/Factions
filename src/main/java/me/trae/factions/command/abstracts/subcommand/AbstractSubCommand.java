package me.trae.factions.command.abstracts.subcommand;

import me.trae.factions.client.enums.Rank;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.abstracts.subcommand.interfaces.IAbstractSubCommand;
import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotPlugin;
import me.trae.factions.utility.UtilJava;
import org.bukkit.command.CommandSender;

public abstract class AbstractSubCommand<M extends SpigotManager, CS extends CommandSender> implements IAbstractSubCommand<M, CS> {

    private final AbstractCommand<M, ?> command;
    private final String label;
    private final Rank requiredRank;

    public AbstractSubCommand(final AbstractCommand<M, ?> command, final String label, final Rank requiredRank) {
        this.command = command;
        this.label = label;
        this.requiredRank = requiredRank;
    }

    public AbstractSubCommand(final AbstractCommand<M, ?> command, final String label) {
        this(command, label, command.getRequiredRank());
    }

    @Override
    public AbstractCommand<M, ?> getCommand() {
        return this.command;
    }

    @Override
    public M getManager() {
        return this.getCommand().getManager();
    }

    @Override
    public SpigotPlugin getInstance() {
        return this.getManager().getInstance();
    }

    @Override
    public void Execute(final CommandSender sender, final String[] args) {
        this.execute(UtilJava.cast(this.getClassOfCommandSender(), sender), args);
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public Rank getRequiredRank() {
        return this.requiredRank;
    }

    @Override
    public String getUsage() {
        return "/" + this.getCommand().getLabel() + " " + this.getLabel();
    }

    @Override
    public String getDescription() {
        return null;
    }
}