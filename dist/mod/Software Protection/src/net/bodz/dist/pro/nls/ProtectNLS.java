package net.bodz.dist.pro.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class ProtectNLS extends NLSAccessor {

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
