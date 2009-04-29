package net.bodz.bas.nls;

import java.util.ResourceBundle;

import net.bodz.bas.text.locale.NLSAccessor;

public class FPNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(FPNLS.class.getName());
    }

    public static String format(String format, Object... args) {
        return format(bundle, format, args);
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
