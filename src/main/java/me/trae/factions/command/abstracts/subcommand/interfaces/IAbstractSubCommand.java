package me.trae.factions.command.abstracts.subcommand.interfaces;

import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.command.abstracts.interfaces.ICommandInfo;
import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotPlugin;
import org.bukkit.command.CommandSender;

public interface IAbstractSubCommand<M extends SpigotManager, CS extends CommandSender> extends ICommandInfo {

    AbstractCommand<M, ?> getCommand();

    M getManager();

    SpigotPlugin getInstance();

    Class<CS> getClassOfCommandSender();

    String getLabel();

    String getPermission();

    void Execute(final CommandSender sender, final String[] args);

    void execute(final CS cs, final String[] args);
}