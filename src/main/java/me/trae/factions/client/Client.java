package me.trae.factions.client;

import me.trae.factions.client.enums.Rank;
import me.trae.factions.client.interfaces.IClient;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Client implements IClient {

    private final UUID uuid;

    private String name;
    private Rank rank;
    private long firstJoined;
    private boolean administrating;

    public Client(final UUID uuid) {
        this.uuid = uuid;
    }

    public Client(final Player player) {
        this(player.getUniqueId());

        this.name = player.getName();
        this.rank = Rank.DEFAULT;
        this.firstJoined = System.currentTimeMillis();
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public Rank getRank() {
        return this.rank;
    }

    @Override
    public void setRank(final Rank rank) {
        this.rank = rank;
    }

    @Override
    public boolean hasRank(final Rank rank) {
        return this.getRank().ordinal() >= rank.ordinal();
    }

    @Override
    public long getFirstJoined() {
        return this.firstJoined;
    }

    @Override
    public boolean isAdministrating() {
        return this.administrating;
    }

    @Override
    public void setAdministrating(final boolean administrating) {
        this.administrating = administrating;
    }
}