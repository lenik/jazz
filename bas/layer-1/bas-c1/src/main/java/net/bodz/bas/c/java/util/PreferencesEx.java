package net.bodz.bas.c.java.util;

import java.util.prefs.Preferences;

public class PreferencesEx
        extends DecoratedPreferences
        implements
            IPreferencesEx {

    public PreferencesEx(Preferences wrapped) {
        super(wrapped);
    }

    @Override
    public void set(String key, String value) {
        if (value == null)
            remove(key);
        else
            put(key, value);
    }

    @Override
    public void setInt(String key, Integer value) {
        if (value == null)
            remove(key);
        else
            super.putInt(key, value);
    }

    @Override
    public void setLong(String key, Long value) {
        if (value == null)
            remove(key);
        else
            super.putLong(key, value);
    }

    @Override
    public void setBoolean(String key, Boolean value) {
        if (value == null)
            remove(key);
        else
            super.putBoolean(key, value);
    }

    @Override
    public void setFloat(String key, Float value) {
        if (value == null)
            remove(key);
        else
            super.putFloat(key, value);
    }

    @Override
    public void setDouble(String key, Double value) {
        if (value == null)
            remove(key);
        else
            super.putDouble(key, value);
    }

    @Override
    public void setByteArray(String key, byte[] value) {
        if (value == null)
            remove(key);
        else
            super.putByteArray(key, value);
    }

}
