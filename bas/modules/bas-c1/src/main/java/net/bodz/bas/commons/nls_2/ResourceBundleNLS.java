package net.bodz.bas.commons.nls_2;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourceBundleNLS
        extends NLS {

    private final ResourceBundle resourceBundle;

    public ResourceBundleNLS(ResourceBundle resourceBundle) {
        if (resourceBundle == null)
            throw new NullPointerException("resourceBundle");
        this.resourceBundle = resourceBundle;
    }

    public ResourceBundleNLS(String baseName, Locale locale) {
        if (baseName == null)
            throw new NullPointerException("baseName");
        if (locale == null)
            throw new NullPointerException("locale");
        resourceBundle = ResourceBundle.getBundle(baseName, locale);
    }

    public ResourceBundleNLS(String baseName) {
        this(baseName, Locale.getDefault());
    }

    public ResourceBundleNLS(Class<?> baseClass, Locale locale) {
        this(baseClass.getName(), locale);
    }

    public ResourceBundleNLS(Class<?> baseClass) {
        this(baseClass.getName(), Locale.getDefault());
    }

    @Override
    public Locale getLocale() {
        return resourceBundle.getLocale();
    }

    @Override
    public boolean containsKey(String key) {
        return resourceBundle.containsKey(key);
    }

    @Override
    public Set<String> keySet() {
        return resourceBundle.keySet();
    }

    @Override
    public Object get(String key) {
        return resourceBundle.getObject(key);
    }

    @Override
    public Object getObject(String key, Object defaultValue) {
        if (resourceBundle.containsKey(key))
            return resourceBundle.getObject(key);
        else
            return defaultValue;
    }

    @Override
    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    @Override
    public String getString(String key, String defaultString) {
        if (resourceBundle.containsKey(key))
            return resourceBundle.getString(key);
        else
            return defaultString;
    }

    @Override
    public String[] getStringArray(String key) {
        return resourceBundle.getStringArray(key);
    }

    @Override
    public String[] getStringArray(String key, String[] defaultValue) {
        if (resourceBundle.containsKey(key))
            return resourceBundle.getStringArray(key);
        else
            return defaultValue;
    }

    // static accessors.
    // These acessors can be configured as code templates.

    public static Object _getObject(String baseName, Locale locale, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        return bundle.getObject(key);
    }

    public static Object _getObject(String baseName, Locale locale, String key, Object defaultValue) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        if (bundle.containsKey(key))
            return bundle.getObject(key);
        return defaultValue;
    }

    public static Object getObject(Class<?> baseClass, Locale locale, String key) {
        return _getObject(baseClass.getName(), locale, key);
    }

    public static Object getObject(Class<?> baseClass, Locale locale, String key, Object defaultValue) {
        return _getObject(baseClass.getName(), locale, key, defaultValue);
    }

    public static Object getObject(Class<?> baseClass, String key) {
        return _getObject(baseClass.getName(), Locale.getDefault(), key);
    }

    public static Object getObject(Class<?> baseClass, String key, Object defaultValue) {
        return _getObject(baseClass.getName(), Locale.getDefault(), key, defaultValue);
    }

    public static String _getString(String baseName, Locale locale, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        return bundle.getString(key);
    }

    public static String _getString(String baseName, Locale locale, String key, String defaultString) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        if (bundle.containsKey(key))
            return bundle.getString(key);
        return defaultString;
    }

    public static String getString(Class<?> baseClass, Locale locale, String key) {
        return _getString(baseClass.getName(), locale, key);
    }

    public static String getString(Class<?> baseClass, Locale locale, String key, String defaultString) {
        return _getString(baseClass.getName(), locale, key, defaultString);
    }

    public static String getString(Class<?> baseClass, String key) {
        return _getString(baseClass.getName(), Locale.getDefault(), key);
    }

    public static String getString(Class<?> baseClass, String key, String defaultString) {
        return _getString(baseClass.getName(), Locale.getDefault(), key, defaultString);
    }

    public static String format(Class<?> baseClass, Locale locale, String formatKey, Object... args) {
        String format = _getString(baseClass.getName(), locale, formatKey);
        return String.format(format, args);
    }

    public static String format(Class<?> baseClass, String formatKey, Object... args) {
        String format = _getString(baseClass.getName(), Locale.getDefault(), formatKey);
        return String.format(format, args);
    }

    public static String[] _getStringArray(String baseName, Locale locale, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        return bundle.getStringArray(key);
    }

    public static String[] _getStringArray(String baseName, Locale locale, String key, String[] defaultValue) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        if (bundle.containsKey(key))
            return bundle.getStringArray(key);
        else
            return defaultValue;
    }

    public static String[] getStringArray(Class<?> baseClass, Locale locale, String key) {
        return _getStringArray(baseClass.getName(), locale, key);
    }

    public static String[] getStringArray(Class<?> baseClass, Locale locale, String key, String[] defaultValue) {
        return _getStringArray(baseClass.getName(), locale, key, defaultValue);
    }

    public static String[] getStringArray(Class<?> baseClass, String key) {
        return _getStringArray(baseClass.getName(), Locale.getDefault(), key);
    }

    public static String[] getStringArray(Class<?> baseClass, String key, String[] defaultValue) {
        return _getStringArray(baseClass.getName(), Locale.getDefault(), key, defaultValue);
    }

}
