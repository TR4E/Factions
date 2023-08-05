package me.trae.factions.account;

import me.trae.factions.account.interfaces.IAccount;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Account implements IAccount {

    private final UUID uuid;

    private String name;
    private long firstJoined;
    private boolean administrating;

    public Account(final UUID uuid) {
        this.uuid = uuid;
    }

    public Account(final Player player) {
        this(player.getUniqueId());

        this.name = player.getName();
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