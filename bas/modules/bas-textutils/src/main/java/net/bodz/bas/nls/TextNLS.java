package net.bodz.bas.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class TextNLS extends NLSAccessor {

    public static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(TextNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
