package net.bodz.bas.c.java.util;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class UTF8ResourceBundle {

    public static final ResourceBundle getBundle(String baseName) {
        return ResourceBundle.getBundle(baseName, UTF8Control.INSTANCE);
    }

    public static final ResourceBundle getBundle(String baseName, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale, UTF8Control.INSTANCE);
    }

    public static ResourceBundle getBundle(String baseName, Locale locale, ClassLoader loader) {
        return ResourceBundle.getBundle(baseName, locale, UTF8Control.INSTANCE);
    }

}