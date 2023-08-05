package me.trae.factions.framework.interfaces.factories;

import me.trae.factions.framework.SpigotManager;
import me.trae.factions.framework.interfaces.containers.InstanceContainer;
import me.trae.factions.utility.UtilFrame;

import java.util.LinkedHashMap;

public interface ManagerContentsForPluginFactory extends InstanceContainer {

    void registerManagers();

    LinkedHashMap<String, SpigotManager> getManagers();

    default void addManager(final SpigotManager manager) {
        UtilFrame.addFrame(this.getManagers(), manager);
    }

    default void removeManager(final SpigotManager manager) {
        UtilFrame.removeFrame(this.getManagers(), manager);
    }

    default SpigotManager getManagerByName(final String name) {
        return UtilFrame.getFrameByName(this.getManagers(), name);
    }

    default <E extends SpigotManager> E getManagerByClass(final Class<E> clazz) {
        return UtilFrame.getFrameByClass(this.getManagers(), clazz);
    }

    default boolean isManagerByName(final String name) {
        return UtilFrame.isFrameByName(this.getManagers(), name);
    }

    default boolean hasManagers() {
        return UtilFrame.hasFrames(this.getManagers());
    }
}