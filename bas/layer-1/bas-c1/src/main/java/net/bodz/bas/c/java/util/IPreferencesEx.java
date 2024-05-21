package net.bodz.bas.c.java.util;

public interface IPreferencesEx
        extends
            IPreferences {

    void set(String key, String value);

    void setInt(String key, Integer value);

    void setLong(String key, Long value);

    void setBoolean(String key, Boolean value);

    void setFloat(String key, Float value);

    void setDouble(String key, Double value);

    void setByteArray(String key, byte[] value);

}
