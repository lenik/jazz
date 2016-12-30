package net.bodz.bas.t.variant;

import java.lang.reflect.Array;

public abstract class AbstractVariantMap<K>
        extends AbstractTmVariantMap<K> {

    @Override
    public String format(K formatKey, Object... args) {
        String format = getString(formatKey);
        return String.format(format, args);
    }

    @Override
    public Object get(Object key, Object defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        return value;
    }

    @Override
    public final Object getScalar(K key) {
        return getScalar(key, null);
    }

    @Override
    public Object getScalar(K key, Object defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        if (!value.getClass().isArray())
            return value;
        int length = Array.getLength(value);
        if (length == 0)
            return null;
        else
            return Array.get(value, 0);
    }

    @Override
    public final Object[] getArray(K key) {
        return getArray(key, null);
    }

    @Override
    public Object[] getArray(K key, Object[] defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? new String[] { null } : defaultValue;
        if (value.getClass().isArray())
            return (Object[]) value;
        else
            return new Object[] { value };
    }

    @Override
    public String getString(K key) {
        Object value = getScalar(key);
        return value == null ? null : value.toString();
    }

    @Override
    public String getString(K key, String defaultString) {
        Object value = getScalar(key);
        if (value == null)
            return containsKey(key) ? null : defaultString;
        return value.toString();
    }

    public <T extends Enum<T>> T getEnum(Class<T> enumType, K key, T defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        T value = Enum.valueOf(enumType, s);
        return value;
    }

    @Override
    public String[] getStringArray(K key) {
        Object value = get(key);
        return _VariantFn.toStringArray(value);
    }

    @Override
    public String[] getStringArray(K key, String[] defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? new String[] { null } : defaultValue;
        return _VariantFn.toStringArray(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (K key : keySet()) {
            sb.append(key);
            sb.append(": ");
            sb.append(getString(key));
            sb.append('\n');
        }
        return super.toString();
    }

    @Override
    public Byte getByte(K key, Byte defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        byte val = getByte(key);
        return val;
    }

    @Override
    public Short getShort(K key, Short defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        short val = getShort(key);
        return val;
    }

    @Override
    public Integer getInt(K key, Integer defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        int val = getInt(key);
        return val;
    }

    @Override
    public Long getLong(K key, Long defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        long val = getLong(key);
        return val;
    }

    @Override
    public Float getFloat(K key, Float defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        float val = getFloat(key);
        return val;
    }

    @Override
    public Double getDouble(K key, Double defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        double val = getDouble(key);
        return val;
    }

    @Override
    public Boolean getBoolean(K key, Boolean defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        switch (s) {
        case "true":
        case "yes":
        case "on":
        case "1":
            return true;
        }
        return false;
    }

    @Override
    public Character getChar(K key, Character defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        char val = getChar(key);
        return val;
    }

}
