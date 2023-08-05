package me.trae.factions.utility;

import me.trae.factions.framework.Frame;

import java.util.LinkedHashMap;

public class UtilFrame {

    private static String getKey(final String name) {
        return UtilFormat.sliceString(name).toUpperCase();
    }

    public static <F extends Frame> void addFrame(final LinkedHashMap<String, F> map, final F frame) {
        map.put(getKey(frame.getClass().getSimpleName()), frame);
    }

    public static <F extends Frame> void removeFrame(final LinkedHashMap<String, F> map, final F frame) {
        map.remove(getKey(frame.getClass().getSimpleName()));
    }

    public static <F extends Frame> F getFrameByName(final LinkedHashMap<String, F> map, final String name) {
        return map.getOrDefault(getKey(name), null);
    }

    public static <F extends Frame, E extends F> E getFrameByClass(final LinkedHashMap<String, F> map, final Class<E> clazz) {
        return UtilJava.cast(clazz, getFrameByName(map, clazz.getSimpleName()));
    }

    public static <F extends Frame> boolean isFrameByName(final LinkedHashMap<String, F> map, final String name) {
        return map.containsKey(getKey(name));
    }

    public static <F extends Frame> boolean hasFrames(final LinkedHashMap<String, F> map) {
        return !(map.isEmpty());
    }
}