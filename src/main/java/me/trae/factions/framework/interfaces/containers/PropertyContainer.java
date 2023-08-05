package me.trae.factions.framework.interfaces.containers;

import me.trae.factions.utility.UtilJava;

import java.util.LinkedHashMap;

public interface PropertyContainer<E extends Enum<?>> {

    LinkedHashMap<E, Object> getProperties();

    default void setProperty(final E property, final Object value) {
        this.getProperties().put(property, value);
    }

    default <T> T getProperty(final Class<T> clazz, final E property) {
        return UtilJava.cast(clazz, this.getProperties().getOrDefault(property, null));
    }

    default boolean isProperty(final E property) {
        return this.getProperties().containsKey(property);
    }
}