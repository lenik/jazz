package net.bodz.bas.t.variant;

import java.lang.reflect.Array;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;

public abstract class AbstractVariant
        extends AutoConvVariant {

    @Override
    public String format(Object... args) {
        String format = getString();
        return String.format(format, args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(T defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        return (T) value;
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
        if (! value.getClass().isArray())
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

    @SuppressWarnings({ "unchecked" })
    public <T> T convert(Class<? super T> type) {
        switch (TypeKind.getTypeId(type)) {
        case TypeId._byte:
        case TypeId.BYTE:
            return (T) (Byte) getByte();

        case TypeId._short:
        case TypeId.SHORT:
            return (T) (Short) getShort();

        case TypeId._int:
        case TypeId.INTEGER:
            return (T) (Integer) getInt();

        case TypeId._long:
        case TypeId.LONG:
            return (T) (Long) getLong();

        case TypeId._float:
        case TypeId.FLOAT:
            return (T) (Float) getFloat();

        case TypeId._double:
        case TypeId.DOUBLE:
            return (T) (Double) getDouble();

        case TypeId.BIG_INTEGER:
            return (T) getBigInteger();

        case TypeId.BIG_DECIMAL:
            return (T) getBigDecimal();

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            return (T) (Boolean) getBoolean();

        case TypeId._char:
        case TypeId.CHARACTER:
            return (T) (Character) getChar();

        case TypeId.STRING:
            return (T) getString();

        case TypeId.DATE:
        case TypeId.SQL_DATE:
            return (T) getDate();
//
//        case TypeId.JODA_DATETIME:
//            throw new NotImplementedException();

        case TypeId.INSTANT:
            return (T) getInstant();

        case TypeId.ZONED_DATE_TIME:
            return (T) getZonedDateTime();

        case TypeId.OFFSET_DATE_TIME:
            return (T) getOffsetDateTime();

        case TypeId.LOCAL_DATE_TIME:
            return (T) getLocalDateTime();

        case TypeId.LOCAL_DATE:
            return (T) getLocalDate();

        case TypeId.LOCAL_TIME:
            return (T) getLocalTime();

        default:
            return (T) get();
        }
    }

}
