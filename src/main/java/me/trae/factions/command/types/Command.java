package me.trae.factions.command.types;

import me.trae.factions.client.enums.Rank;
import me.trae.factions.command.abstracts.AbstractCommand;
import me.trae.factions.framework.SpigotManager;
import org.bukkit.command.CommandSender;

public abstract class Command<M extends SpigotManager> extends AbstractCommand<M, CommandSender> {

    public Command(final M manager, final String label, final String[] aliases, final Rank requiredRank) {
        super(manager, label, aliases, requiredRank);
    }

    @Override
    public Class<CommandSender> getClassOfCommandSender() {
        return CommandSender.class;
    }
}