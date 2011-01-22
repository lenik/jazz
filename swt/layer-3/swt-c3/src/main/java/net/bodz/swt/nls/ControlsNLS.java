package net.bodz.swt.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class ControlsNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(ControlsNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
