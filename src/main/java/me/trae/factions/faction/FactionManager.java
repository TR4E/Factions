package me.trae.factions.faction;

import me.trae.factions.faction.commands.FactionCommand;
import me.trae.factions.faction.commands.subcommands.CreateCommand;
import me.trae.factions.faction.commands.subcommands.DisbandCommand;
import me.trae.factions.faction.enums.FactionRelation;
import me.trae.factions.faction.interfaces.IFactionManager;
import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotPlugin;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactionManager extends SpigotManager implements IFactionManager {

    private final Map<String, Faction> FACTIONS = new HashMap<>();

    public FactionManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
        // Commands
        addModule(new FactionCommand(this));

        // Sub Commands
        addModule(new CreateCommand(this));
        addModule(new DisbandCommand(this));
    }

    @Override
    public Map<String, Faction> getFactions() {
        return this.FACTIONS;
    }

    @Override
    public void addFaction(final Faction faction) {
        this.getFactions().put(faction.getName().toLowerCase(), faction);
    }

    @Override
    public void removeFaction(final Faction faction) {
        this.getFactions().remove(faction.getName().toLowerCase());
    }

    @Override
    public Faction getFactionByName(final String name) {
        return this.getFactions().getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public Faction getFactionByUUID(final UUID uuid) {
        for (final Faction faction : this.getFactions().values()) {
            if (!(faction.isMemberByUUID(uuid))) {
                continue;
            }

            return faction;
        }

        return null;
    }

    @Override
    public Faction getFactionByPlayer(final Player player) {
        return this.getFactionByUUID(player.getUniqueId());
    }

    @Override
    public Faction getFactionByChunk(final Chunk chunk) {
        for (final Faction faction : this.getFactions().values()) {
            if (!(faction.isTerritory(chunk))) {
                continue;
            }

            return faction;
        }

        return null;
    }

    @Override
    public Faction getFactionByLocation(final Location location) {
        return this.getFactionByChunk(location.getChunk());
    }

    @Override
    public boolean isFactionByName(final String name) {
        return this.getFactions().containsKey(name.toLowerCase());
    }

    @Override
    public FactionRelation getFactionRelationByFaction(final Faction faction, final Faction target) {
        if (faction != null && target != null) {
            if (faction.isAllianceByFaction(target)) {
                if (faction.isTrustedByFaction(target)) {
                    return FactionRelation.TRUSTED_ALLIANCE;
                }

                return FactionRelation.ALLIANCE;
            }

            if (faction.isEnemyByFaction(target)) {
                return FactionRelation.ENEMY;
            }

            if (faction.isPillageByFaction(target) || target.isPillageByFaction(faction)) {
                return FactionRelation.PILLAGE;
            }
        }

        return FactionRelation.NEUTRAL;
    }

    @Override
    public FactionRelation getFactionRelationByPlayer(final Player player, final Player target) {
        return this.getFactionRelationByFaction(this.getFactionByPlayer(player), this.getFactionByPlayer(target));
    }

    @Override
    public Faction searchFaction(final CommandSender sender, final String name, final boolean inform) {
        return this.getFactions().getOrDefault(name, null);
    }

    @Override
    public String getFactionFullName(final Faction faction, final FactionRelation factionRelation) {
        return factionRelation.getSuffix() + faction.getType() + " " + faction.getName();
    }

    @Override
    public String getFactionShortName(final Faction faction, final FactionRelation factionRelation) {
        return factionRelation.getSuffix() + faction.getName();
    }
}