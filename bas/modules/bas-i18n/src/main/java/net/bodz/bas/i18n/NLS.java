package net.bodz.bas.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class NLS {

    private final ResourceBundle resourceBundle;

    public NLS( ResourceBundle resourceBundle ) {
        if (resourceBundle == null)
            throw new NullPointerException( "resourceBundle" );
        this.resourceBundle = resourceBundle;
    }

    public NLS( String baseName, Locale locale ) {
        if (baseName == null)
            throw new NullPointerException( "baseName" );
        if (locale == null)
            throw new NullPointerException( "locale" );
        resourceBundle = ResourceBundle.getBundle( baseName, locale );
    }

    public NLS( String baseName ) {
        this( baseName, Locale.getDefault() );
    }

    public NLS( Class<?> baseClass, Locale locale ) {
        this( baseClass.getName(), locale );
    }

    public NLS( Class<?> baseClass ) {
        this( baseClass.getName(), Locale.getDefault() );
    }

    public Object getObject( String key ) {
        return resourceBundle.getObject( key );
    }

    public Object getObject( String key, Object defaultValue ) {
        if (resourceBundle.containsKey( key ))
            return resourceBundle.getObject( key );
        else
            return defaultValue;
    }

    public String getString( String key ) {
        return resourceBundle.getString( key );
    }

    public String getString( String key, String defaultString ) {
        if (resourceBundle.containsKey( key ))
            return resourceBundle.getString( key );
        else
            return defaultString;
    }

    public String[] getStringArray( String key ) {
        return resourceBundle.getStringArray( key );
    }

    public String[] getStringArray( String key, String[] defaultValue ) {
        if (resourceBundle.containsKey( key ))
            return resourceBundle.getStringArray( key );
        else
            return defaultValue;
    }

    // static accessors.
    // These acessors can be configured as code templates.

    public static Object _getObject( String baseName, Locale locale, String key ) {
        ResourceBundle bundle = ResourceBundle.getBundle( baseName, locale );
        return bundle.getObject( key );
    }

    public static Object _getObject( String baseName, Locale locale, String key, Object defaultValue ) {
        ResourceBundle bundle = ResourceBundle.getBundle( baseName, locale );
        if (bundle.containsKey( key ))
            return bundle.getObject( key );
        return defaultValue;
    }

    public static Object getObject( Class<?> baseClass, Locale locale, String key ) {
        return _getObject( baseClass.getName(), locale, key );
    }

    public static Object getObject( Class<?> baseClass, Locale locale, String key, Object defaultValue ) {
        return _getObject( baseClass.getName(), locale, key, defaultValue );
    }

    public static Object getObject( Class<?> baseClass, String key ) {
        return _getObject( baseClass.getName(), Locale.getDefault(), key );
    }

    public static Object getObject( Class<?> baseClass, String key, Object defaultValue ) {
        return _getObject( baseClass.getName(), Locale.getDefault(), key, defaultValue );
    }

    public static String _getString( String baseName, Locale locale, String key ) {
        ResourceBundle bundle = ResourceBundle.getBundle( baseName, locale );
        return bundle.getString( key );
    }

    public static String _getString( String baseName, Locale locale, String key, String defaultString ) {
        ResourceBundle bundle = ResourceBundle.getBundle( baseName, locale );
        if (bundle.containsKey( key ))
            return bundle.getString( key );
        return defaultString;
    }

    public static String getString( Class<?> baseClass, Locale locale, String key ) {
        return _getString( baseClass.getName(), locale, key );
    }

    public static String getString( Class<?> baseClass, Locale locale, String key, String defaultString ) {
        return _getString( baseClass.getName(), locale, key, defaultString );
    }

    public static String getString( Class<?> baseClass, String key ) {
        return _getString( baseClass.getName(), Locale.getDefault(), key );
    }

    public static String getString( Class<?> baseClass, String key, String defaultString ) {
        return _getString( baseClass.getName(), Locale.getDefault(), key, defaultString );
    }

    public static String format( Class<?> baseClass, Locale locale, String formatKey, Object... args ) {
        String format = _getString( baseClass.getName(), locale, formatKey );
        return String.format( format, args );
    }

    public static String format( Class<?> baseClass, String formatKey, Object... args ) {
        String format = _getString( baseClass.getName(), Locale.getDefault(), formatKey );
        return String.format( format, args );
    }

    public static String[] _getStringArray( String baseName, Locale locale, String key ) {
        ResourceBundle bundle = ResourceBundle.getBundle( baseName, locale );
        return bundle.getStringArray( key );
    }

    public static String[] _getStringArray( String baseName, Locale locale, String key, String[] defaultValue ) {
        ResourceBundle bundle = ResourceBundle.getBundle( baseName, locale );
        if (bundle.containsKey( key ))
            return bundle.getStringArray( key );
        else
            return defaultValue;
    }

    public static String[] getStringArray( Class<?> baseClass, Locale locale, String key ) {
        return _getStringArray( baseClass.getName(), locale, key );
    }

    public static String[] getStringArray( Class<?> baseClass, Locale locale, String key, String[] defaultValue ) {
        return _getStringArray( baseClass.getName(), locale, key, defaultValue );
    }

    public static String[] getStringArray( Class<?> baseClass, String key ) {
        return _getStringArray( baseClass.getName(), Locale.getDefault(), key );
    }

    public static String[] getStringArray( Class<?> baseClass, String key, String[] defaultValue ) {
        return _getStringArray( baseClass.getName(), Locale.getDefault(), key, defaultValue );
    }

}
