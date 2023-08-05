package me.trae.factions.framework;

import me.trae.factions.framework.interfaces.IFrame;
import me.trae.factions.utility.UtilFormat;
import me.trae.factions.utility.UtilJava;

import java.util.HashMap;
import java.util.Map;

public class Frame implements IFrame {

    private final SpigotPlugin instance;
    private final String name;
    private final Map<String, Object> primitives;

    public Frame(final SpigotPlugin instance) {
        this.instance = instance;
        this.name = UtilFormat.unSliceString(this.getClass().getSimpleName());
        this.primitives = new HashMap<>();
    }

    @Override
    public SpigotPlugin getInstance() {
        return this.instance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Map<String, Object> getPrimitives() {
        return this.primitives;
    }

    @Override
    public void addPrimitive(final String name, final Object value) {
        this.getPrimitives().put(name, value);
    }

    @Override
    public <E> E getPrimitiveCasted(final Class<E> clazz, final String name) {
        return UtilJava.cast(clazz, this.getPrimitives().getOrDefault(name, null));
    }

    @Override
    public boolean isPrimitiveByName(final String name) {
        return this.getPrimitives().containsKey(name);
    }

    @Override
    public boolean hasPrimitives() {
        return !(this.getPrimitives().isEmpty());
    }

    @Override
    public void initializeFrame() {
        this.onInitialize();
    }

    @Override
    public void shutdownFrame() {
        this.onShutdown();
    }

    @Override
    public void onInitialize() {
    }

    @Override
    public void onShutdown() {
    }
}