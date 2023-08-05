package me.trae.factions.framework;

import me.trae.factions.framework.interfaces.ISpigotManager;

import java.util.LinkedHashMap;

public abstract class SpigotManager extends Frame implements ISpigotManager {

    private final LinkedHashMap<String, SpigotModule<?>> MODULES = new LinkedHashMap<>();

    public SpigotManager(final SpigotPlugin instance) {
        super(instance);
    }

    @Override
    public LinkedHashMap<String, SpigotModule<?>> getModules() {
        return this.MODULES;
    }

    @Override
    public void initializeFrame() {
        for (final SpigotModule<?> module : this.getModules().values()) {
            module.initializeFrame();
        }

        super.initializeFrame();
    }

    @Override
    public void shutdownFrame() {
        for (final SpigotModule<?> module : this.getModules().values()) {
            module.shutdownFrame();
        }

        super.shutdownFrame();
    }
}