package me.trae.factions.faction.commands.subcommands.abstracts.interfaces;

import me.trae.factions.account.Account;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.data.enums.MemberRole;
import org.bukkit.entity.Player;

public interface IFactionSubCommand {

    String getLabel();

    String getUsage();

    String getDescription();

    MemberRole getRequiredMemberRole();

    boolean hasRequiredMemberRole(final Player player, final Account account, final Faction faction, final MemberRole memberRole, final boolean inform);

    boolean hasRequiredMemberRole(final Player player, final Account account, final Faction faction, final boolean inform);

    void Execute(final Player player, final Account account, final Faction faction, final String[] args);

    void execute(final Player player, final Account account, final Faction faction, final String[] args);
}