package me.trae.factions.client.commands;

import me.trae.factions.client.Client;
import me.trae.factions.client.ClientManager;
import me.trae.factions.client.enums.Rank;
import me.trae.factions.command.types.Command;
import me.trae.factions.command.types.subcommand.PlayerSubCommand;
import me.trae.factions.command.types.subcommand.SubCommand;
import me.trae.factions.utility.UtilFormat;
import me.trae.factions.utility.UtilMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ClientCommand extends Command<ClientManager> {

    public ClientCommand(final ClientManager manager) {
        super(manager, "client", new String[0], Rank.ADMIN);
    }

    @Override
    public String getDescription() {
        return "Manage Client Settings";
    }

    @Override
    public void registerSubCommands() {
        this.addSubCommand(this.promoteCommand());
        this.addSubCommand(this.demoteCommand());
        this.addSubCommand(this.adminCommand());
    }

    private SubCommand<ClientManager> promoteCommand() {
        return new SubCommand<ClientManager>(this, "promote", Rank.OWNER) {
            @Override
            public String getUsage() {
                return super.getUsage() + " <client>";
            }

            @Override
            public String getDescription() {
                return "Promote a Client";
            }

            @Override
            public void execute(final CommandSender sender, final String[] args) {
                if (args.length == 1) {
                    UtilMessage.message(sender, "Client", "You did not input a Client to Promote.");
                    return;
                }

                final Client target = this.getManager().searchClient(sender, args[1], true);
                if (target == null) {
                    return;
                }

                target.setRank(Rank.getByOrdinal(target.getRank().ordinal() + 1));

                UtilMessage.broadcast("Client", ChatColor.YELLOW + sender.getName() + ChatColor.GRAY + " promoted " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " to " + target.getRank().getTag() + ChatColor.GRAY + ".");
            }
        };
    }

    private SubCommand<ClientManager> demoteCommand() {
        return new SubCommand<ClientManager>(this, "client", Rank.OWNER) {
            @Override
            public String getUsage() {
                return super.getUsage() + " <client>";
            }

            @Override
            public String getDescription() {
                return "Demote a Client";
            }

            @Override
            public void execute(final CommandSender sender, final String[] args) {
                if (args.length == 1) {
                    UtilMessage.message(sender, "Client", "You did not input a Client to Demote.");
                    return;
                }

                final Client target = this.getManager().searchClient(sender, args[1], true);
                if (target == null) {
                    return;
                }

                target.setRank(Rank.getByOrdinal(target.getRank().ordinal() - 1));

                UtilMessage.broadcast("Client", ChatColor.YELLOW + sender.getName() + ChatColor.GRAY + " demoted " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " to " + target.getRank().getTag() + ChatColor.GRAY + ".");
            }
        };
    }

    private SubCommand<ClientManager> searchCommand() {
        return new SubCommand<ClientManager>(this, "search") {
            @Override
            public String getUsage() {
                return super.getUsage() + " <client>";
            }

            @Override
            public String getDescription() {
                return "Search a Client";
            }

            @Override
            public void execute(final CommandSender sender, final String[] args) {
                if (args.length == 1) {
                    UtilMessage.message(sender, "Client", "You did not input a Client to Demote.");
                    return;
                }

                final Client target = this.getManager().searchClient(sender, args[1], true);
                if (target == null) {
                    return;
                }

                target.setRank(Rank.getByOrdinal(target.getRank().ordinal() - 1));

                UtilMessage.broadcast("Client", ChatColor.YELLOW + sender.getName() + ChatColor.GRAY + " demoted " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " to " + target.getRank().getTag() + ChatColor.GRAY + ".");
            }
        };
    }

    private PlayerSubCommand<ClientManager> adminCommand() {
        return new PlayerSubCommand<ClientManager>(this, "admin") {
            @Override
            public String getDescription() {
                return "Toggle Admin Mode";
            }

            @Override
            public void execute(final Player player, final Client client, final String[] args) {
                if (client.isAdministrating()) {
                    client.setAdministrating(false);

                    UtilMessage.message(player, "Client", UtilFormat.toPairString("Admin Mode", ChatColor.RED + "Disabled"));

                    this.getManager().messageStaff("Client", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " is no longer Administrating.", this.getRequiredRank(), Collections.singletonList(player.getUniqueId()));
                } else {
                    client.setAdministrating(true);

                    UtilMessage.message(player, "Client", UtilFormat.toPairString("Admin Mode", ChatColor.GREEN + "Enabled"));

                    this.getManager().messageStaff("Client", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " is now Administrating.", this.getRequiredRank(), Collections.singletonList(player.getUniqueId()));
                }
            }
        };
    }
}