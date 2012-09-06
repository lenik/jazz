package net.bodz.bas.i18n.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

class Utf8PropertyResourceBundle
        extends ResourceBundle {

    private final PropertyResourceBundle bundle;

    Utf8PropertyResourceBundle(PropertyResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public Enumeration<String> getKeys() {
        return bundle.getKeys();
    }

    @Override
    protected Object handleGetObject(String key) {
        String value = (String) bundle.handleGetObject(key);
        try {
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Shouldn't fail - but should we still add logging message?
            return null;
        }
    }

}
