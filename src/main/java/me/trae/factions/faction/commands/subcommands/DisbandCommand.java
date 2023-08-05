package me.trae.factions.faction.commands.subcommands;

import me.trae.factions.account.Account;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.FactionManager;
import me.trae.factions.faction.commands.subcommands.abstracts.FactionSubCommand;
import me.trae.factions.faction.enums.FactionRelation;
import me.trae.factions.faction.events.FactionDisbandEvent;
import me.trae.factions.framework.interfaces.containers.EventContainer;
import me.trae.factions.utility.UtilMessage;
import me.trae.factions.utility.UtilServer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DisbandCommand extends FactionSubCommand implements EventContainer<FactionDisbandEvent> {

    public DisbandCommand(final FactionManager manager) {
        super(manager);
    }

    @Override
    public String getDescription() {
        return "Disband the Faction";
    }

    @Override
    public void execute(final Player player, final Account account, final Faction faction, final String[] args) {
        if (faction == null) {
            UtilMessage.message(player, "Factions", "You are not in a Faction.");
            return;
        }

        if (!(this.canDisbandFaction(player, account, faction))) {
            return;
        }

        this.callEvent(new FactionDisbandEvent(faction, player));
    }

    private boolean canDisbandFaction(final Player player, final Account account, final Faction faction) {
        return true;
    }

    @Override
    public Class<FactionDisbandEvent> getClassOfEvent() {
        return FactionDisbandEvent.class;
    }

    @Override
    public void onEvent(final FactionDisbandEvent event) {
        if (event.isCancelled()) {
            return;
        }

        final Faction faction = event.getFaction();
        final Player player = event.getPlayer();

        this.getManager().removeFaction(faction);

        for (final Player target : UtilServer.getOnlinePlayers()) {
            final FactionRelation factionRelation = this.getManager().getFactionRelationByFaction(this.getManager().getFactionByPlayer(target), faction);

            UtilMessage.message(target, "Factions", factionRelation.getSuffix() + player.getName() + ChatColor.GRAY + " has disbanded " + this.getManager().getFactionFullName(faction, factionRelation) + ChatColor.GRAY + ".");
        }
    }
}