package me.trae.factions.framework.interfaces.factories;

import me.trae.factions.framework.SpigotModule;
import me.trae.factions.framework.interfaces.containers.InstanceContainer;
import me.trae.factions.utility.UtilFrame;

import java.util.LinkedHashMap;

public interface ModuleContentsForManagerFactory extends InstanceContainer {

    void registerModules();

    LinkedHashMap<String, SpigotModule<?>> getModules();

    default void addModule(final SpigotModule<?> module) {
        UtilFrame.addFrame(this.getModules(), module);
    }

    default void removeModule(final SpigotModule<?> module) {
        UtilFrame.removeFrame(this.getModules(), module);
    }

    default SpigotModule<?> getModuleByName(final String name) {
        return UtilFrame.getFrameByName(this.getModules(), name);
    }

    default <E extends SpigotModule<?>> E getModuleByClass(final Class<E> clazz) {
        return UtilFrame.getFrameByClass(this.getModules(), clazz);
    }

    default boolean isModuleByName(final String name) {
        return UtilFrame.isFrameByName(this.getModules(), name);
    }

    default boolean hasModules() {
        return UtilFrame.hasFrames(this.getModules());
    }
}