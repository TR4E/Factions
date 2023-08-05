package me.trae.factions.faction.interfaces;

import me.trae.factions.faction.Faction;
import me.trae.factions.faction.FactionManager;
import me.trae.factions.faction.data.Alliance;
import me.trae.factions.faction.data.Enemy;
import me.trae.factions.faction.data.Member;
import me.trae.factions.faction.data.Pillage;
import org.bukkit.Chunk;
import org.bukkit.Location;
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

    int getMaxClaims();

    String getTerritoryString();

    Map<UUID, Member> getMembers();

    void addMember(final Member member);

    void removeMember(final Member member);

    Member getMemberByUUID(final UUID uuid);

    Member getMemberByPlayer(final Player player);

    boolean isMemberByUUID(final UUID uuid);

    boolean isMemberByPlayer(final Player player);

    String getMembersString(final FactionManager manager, final Player receiverPlayer);

    Map<String, Alliance> getAlliances();

    void addAlliance(final Alliance alliance);

    void removeAlliance(final Alliance alliance);

    Alliance getAllianceByFaction(final Faction faction);

    boolean isAllianceByFaction(final Faction faction);

    boolean isTrustedByFaction(final Faction faction);

    String getAlliancesString(final FactionManager manager, final Faction receiverFaction);

    Map<String, Enemy> getEnemies();

    void addEnemy(final Enemy enemy);

    void removeEnemy(final Enemy enemy);

    Enemy getEnemyByFaction(final Faction faction);

    boolean isEnemyByFaction(final Faction faction);

    String getEnemiesString(final FactionManager manager, final Faction receiverFaction);

    Map<String, Pillage> getPillages();

    void addPillage(final Pillage pillage);

    void removePillage(final Pillage pillage);

    Pillage getPillageByFaction(final Faction faction);

    boolean isPillageByFaction(final Faction faction);

    String getPillagesString(final FactionManager manager, final Faction receiverFaction);

    boolean isNeutralByFaction(final Faction faction);

    long getCreated();

    String getCreatedString();

    UUID getFounder();

    void setFounder(final UUID founder);

    Location getHome();

    void setHome(final Location home);

    boolean hasHome();

    String getHomeString();
}