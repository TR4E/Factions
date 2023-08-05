package me.trae.factions.framework.interfaces;

import me.trae.factions.framework.SpigotPlugin;

import java.util.Map;

public interface IFrame {

    SpigotPlugin getInstance();

    String getName();

    Map<String, Object> getPrimitives();

    void addPrimitive(final String name, final Object value);

    <E> E getPrimitiveCasted(final Class<E> clazz, final String name);

    boolean isPrimitiveByName(final String name);

    boolean hasPrimitives();

    void initializeFrame();

    void shutdownFrame();

    void onInitialize();

    void onShutdown();
}