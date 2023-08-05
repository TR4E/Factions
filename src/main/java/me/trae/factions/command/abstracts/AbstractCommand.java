package me.trae.factions.command.abstracts;

import me.trae.factions.command.abstracts.interfaces.IAbstractCommand;
import me.trae.factions.command.abstracts.subcommand.AbstractSubCommand;
import me.trae.factions.command.events.CommandExecuteEvent;
import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotModule;
import me.trae.factions.utility.UtilJava;
import me.trae.factions.utility.UtilMessage;
import me.trae.factions.utility.UtilPlayer;
import me.trae.factions.utility.UtilServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommand<M extends SpigotManager, CS extends CommandSender> extends SpigotModule<M> implements IAbstractCommand<CS> {

    private final String label;
    private final List<String> aliases;
    private final String permission;
    private final Map<String, AbstractSubCommand<?, ?>> subCommands;

    public AbstractCommand(final M manager, final String label, final String[] aliases, final String permission) {
        super(manager);

        this.label = label;
        this.aliases = Arrays.asList(aliases);
        this.permission = permission;
        this.subCommands = new HashMap<>();
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public String getPermission() {
        return this.permission;
    }

    @Override
    public void registerSubCommands() {
    }

    @Override
    public Map<String, AbstractSubCommand<?, ?>> getSubCommands() {
        return this.subCommands;
    }

    @Override
    public void addSubCommand(final AbstractSubCommand<?, ?> subCommand) {
        this.getSubCommands().put(subCommand.getLabel(), subCommand);
    }

    @Override
    public boolean hasSubCommands() {
        return !(this.getSubCommands().isEmpty());
    }

    @Override
    public boolean isValidSender(final CommandSender sender, final Class<? extends CommandSender> clazz, final boolean inform) {
        if (clazz.isInstance(sender)) {
            return true;
        }

        if (inform) {
            UtilMessage.message(sender, "Command", "Invalid Command Sender");
        }

        return false;
    }

    @Override
    public boolean isValidSender(final CommandSender sender, final boolean inform) {
        return this.isValidSender(sender, this.getClassOfCommandSender(), inform);
    }

    @Override
    public boolean hasPermission(final CommandSender sender, final String permission, final boolean inform) {
        if (!(sender instanceof Player) || UtilPlayer.hasPermission(UtilJava.cast(Player.class, sender), permission)) {
            return true;
        }

        if (inform) {
            UtilMessage.message(sender, "Permissions", "You do not have permission to use this command!");
        }

        return false;
    }

    @Override
    public boolean hasPermission(final CommandSender sender, final boolean inform) {
        return this.hasPermission(sender, this.getPermission(), inform);
    }

    @Override
    public List<String> onTabCompletion(final CS cs, final String[] args) {
        return null;
    }

    @Override
    public String getUsage() {
        return "/" + this.getLabel();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(final CS cs, final String[] args) {
        final AbstractSubCommand<?, ?> subCommand = this.getSubCommands().getOrDefault(args[0].toLowerCase(), null);
        if (subCommand == null) {
            this.help(cs);
            return;
        }

        final CommandSender sender = UtilJava.cast(CommandSender.class, cs);

        if (!(this.isValidSender(sender, subCommand.getClassOfCommandSender(), true))) {
            return;
        }

        if (!(this.hasPermission(sender, subCommand.getPermission(), true))) {
            return;
        }

        subCommand.Execute(sender, args);
    }

    @Override
    public void help(final CS cs) {
        if (!(this.hasSubCommands())) {
            return;
        }

        final CommandSender sender = UtilJava.cast(CommandSender.class, cs);

        UtilMessage.message(sender, this.getManager().getName().split(" ")[0], "Help Information:");

        for (final AbstractSubCommand<?, ?> subCommand : this.getSubCommands().values()) {
            if (subCommand.getDescription() == null) {
                continue;
            }

            UtilMessage.usageMessage(sender, subCommand.getUsage(), subCommand.getDescription(), ChatColor.YELLOW);
        }
    }

    @Override
    public void onInitialize() {
        Bukkit.getServer().getCommandMap().register(this.getLabel(), this.getInstance().getPluginName(), new Command(this.getLabel()) {
            @Override
            public boolean execute(final CommandSender sender, final String label, final String[] args) {
                if (!(AbstractCommand.this.isValidSender(sender, true))) {
                    return false;
                }

                if (!(AbstractCommand.this.hasPermission(sender, true))) {
                    return false;
                }

                final CommandExecuteEvent event = new CommandExecuteEvent(AbstractCommand.this, args, sender);
                UtilServer.callEvent(event);
                if (event.isCancelled()) {
                    return false;
                }

                AbstractCommand.this.execute(UtilJava.cast(AbstractCommand.this.getClassOfCommandSender(), sender), args);
                return true;
            }

            @Override
            public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) throws IllegalArgumentException {
                List<String> list = AbstractCommand.this.onTabCompletion(UtilJava.cast(AbstractCommand.this.getClassOfCommandSender(), sender), args);
                if (list == null) {
                    list = super.tabComplete(sender, alias, args);
                }

                return list;
            }
        });
    }

    @Override
    public void onShutdown() {
        Bukkit.getServer().getCommandMap().unregisterAll(command -> command.getLabel().equals(this.getLabel()));
    }
}