package me.trae.factions.client;

import me.trae.factions.client.commands.ClientCommand;
import me.trae.factions.client.enums.Rank;
import me.trae.factions.client.interfaces.IClientManager;
import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotPlugin;
import me.trae.factions.utility.UtilMessage;
import me.trae.factions.utility.UtilServer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class ClientManager extends SpigotManager implements IClientManager {

    private final Map<UUID, Client> CLIENTS = new HashMap<>();

    public ClientManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
        addModule(new ClientCommand(this));
    }

    @Override
    public Map<UUID, Client> getClients() {
        return this.CLIENTS;
    }

    @Override
    public void addClient(final Client client) {
        this.getClients().put(client.getUUID(), client);
    }

    @Override
    public void removeClient(final Client client) {
        this.getClients().remove(client.getUUID());
    }

    @Override
    public Client getClientByUUID(final UUID uuid) {
        return this.getClients().getOrDefault(uuid, null);
    }

    @Override
    public Client getClientByPlayer(final Player player) {
        return this.getClientByUUID(player.getUniqueId());
    }

    @Override
    public Client searchClient(final CommandSender sender, final String name, final boolean inform) {
        return null;
    }

    @Override
    public Map<Player, Client> getOnlineClients(final Predicate<Client> predicate) {
        final Map<Player, Client> map = new HashMap<>();

        for (final Player player : UtilServer.getOnlinePlayers()) {
            final Client client = this.getClientByPlayer(player);
            if (client == null) {
                continue;
            }

            map.put(player, client);
        }

        return map;
    }

    @Override
    public Map<Player, Client> getOnlineClients() {
        return this.getOnlineClients(null);
    }

    @Override
    public void messageStaff(final String prefix, final String message, final Rank requiredRank, final List<UUID> ignore) {
        UtilMessage.message(UtilServer.getOnlinePlayers(), prefix, message, player -> this.getClientByPlayer(player).hasRank(requiredRank), ignore);
    }

    @Override
    public void messageAdmins(final String prefix, final String message, final List<UUID> ignore) {
        UtilMessage.message(UtilServer.getOnlinePlayers(), prefix, message, player -> this.getClientByPlayer(player).isAdministrating(), ignore);
    }
}