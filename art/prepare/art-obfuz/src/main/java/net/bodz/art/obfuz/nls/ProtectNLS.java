package net.bodz.art.obfuz.nls;

import java.util.ResourceBundle;

public class ProtectNLS
        extends NLS {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(ProtectNLS.class.getName());
    }

    public static String format(String key, Object... args) {
        return format(bundle, key, args);
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
