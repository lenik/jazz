package net.bodz.bios.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class BIOSNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(BIOSNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
