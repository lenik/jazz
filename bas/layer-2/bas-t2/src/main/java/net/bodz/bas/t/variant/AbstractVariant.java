package net.bodz.bas.t.variant;

import java.lang.reflect.Array;

public abstract class AbstractVariant
        extends AutoConvVariant {

    @Override
    public String format(Object... args) {
        String format = getString();
        return String.format(format, args);
    }

    @Override
    public Object get(Object defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        return value;
    }

    @Override
    public final Object getScalar() {
        return getScalar(null);
    }

    @Override
    public Object getScalar(Object defaultValue) {
        Object value = get();
        if (value == null)
            return defaultValue;
        if (!value.getClass().isArray())
            return value;
        int length = Array.getLength(value);
        if (length == 0)
            return null;
        else
            return Array.get(value, 0);
    }

    @Override
    public final Object[] getArray() {
        return getArray(null);
    }

    @Override
    public Object[] getArray(Object[] defaultValue) {
        Object value = get();
        if (value == null)
            return defaultValue;
        if (value.getClass().isArray())
            return (Object[]) value;
        else
            return new Object[] { value };
    }

    @Override
    public String getString() {
        Object value = getScalar();
        return value == null ? null : value.toString();
    }

    @Override
    public String getString(String defaultString) {
        Object value = getScalar();
        if (value == null)
            return defaultString;
        return value.toString();
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType) {
        return getEnum(enumType, null);
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType, T defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        T value = Enum.valueOf(enumType, s);
        return value;
    }

    @Override
    public String[] getStringArray() {
        return getStringArray(null);
    }

    @Override
    public String[] getStringArray(String[] defaultValue) {
        Object value = get();
        if (value == null)
            return defaultValue;
        return _VariantFn.toStringArray(value);
    }

    @Override
    public String toString() {
        return getString();
    }

    @Override
    public Byte getByte(Byte defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        byte val = getByte();
        return val;
    }

    @Override
    public Short getShort(Short defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        short val = getShort();
        return val;
    }

    @Override
    public Integer getInt(Integer defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        int val = getInt();
        return val;
    }

    @Override
    public Long getLong(Long defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        long val = getLong();
        return val;
    }

    @Override
    public Float getFloat(Float defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        float val = getFloat();
        return val;
    }

    @Override
    public Double getDouble(Double defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        double val = getDouble();
        return val;
    }

    @Override
    public Boolean getBoolean(Boolean defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        if (Literals.trueValues.contains(s))
            return true;
        return false;
    }

    @Override
    public Character getChar(Character defaultValue) {
        String s = getString();
        if (s == null || s.isEmpty())
            return defaultValue;
        char val = getChar();
        return val;
    }

}
