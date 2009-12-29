package net.bodz.bas.collection;

import java.util.ResourceBundle;

import net.bodz.bas.lang.i18n.nls.NLSAccessor;

public class TypesNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(TypesNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
