package net.bodz.bas.nls;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class FPNLS {
    private static final String         BUNDLE_NAME     = "net.bodz.bas.FPNLS";         //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
                                                                .getBundle(BUNDLE_NAME);

    private FPNLS() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
