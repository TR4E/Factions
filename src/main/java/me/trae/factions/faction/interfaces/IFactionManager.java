package me.trae.factions.faction.interfaces;

import me.trae.factions.client.Client;
import me.trae.factions.faction.Faction;
import me.trae.factions.faction.enums.FactionRelation;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public interface IFactionManager {

    Map<String, Faction> getFactions();

    void addFaction(final Faction faction);

    void removeFaction(final Faction faction);

    Faction getFactionByName(final String name);

    Faction getFactionByUUID(final UUID uuid);

    Faction getFactionByPlayer(final Player player);

    Faction getFactionByChunk(final Chunk chunk);

    Faction getFactionByLocation(final Location location);

    boolean isFactionByName(final String name);

    FactionRelation getFactionRelationByFaction(final Faction faction, final Faction target);

    FactionRelation getFactionRelationByPlayer(final Player player, final Player target);

    Faction searchFaction(final CommandSender sender, final String name, final boolean inform);

    String getFactionFullName(final Faction faction, final FactionRelation factionRelation);

    String getFactionShortName(final Faction faction, final FactionRelation factionRelation);

    LinkedHashMap<String, String> getFactionInformation(final Player player, final Client client, final Faction playerFaction, final Faction targetFaction);
}