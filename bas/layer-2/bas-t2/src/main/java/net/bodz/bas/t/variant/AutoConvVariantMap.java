package net.bodz.bas.t.variant;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import net.bodz.bas.c.object.Enums;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.*;

// @GeneratedBy(cg.VariantMapGenerator.class)
public abstract class AutoConvVariantMap<K>
        implements
            IVariantMap<K> {

    @Override
    public Byte getByte(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return ByteVarConverter.INSTANCE.from(value);
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return ByteVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Short getShort(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return ShortVarConverter.INSTANCE.from(value);
    }

    @Override
    public short getShort(K key, short defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return ShortVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Integer getInt(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return IntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public int getInt(K key, int defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return IntegerVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Long getLong(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return LongVarConverter.INSTANCE.from(value);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return LongVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Float getFloat(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return FloatVarConverter.INSTANCE.from(value);
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return FloatVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Double getDouble(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return DoubleVarConverter.INSTANCE.from(value);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return DoubleVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Boolean getBoolean(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return BooleanVarConverter.INSTANCE.from(value);
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return BooleanVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Character getChar(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return CharacterVarConverter.INSTANCE.from(value);
    }

    @Override
    public char getChar(K key, char defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return CharacterVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return BigIntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            // if (containsKey(key))
            // return null;
            // else
            return defaultValue;
        try {
            return BigIntegerVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return BigDecimalVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;
        try {
            return BigDecimalVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate(DateFormat format, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        DateVarConverter conv = new DateVarConverter(format);
        return conv.from(value);
    }

    @Override
    public Date getDate(DateFormat format, K key, Date defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;

        DateVarConverter conv = new DateVarConverter(format);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return DateVarConverter.INSTANCE.from(value);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;
        try {
            return DateVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public DateTime getDateTime(DateTimeFormatter formatter, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        DateTimeVarConverter conv = new DateTimeVarConverter(formatter);
        return conv.from(value);
    }

    @Override
    public DateTime getDateTime(DateTimeFormatter formatter, K key, DateTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;

        DateTimeVarConverter conv = new DateTimeVarConverter(formatter);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public LocalDate getLocalDate(DateTimeFormatter formatter, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        LocalDateVarConverter conv = new LocalDateVarConverter(formatter);
        return conv.from(value);
    }

    @Override
    public LocalDate getLocalDate(DateTimeFormatter formatter, K key, LocalDate defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;

        LocalDateVarConverter conv = new LocalDateVarConverter(formatter);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public LocalDate getLocalDate(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return LocalDateVarConverter.INSTANCE.from(value);
    }

    @Override
    public LocalDate getLocalDate(K key, LocalDate defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;
        try {
            return LocalDateVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public File getFile(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return FileConverter.INSTANCE.from(value);
    }

    @Override
    public File getFile(K key, File defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;
        try {
            return FileConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> type, K key) {
        return getEnum(type, key, null);
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> type, K key, T defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;
        Object enumKey = get(key);
        if (enumKey == null)
            return null;

        T enumVal = Enums.valueOf(type, enumKey, defaultValue);
        return enumVal;
    }

    @Override
    public <T> T getAny(Class<T> type, K key) {
        return getAny(type, key, null);
    }

    @Override
    public <T> T getAny(Class<T> type, K key, T defaultValue) {
        Object value = getScalar(key);
        if (value == null)
//            if (containsKey(key))
//                return null;
//            else
            return defaultValue;
        Object val = get(key);
        IVarConverter<Object> converter = VarConverters.getConverter(type);
        if (converter == null)
            throw new UnsupportedOperationException(String.format(//
                    "No converter for class %s.", type.getName()));
        // try {
        Object converted = converter.from(val);
        // } catch (TypeConvertException e) {
        // throw new JSONException(e);
        // }
        T var = type.cast(converted);
        return var;
    }

}
