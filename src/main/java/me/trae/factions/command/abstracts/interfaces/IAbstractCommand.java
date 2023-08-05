package me.trae.factions.command.abstracts.interfaces;

import me.trae.factions.command.abstracts.subcommand.AbstractSubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;

public interface IAbstractCommand<CS extends CommandSender> extends ICommandInfo {

    Class<CS> getClassOfCommandSender();

    String getLabel();

    List<String> getAliases();

    String getPermission();

    void registerSubCommands();

    Map<String, AbstractSubCommand<?, ?>> getSubCommands();

    void addSubCommand(final AbstractSubCommand<?, ?> subCommand);

    boolean hasSubCommands();

    boolean isValidSender(final CommandSender sender, final Class<? extends CommandSender> clazz, final boolean inform);

    boolean isValidSender(final CommandSender sender, final boolean inform);

    boolean hasPermission(final CommandSender sender, final String permission, final boolean inform);

    boolean hasPermission(final CommandSender sender, final boolean inform);

    void execute(final CS cs, final String[] args);

    void help(final CS cs);

    List<String> onTabCompletion(final CS cs, final String[] args);
}