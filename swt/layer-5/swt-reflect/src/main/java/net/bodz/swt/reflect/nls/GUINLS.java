package net.bodz.swt.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class GUINLS extends NLSAccessor {

    public static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(GUINLS.class.getName());
    }

    public static String format(String key, Object... args) {
        return format(bundle, key, args);
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
