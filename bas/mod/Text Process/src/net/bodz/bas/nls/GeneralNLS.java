package net.bodz.bas.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class GeneralNLS extends NLSAccessor {

    public static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(GeneralNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

    public static String format(String key, Object... args) {
        return format(bundle, key, args);
    }

}
