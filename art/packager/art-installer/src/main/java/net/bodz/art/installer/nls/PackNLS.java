package net.bodz.art.installer.nls;

import java.util.ResourceBundle;

public class PackNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(PackNLS.class.getName());
    }

    public static String format(String key, Object... args) {
        return format(bundle, key, args);
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
