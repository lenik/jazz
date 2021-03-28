package net.bodz.bas.i18n.nls;

public class NlstrFn {

    public static String tr(String key) {
        return key;
    }

    public static String tr(String format, Object... args) {
        return String.format(format, args);
    }

}
