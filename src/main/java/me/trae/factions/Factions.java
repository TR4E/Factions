package me.trae.factions;

import me.trae.factions.account.AccountManager;
import me.trae.factions.command.CommandManager;
import me.trae.factions.faction.FactionManager;
import me.trae.factions.framework.SpigotPlugin;
import me.trae.factions.server.ServerManager;

public class Factions extends SpigotPlugin {

    @Override
    public void registerManagers() {
        addManager(new AccountManager(this));
        addManager(new CommandManager(this));
        addManager(new FactionManager(this));
        addManager(new ServerManager(this));
    }
}