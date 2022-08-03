package net.bodz.bas.t.catalog;

public class NamePattern {

    public static boolean matches(String pattern, String s, boolean ignoreCase) {
        if (ignoreCase)
            return matchesIgnoreCase(pattern, s);
        else
            return matches(pattern, s);
    }

    public static boolean matches(String pattern, String s) {
        if (pattern == null)
            return true;
        if (s == null)
            return true;
        if (pattern.equals(s))
            return true;
        return false;
    }

    public static boolean matchesIgnoreCase(String pattern, String s) {
        if (pattern == null)
            return true;
        if (s == null)
            return true;
        if (pattern.equalsIgnoreCase(s))
            return true;
        return false;
    }

}
