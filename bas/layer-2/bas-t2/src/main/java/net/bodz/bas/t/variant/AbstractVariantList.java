package net.bodz.bas.t.variant;

import java.lang.reflect.Array;

public abstract class AbstractVariantList
        extends AutoConvVariantList {

    @Override
    public String format(int formatIndex, Object... args) {
        String format = getString(formatIndex);
        return String.format(format, args);
    }

    @Override
    public Object get(int index, Object defaultValue) {
        Object value = get(index);
        if (value == null)
            return checkIndex(index) ? null : defaultValue;
        return value;
    }

    @Override
    public final Object getScalar(int index) {
        return getScalar(index, null);
    }

    @Override
    public Object getScalar(int index, Object defaultValue) {
        Object value = get(index);
        if (value == null)
            return checkIndex(index) ? null : defaultValue;
        if (!value.getClass().isArray())
            return value;
        int length = Array.getLength(value);
        if (length == 0)
            return null;
        else
            return Array.get(value, 0);
    }

    @Override
    public final Object[] getArray(int index) {
        return getArray(index, null);
    }

    @Override
    public Object[] getArray(int index, Object[] defaultValue) {
        Object value = get(index);
        if (value == null)
            return checkIndex(index) ? new String[] { null } : defaultValue;
        if (value.getClass().isArray())
            return (Object[]) value;
        else
            return new Object[] { value };
    }

    @Override
    public String getString(int index) {
        Object value = getScalar(index);
        return value == null ? null : value.toString();
    }

    @Override
    public String getString(int index, String defaultString) {
        Object value = getScalar(index);
        if (value == null)
            return checkIndex(index) ? null : defaultString;
        return value.toString();
    }

    public <T extends Enum<T>> T getEnum(Class<T> enumType, int index, T defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        T value = Enum.valueOf(enumType, s);
        return value;
    }

    @Override
    public String[] getStringArray(int index) {
        Object value = get(index);
        return _VariantFn.toStringArray(value);
    }

    @Override
    public String[] getStringArray(int index, String[] defaultValue) {
        Object value = get(index);
        if (value == null)
            return checkIndex(index) ? new String[] { null } : defaultValue;
        return _VariantFn.toStringArray(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = size();
        for (int i = 0; i < n; i++) {
            sb.append(i);
            sb.append(": ");
            sb.append(getString(i));
            sb.append('\n');
        }
        return super.toString();
    }

    @Override
    public Byte getByte(int index, Byte defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        byte val = getByte(index);
        return val;
    }

    @Override
    public Short getShort(int index, Short defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        short val = getShort(index);
        return val;
    }

    @Override
    public Integer getInt(int index, Integer defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        int val = getInt(index);
        return val;
    }

    @Override
    public Long getLong(int index, Long defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        long val = getLong(index);
        return val;
    }

    @Override
    public Float getFloat(int index, Float defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        float val = getFloat(index);
        return val;
    }

    @Override
    public Double getDouble(int index, Double defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        double val = getDouble(index);
        return val;
    }

    @Override
    public Boolean getBoolean(int index, Boolean defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        if (Literals.trueValues.contains(s))
            return true;
        return false;
    }

    @Override
    public Character getChar(int index, Character defaultValue) {
        String s = getString(index);
        if (s == null || s.isEmpty())
            return defaultValue;
        char val = getChar(index);
        return val;
    }

}
