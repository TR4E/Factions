package me.trae.factions.framework.interfaces.containers;

import me.trae.factions.framework.Frame;
import me.trae.factions.framework.SpigotPlugin;
import me.trae.factions.utility.UtilJava;

public interface InstanceContainer {

    default SpigotPlugin getInstance() {
        if (this instanceof Frame) {
            return UtilJava.cast(Frame.class, this).getInstance();
        }

        if (this instanceof SpigotPlugin) {
            return UtilJava.cast(SpigotPlugin.class, this);
        }

        return null;
    }
}