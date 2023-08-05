package me.trae.factions.faction.interfaces;

import me.trae.factions.faction.Faction;
import me.trae.factions.faction.data.Alliance;
import me.trae.factions.faction.data.Enemy;
import me.trae.factions.faction.data.Member;
import me.trae.factions.faction.data.Pillage;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IFaction {

    boolean isAdmin();

    String getType();

    String getName();

    List<Chunk> getTerritory();

    void addTerritory(final Chunk chunk);

    void removeTerritory(final Chunk chunk);

    boolean isTerritory(final Chunk chunk);

    Map<UUID, Member> getMembers();

    void addMember(final Member member);

    void removeMember(final Member member);

    Member getMemberByUUID(final UUID uuid);

    Member getMemberByPlayer(final Player player);

    boolean isMemberByUUID(final UUID uuid);

    boolean isMemberByPlayer(final Player player);

    Map<String, Alliance> getAlliances();

    void addAlliance(final Alliance alliance);

    void removeAlliance(final Alliance alliance);

    Alliance getAllianceByFaction(final Faction faction);

    boolean isAllianceByFaction(final Faction faction);

    boolean isTrustedByFaction(final Faction faction);

    Map<String, Enemy> getEnemies();

    void addEnemy(final Enemy enemy);

    void removeEnemy(final Enemy enemy);

    Enemy getEnemyByFaction(final Faction faction);

    boolean isEnemyByFaction(final Faction faction);

    Map<String, Pillage> getPillages();

    void addPillage(final Pillage pillage);

    void removePillage(final Pillage pillage);

    Pillage getPillageByFaction(final Faction faction);

    boolean isPillageByFaction(final Faction faction);

    long getCreated();

    UUID getFounder();

    void setFounder(final UUID founder);
}