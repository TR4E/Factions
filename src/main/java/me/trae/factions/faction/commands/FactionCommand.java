package me.trae.factions.faction.commands;

import me.trae.factions.client.Client;
import me.trae.factions.command.types.PlayerCommand;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.FactionManager;
import me.trae.factions.faction.enums.FactionRelation;
import me.trae.factions.utility.UtilFormat;
import me.trae.factions.utility.UtilMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class FactionCommand extends PlayerCommand<FactionManager> {

    public FactionCommand(final FactionManager manager) {
        super(manager, "faction", new String[]{"fac", "f"}, null);
    }

    @Override
    public void execute(final Player player, final Client client, final String[] args) {
        final Faction playerFaction = this.getManager().getFactionByPlayer(player);

        if (args == null || args.length == 0) {
            if (playerFaction == null) {
                UtilMessage.message(player, "Factions", "You are not in a Faction.");
                return;
            }

            this.handleFactionInformation(player, client, playerFaction, playerFaction);
            return;
        }

        if (args.length == 1) {
            final Faction targetFaction = this.getManager().searchFaction(player, args[0], true);
            if (targetFaction == null) {
                return;
            }

            if (!(client.isAdministrating()) && playerFaction != targetFaction && targetFaction.isAdmin()) {
                UtilMessage.message(player, "Factions", "You cannot view Admin Factions!");
                return;
            }

            this.handleFactionInformation(player, client, playerFaction, targetFaction);
        }
    }

    private void handleFactionInformation(final Player player, final Client client, final Faction playerFaction, final Faction targetFaction) {
        final FactionRelation factionRelation = this.getManager().getFactionRelationByFaction(playerFaction, targetFaction);

        UtilMessage.message(player, "Factions", factionRelation.getSuffix() + targetFaction.getName() + ChatColor.GRAY + " Information:");

        for (final Map.Entry<String, String> entry : this.getManager().getFactionInformation(player, client, playerFaction, targetFaction).entrySet()) {
            UtilMessage.message(player, UtilFormat.toPairString(entry.getKey(), entry.getValue()));
        }
    }
}