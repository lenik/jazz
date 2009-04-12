package net.bodz.bas.types;

import static net.bodz.bas.types.VarCast.toBoolean;
import static net.bodz.bas.types.VarCast.toByte;
import static net.bodz.bas.types.VarCast.toChar;
import static net.bodz.bas.types.VarCast.toDate;
import static net.bodz.bas.types.VarCast.toDouble;
import static net.bodz.bas.types.VarCast.toFloat;
import static net.bodz.bas.types.VarCast.toInt;
import static net.bodz.bas.types.VarCast.toLong;
import static net.bodz.bas.types.VarCast.toShort;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Map;

import net.bodz.bas.proxy.java.util.ProxyMap;
import net.bodz.bas.types.util.Strings;

public class VarMap<K, V> extends ProxyMap<K, V> {

    public VarMap(Map<K, V> proxy) {
        super(proxy);
    }

    public boolean exists(Object key) {
        return containsKey(key);
    }

    public V get(K key, V def) {
        return exists(key) ? get(key) : def;
    }

    public V getObject(K key) {
        return get(key);
    }

    public V getObject(K key, V def) {
        return get(key, def);
    }

    public String getString(K key) {
        return String.valueOf(get(key));
    }

    public String getString(K key, String def) {
        return exists(key) ? getString(key) : def;
    }

    public byte getByte(K key) {
        return toByte(get(key));
    }

    public byte getByte(K key, byte def) {
        return exists(key) ? getByte(key) : def;
    }

    public short getShort(K key) {
        return toShort(get(key));
    }

    public short getShort(K key, short def) {
        return exists(key) ? getShort(key) : def;
    }

    public int getInt(K key) {
        return toInt(get(key));
    }

    public int getInt(K key, int def) {
        return exists(key) ? getInt(key) : def;
    }

    public long getLong(K key) {
        return toLong(get(key));
    }

    public long getLong(K key, long def) {
        return exists(key) ? getLong(key) : def;
    }

    public float getFloat(K key) {
        return toFloat(get(key));
    }

    public float getFloat(K key, float def) {
        return exists(key) ? getFloat(key) : def;
    }

    public double getDouble(K key) {
        return toDouble(get(key));
    }

    public double getDouble(K key, double def) {
        return exists(key) ? getDouble(key) : def;
    }

    public boolean getBoolean(K key) {
        return toBoolean(get(key));
    }

    public boolean getBoolean(K key, boolean def) {
        return exists(key) ? getBoolean(key) : def;
    }

    public char getChar(K key) {
        return toChar(get(key));
    }

    public char getChar(K key, char def) {
        return exists(key) ? getChar(key) : def;
    }

    public Date getDate(K key, Date def) {
        return exists(key) ? getDate(key) : def;
    }

    public Date getDate(K key) {
        return toDate(get(key));
    }

    @SuppressWarnings("unchecked")
    public <T> T[] getArray(K key, T[] hint) {
        V val = get(key);
        if (val == null)
            return null;
        if (val instanceof Object[])
            return (T[]) val;
        Class<?> _t = hint.getClass().getComponentType();
        T[] array = (T[]) Array.newInstance(_t, 1);
        array[0] = (T) val;
        return array;
    }

    private static final Object[] T_OBJECT = {};

    public String[] getStrings(K key) {
        Object[] array = getArray(key, T_OBJECT);
        if (array == null)
            return null;
        String[] strings = new String[array.length];
        for (int i = 0; i < array.length; i++)
            if (array[i] != null)
                strings[i] = array[i].toString();
        return strings;
    }

    public String getStrings(K key, String delimiter) {
        String[] strings = getStrings(key);
        if (strings == null)
            return null;
        return Strings.join(delimiter, strings);
    }

}
