package me.trae.factions.faction.commands.subcommands.abstracts;

import me.trae.factions.client.Client;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.FactionManager;
import me.trae.factions.faction.commands.subcommands.abstracts.interfaces.IFactionSubCommand;
import me.trae.factions.faction.data.enums.MemberRole;
import me.trae.factions.framework.SpigotModule;
import me.trae.factions.utility.UtilMessage;
import org.bukkit.entity.Player;

public abstract class FactionSubCommand extends SpigotModule<FactionManager> implements IFactionSubCommand {

    public FactionSubCommand(final FactionManager manager) {
        super(manager);
    }

    @Override
    public String getLabel() {
        return this.getClass().getSimpleName().replace("Command", "").toLowerCase();
    }

    @Override
    public String getUsage() {
        return "/f " + this.getLabel();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public MemberRole getRequiredMemberRole() {
        return null;
    }

    @Override
    public boolean hasRequiredMemberRole(final Player player, final Client client, final Faction faction, final MemberRole memberRole, final boolean inform) {
        if (memberRole == null) {
            return true;
        }

        if (client.isAdministrating()) {
            return true;
        }

        if (faction.isMemberByPlayer(player) && faction.getMemberByPlayer(player).hasRole(memberRole)) {
            return true;
        }

        if (inform) {
            UtilMessage.message(player, "Factions", "You must be Faction " + memberRole.getName() + " to " + this.getDescription() + "!");
        }

        return false;
    }

    @Override
    public boolean hasRequiredMemberRole(final Player player, final Client client, final Faction faction, final boolean inform) {
        return this.hasRequiredMemberRole(player, client, faction, this.getRequiredMemberRole(), inform);
    }

    @Override
    public void Execute(final Player player, final Client client, final Faction faction, final String[] args) {
        if (!(this.hasRequiredMemberRole(player, client, faction, true))) {
            return;
        }

        this.execute(player, client, faction, args);
    }
}