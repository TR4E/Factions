package me.trae.factions.client.interfaces;

import me.trae.factions.client.Client;
import me.trae.factions.client.enums.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public interface IClientManager {

    Map<UUID, Client> getClients();

    void addClient(final Client client);

    void removeClient(final Client client);

    Client getClientByUUID(final UUID uuid);

    Client getClientByPlayer(final Player player);

    Client searchClient(final CommandSender sender, final String name, final boolean inform);

    Map<Player, Client> getOnlineClients(final Predicate<Client> predicate);

    Map<Player, Client> getOnlineClients();

    void messageStaff(final String prefix, final String message, final Rank requiredRank, final List<UUID> ignore);

    void messageAdmins(final String prefix, final String message, final List<UUID> ignore);
}