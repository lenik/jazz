package net.bodz.bas.t.variant;

import java.lang.reflect.Array;

import net.bodz.bas.c.object.Nullables;

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
        return Nullables.toString(value);
    }

    @Override
    public String getString(K key, String defaultString) {
        Object value = getScalar(key);
        if (value == null)
            return containsKey(key) ? null : defaultString;
        return value.toString();
    }

    @Override
    public String[] getStringArray(K key) {
        Object value = get(key);
        return toStringArray(value);
    }

    @Override
    public String[] getStringArray(K key, String[] defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? new String[] { null } : defaultValue;
        return toStringArray(value);
    }

    static String[] toStringArray(Object scalarOrArray) {
        if (scalarOrArray == null)
            return null;

        Class<?> type = scalarOrArray.getClass();
        if (!type.isArray())
            return new String[] { Nullables.toString(scalarOrArray) };

        if (type.getComponentType().equals(String.class))
            return (String[]) scalarOrArray;

        Object array = scalarOrArray;
        int len = Array.getLength(array);
        String[] sv = new String[len];
        for (int i = 0; i < len; i++) {
            Object item = Array.get(array, i);
            if (item != null) {
                sv[i] = item.toString();
            }
        }
        return sv;
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

}
