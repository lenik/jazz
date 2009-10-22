package net.bodz.swt.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class CommonNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(CommonNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
