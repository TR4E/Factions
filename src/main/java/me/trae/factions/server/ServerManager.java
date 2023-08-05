package me.trae.factions.server;

import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.SpigotPlugin;
import me.trae.factions.server.modules.HandleServerMOTD;

public class ServerManager extends SpigotManager {

    public ServerManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public void registerModules() {
        addModule(new HandleServerMOTD(this));
    }
}