package net.bodz.bas.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class AppNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(AppNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
