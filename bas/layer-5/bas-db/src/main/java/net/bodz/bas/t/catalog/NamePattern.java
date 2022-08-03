package net.bodz.bas.t.catalog;

public class NamePattern {

    public static boolean matches(String pattern, String s) {
        if (pattern == null)
            return true;
        if (s == null)
            return true;
        if (pattern.equals(s))
            return true;
        return false;
    }

}
