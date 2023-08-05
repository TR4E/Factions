package me.trae.factions.framework.interfaces;

import me.trae.factions.framework.enums.PluginProperty;
import me.trae.factions.framework.interfaces.containers.PropertyContainer;
import me.trae.factions.framework.interfaces.factories.ManagerContentsForPluginFactory;

public interface ISpigotPlugin extends ManagerContentsForPluginFactory, PropertyContainer<PluginProperty> {

    default String getPluginName() {
        return this.getClass().getSimpleName();
    }

    void initializePlugin();

    void shutdownPlugin();
}