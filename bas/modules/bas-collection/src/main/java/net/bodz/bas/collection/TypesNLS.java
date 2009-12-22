package net.bodz.bas.collection;

import java.util.ResourceBundle;

public class TypesNLS extends NLSAccessor {

    private static final ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(TypesNLS.class.getName());
    }

    public static String getString(String key) {
        return getString(bundle, key);
    }

}
