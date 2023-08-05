package me.trae.factions.utility;

public class UtilFormat {

    public static String cleanString(final String string) {
        return string;
    }

    public static String sliceString(String string) {
        for (final String replacement : new String[]{"_", " "}) {
            string = string.replaceAll(replacement, "");
        }

        return string;
    }

    public static String unSliceString(final String string) {
        return string;
    }

    public static String toPairString(final String key, final String value) {
        return key + ": " + value;
    }
}