package me.trae.factions.server.modules;

import me.trae.factions.framework.SpigotListener;
import me.trae.factions.server.ServerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

public class HandleServerMOTD extends SpigotListener<ServerManager> {

    public HandleServerMOTD(final ServerManager manager) {
        super(manager);

        this.addPrimitive("MOTD", "");
    }

    @EventHandler
    public void onServerListPing(final ServerListPingEvent event) {
        event.setMotd(this.getPrimitiveCasted(String.class, "MOTD"));
    }
}