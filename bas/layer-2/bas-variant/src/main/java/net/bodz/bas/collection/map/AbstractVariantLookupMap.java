package net.bodz.bas.collection.map;

public abstract class AbstractVariantLookupMap<K>
        extends AbstractVariantLookupMap_MatrixImpl<K> {

    @Override
    public String format(K formatKey, Object... args) {
        String format = getString(formatKey);
        return String.format(format, args);
    }

    @Override
    public Object get(K key, Object defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        return value;
    }

    @Override
    public String getString(K key, String defaultString) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultString;
        return value.toString();
    }

    @Override
    public String getString(K key) {
        return getString(key, null);
    }

    @Override
    public String[] getStringArray(K key, String[] defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        if (value instanceof String[])
            return (String[]) value;
        return new String[] { value.toString() };
    }

    @Override
    public String[] getStringArray(K key) {
        return getStringArray(key, null);
    }

}
