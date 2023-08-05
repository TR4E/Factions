package me.trae.factions.faction.commands;

import me.trae.factions.account.Account;
import me.trae.factions.command.types.PlayerCommand;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.FactionManager;
import me.trae.factions.faction.enums.FactionRelation;
import me.trae.factions.utility.UtilMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FactionCommand extends PlayerCommand<FactionManager> {

    public FactionCommand(final FactionManager manager) {
        super(manager, "faction", new String[]{"fac", "f"}, null);
    }

    @Override
    public void execute(final Player player, final Account account, final String[] args) {
        final Faction playerFaction = this.getManager().getFactionByPlayer(player);

        if (args == null || args.length == 0) {
            if (playerFaction == null) {
                UtilMessage.message(player, "Factions", "You are not in a Faction.");
                return;
            }

            this.handleFactionInformation(player, account, playerFaction, playerFaction);
            return;
        }

        if (args.length == 1) {
            final Faction targetFaction = this.getManager().searchFaction(player, args[0], true);
            if (targetFaction == null) {
                return;
            }

            if (!(account.isAdministrating()) && playerFaction != targetFaction && targetFaction.isAdmin()) {
                UtilMessage.message(player, "Factions", "You cannot view Admin Factions!");
                return;
            }

            this.handleFactionInformation(player, account, playerFaction, targetFaction);
        }
    }

    private void handleFactionInformation(final Player player, final Account account, final Faction playerFaction, final Faction targetFaction) {
        final FactionRelation factionRelation = this.getManager().getFactionRelationByFaction(playerFaction, targetFaction);

        UtilMessage.message(player, "Factions", factionRelation.getSuffix() + targetFaction.getName() + ChatColor.GRAY + " Information:");
    }
}