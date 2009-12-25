package net.bodz.bas.i18n.nls;

import java.util.ResourceBundle;

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
