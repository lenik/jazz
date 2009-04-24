package net.bodz.dist.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class PackNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(PackNLS.class.getName());
    }

    public static String format(String key, Object... args) {
        return getString(bundle, key);
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
