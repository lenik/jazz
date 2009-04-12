package net.bodz.swt.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class GUINLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(GUINLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
