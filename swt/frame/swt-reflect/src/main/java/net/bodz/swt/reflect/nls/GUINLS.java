package net.bodz.swt.reflect.nls;

import java.util.ResourceBundle;

import net.bodz.bas.i18n.nls.ResourceBundleNLS;

public class GUINLS
        extends ResourceBundleNLS {

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
