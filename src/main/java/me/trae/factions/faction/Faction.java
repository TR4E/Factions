package me.trae.factions.faction;

import me.trae.factions.faction.data.Alliance;
import me.trae.factions.faction.data.Enemy;
import me.trae.factions.faction.data.Member;
import me.trae.factions.faction.data.Pillage;
import me.trae.factions.faction.interfaces.IFaction;
import me.trae.factions.utility.UtilJava;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.*;

public class Faction implements IFaction {

    private final String name;

    private final List<Chunk> territory = new ArrayList<>();

    private final Map<UUID, Member> members = new HashMap<>();
    private final Map<String, Alliance> alliances = new HashMap<>();
    private final Map<String, Enemy> enemies = new HashMap<>();
    private final Map<String, Pillage> pillages = new HashMap<>();

    private final long created;

    private UUID founder;

    public Faction(final String name) {
        this.name = name;
        this.created = System.currentTimeMillis();
    }

    @Override
    public boolean isAdmin() {
        return this instanceof AdminFaction;
    }

    @Override
    public String getType() {
        return "Faction";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Chunk> getTerritory() {
        return this.territory;
    }

    @Override
    public void addTerritory(final Chunk chunk) {
        this.getTerritory().add(chunk);
    }

    @Override
    public void removeTerritory(final Chunk chunk) {
        this.getTerritory().remove(chunk);
    }

    @Override
    public boolean isTerritory(final Chunk chunk) {
        return this.getTerritory().contains(chunk);
    }

    @Override
    public Map<UUID, Member> getMembers() {
        return this.members;
    }

    @Override
    public void addMember(final Member member) {
        this.getMembers().put(member.getUUID(), member);
    }

    @Override
    public void removeMember(final Member member) {
        this.getMembers().remove(member.getUUID());
    }

    @Override
    public Member getMemberByUUID(final UUID uuid) {
        return this.getMembers().getOrDefault(uuid, null);
    }

    @Override
    public Member getMemberByPlayer(final Player player) {
        return this.getMemberByUUID(player.getUniqueId());
    }

    @Override
    public boolean isMemberByUUID(final UUID uuid) {
        return this.getMembers().containsKey(uuid);
    }

    @Override
    public boolean isMemberByPlayer(final Player player) {
        return this.isMemberByUUID(player.getUniqueId());
    }

    @Override
    public Map<String, Alliance> getAlliances() {
        return this.alliances;
    }

    @Override
    public void addAlliance(final Alliance alliance) {
        this.getAlliances().put(alliance.getName(), alliance);
    }

    @Override
    public void removeAlliance(final Alliance alliance) {
        this.getAlliances().remove(alliance.getName());
    }

    @Override
    public Alliance getAllianceByFaction(final Faction faction) {
        return this.getAlliances().getOrDefault(faction.getName(), null);
    }

    @Override
    public boolean isAllianceByFaction(final Faction faction) {
        return this.getAlliances().containsKey(faction.getName());
    }

    @Override
    public boolean isTrustedByFaction(final Faction faction) {
        return this.isAllianceByFaction(faction) && this.getAllianceByFaction(faction).isTrusted();
    }

    @Override
    public Map<String, Enemy> getEnemies() {
        return this.enemies;
    }

    @Override
    public void addEnemy(final Enemy enemy) {
        this.getEnemies().put(enemy.getName(), enemy);
    }

    @Override
    public void removeEnemy(final Enemy enemy) {
        this.getEnemies().remove(enemy.getName());
    }

    @Override
    public Enemy getEnemyByFaction(final Faction faction) {
        return this.getEnemies().getOrDefault(faction.getName(), null);
    }

    @Override
    public boolean isEnemyByFaction(final Faction faction) {
        return this.getEnemies().containsKey(faction.getName());
    }

    @Override
    public Map<String, Pillage> getPillages() {
        return this.pillages;
    }

    @Override
    public void addPillage(final Pillage pillage) {
        this.getPillages().put(pillage.getName(), pillage);
    }

    @Override
    public void removePillage(final Pillage pillage) {
        this.getPillages().remove(pillage.getName());
    }

    @Override
    public Pillage getPillageByFaction(final Faction faction) {
        return this.getPillages().getOrDefault(faction.getName(), null);
    }

    @Override
    public boolean isPillageByFaction(final Faction faction) {
        return this.getPillages().containsKey(faction.getName());
    }

    @Override
    public long getCreated() {
        return this.created;
    }

    @Override
    public UUID getFounder() {
        return this.founder;
    }

    @Override
    public void setFounder(final UUID founder) {
        this.founder = founder;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Faction && UtilJava.cast(Faction.class, object).getName().equals(this.getName());
    }
}