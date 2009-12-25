package net.bodz.bas.i18n.nls;

import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class NLSAccessor {

    Iterator<Locale> getLocales(Locale locale) {
        // ResourceBundle rb;
        return null;
    }

    // ResourceBundle utilities.
    static boolean diag = true;

    protected static String format(ResourceBundle bundle, String key, Object... args) {
        String format = getString(bundle, key);
        if (format == null)
            throw new NullPointerException("key isn't existed: " + key); //$NON-NLS-1$
        return String.format(format, args);
    }

    protected static String getString(ResourceBundle bundle, String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

}
