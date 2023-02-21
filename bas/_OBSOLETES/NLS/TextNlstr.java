package net.bodz.bas.i18n.nls;

import java.util.ResourceBundle;

public class TextNLS extends NLSAccessor {

    public static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(TextNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
