package me.trae.factions.utility;

public class UtilJava {

    public static <T> T cast(final Class<T> clazz, final Object object) {
        if ((clazz != null && object != null) && clazz.isInstance(object)) {
            try {
                return clazz.cast(object);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}