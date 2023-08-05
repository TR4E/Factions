package me.trae.factions.faction.commands.subcommands;

import me.trae.factions.account.Account;
import me.trae.factions.faction.AdminFaction;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.FactionManager;
import me.trae.factions.faction.commands.subcommands.abstracts.FactionSubCommand;
import me.trae.factions.faction.enums.FactionRelation;
import me.trae.factions.faction.events.FactionCreateEvent;
import me.trae.factions.framework.interfaces.containers.EventContainer;
import me.trae.factions.utility.UtilMessage;
import me.trae.factions.utility.UtilServer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateCommand extends FactionSubCommand implements EventContainer<FactionCreateEvent> {

    public CreateCommand(final FactionManager manager) {
        super(manager);

        this.addPrimitive("MinNameLength", 3);
        this.addPrimitive("MaxNameLength", 14);
    }

    @Override
    public String getUsage() {
        return super.getUsage() + " <name>";
    }

    @Override
    public String getDescription() {
        return "Create a Faction";
    }

    @Override
    public void execute(final Player player, final Account account, Faction faction, final String[] args) {
        if (faction != null) {
            UtilMessage.message(player, "Factions", "You are already in a Faction.");
            return;
        }

        if (args.length == 1) {
            UtilMessage.message(player, "Factions", "You did not input a Name to Create.");
            return;
        }

        final String name = args[1];

        if (!(this.canCreateFaction(player, account, name))) {
            return;
        }

        faction = (account.isAdministrating() ? new AdminFaction(name) : new Faction(name));

        this.callEvent(new FactionCreateEvent(faction, player));
    }

    private boolean canCreateFaction(final Player player, final Account account, final String name) {
        if (this.getManager().isFactionByName(name)) {
            UtilMessage.message(player, "Factions", "That name is already being used!");
            return false;
        }

        final int maxNameLength = this.getPrimitiveCasted(Integer.class, "MaxNameLength");
        if (name.length() > maxNameLength) {
            UtilMessage.message(player, "Factions", "That name is too long. Maximum Length is " + ChatColor.YELLOW + maxNameLength + ChatColor.GRAY + "!");
            return false;
        }

        if (!(account.isAdministrating())) {
            final int minNameLength = this.getPrimitiveCasted(Integer.class, "MinNameLength");
            if (name.length() < minNameLength) {
                UtilMessage.message(player, "Factions", "That name is too short. Minimum Length is " + ChatColor.YELLOW + minNameLength + ChatColor.GRAY + "!");
                return false;
            }
        }

        return true;
    }

    @Override
    public Class<FactionCreateEvent> getClassOfEvent() {
        return FactionCreateEvent.class;
    }

    @Override
    public void onEvent(final FactionCreateEvent event) {
        if (event.isCancelled()) {
            return;
        }

        final Faction faction = event.getFaction();
        final Player player = event.getPlayer();

        this.getManager().addFaction(faction);

        for (final Player target : UtilServer.getOnlinePlayers()) {
            final FactionRelation factionRelation = this.getManager().getFactionRelationByFaction(this.getManager().getFactionByPlayer(target), faction);

            UtilMessage.message(target, "Factions", factionRelation.getSuffix() + player.getName() + ChatColor.GRAY + " formed " + this.getManager().getFactionFullName(faction, factionRelation) + ChatColor.GRAY + ".");
        }
    }
}