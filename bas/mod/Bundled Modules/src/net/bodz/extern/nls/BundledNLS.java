package net.bodz.extern.nls;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BundledNLS {
    private static final String         BUNDLE_NAME     = "net.bodz.extern.diff.messages";      //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private BundledNLS() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
