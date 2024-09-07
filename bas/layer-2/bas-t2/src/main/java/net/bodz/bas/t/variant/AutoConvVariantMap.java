package net.bodz.bas.t.variant;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    public Date getDate(DateTimeFormatter format, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        DateVarConverter conv = new DateVarConverter(format);
        return conv.from(value);
    }

    @Override
    public Date getDate(DateTimeFormatter format, K key, Date defaultValue) {
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
//
//    @Override
//    public DateTime getDateTime(DateTimeFormatter formatter, K key) {
//        Object value = getScalar(key);
//        if (value == null)
//            return null;
//        InstantVarConverter conv = new InstantVarConverter(formatter);
//        return conv.from(value);
//    }
//
//    @Override
//    public DateTime getDateTime(DateTimeFormatter formatter, K key, DateTime defaultValue) {
//        Object value = getScalar(key);
//        if (value == null)
////            if (containsKey(key))
////                return null;
////            else
//            return defaultValue;
//
//        InstantVarConverter conv = new InstantVarConverter(formatter);
//        try {
//            return conv.from(value);
//        } catch (TypeConvertException e) {
//            return defaultValue;
//        }
//    }

    // Instant

    @Override
    public Instant getInstant(DateTimeFormatter formatter, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        InstantVarConverter conv = new InstantVarConverter(formatter);
        return conv.from(value);
    }

    @Override
    public Instant getInstant(DateTimeFormatter formatter, K key, Instant defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        InstantVarConverter conv = new InstantVarConverter(formatter);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Instant getInstant(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return InstantVarConverter.INSTANCE.from(value);
    }

    @Override
    public Instant getInstant(K key, Instant defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return InstantVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    // ZonedDateTime

    @Override
    public ZonedDateTime getZonedDateTime(DateTimeFormatter formatter, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        ZonedDateTimeVarConverter conv = new ZonedDateTimeVarConverter(formatter);
        return conv.from(value);
    }

    @Override
    public ZonedDateTime getZonedDateTime(DateTimeFormatter formatter, K key, ZonedDateTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        ZonedDateTimeVarConverter conv = new ZonedDateTimeVarConverter(formatter);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public ZonedDateTime getZonedDateTime(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return ZonedDateTimeVarConverter.INSTANCE.from(value);
    }

    @Override
    public ZonedDateTime getZonedDateTime(K key, ZonedDateTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return ZonedDateTimeVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    // OffsetDateTime

    @Override
    public OffsetDateTime getOffsetDateTime(DateTimeFormatter formatter, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        OffsetDateTimeVarConverter conv = new OffsetDateTimeVarConverter(formatter);
        return conv.from(value);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(DateTimeFormatter formatter, K key, OffsetDateTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        OffsetDateTimeVarConverter conv = new OffsetDateTimeVarConverter(formatter);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public OffsetDateTime getOffsetDateTime(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return OffsetDateTimeVarConverter.INSTANCE.from(value);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(K key, OffsetDateTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return OffsetDateTimeVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    // LocalDateTime

    @Override
    public LocalDateTime getLocalDateTime(DateTimeFormatter formatter, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        LocalDateTimeVarConverter conv = new LocalDateTimeVarConverter(formatter);
        return conv.from(value);
    }

    @Override
    public LocalDateTime getLocalDateTime(DateTimeFormatter formatter, K key, LocalDateTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        LocalDateTimeVarConverter conv = new LocalDateTimeVarConverter(formatter);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public LocalDateTime getLocalDateTime(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return LocalDateTimeVarConverter.INSTANCE.from(value);
    }

    @Override
    public LocalDateTime getLocalDateTime(K key, LocalDateTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return LocalDateTimeVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    // LocalDate

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
            return defaultValue;
        try {
            return LocalDateVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    // LocalTime

    @Override
    public LocalTime getLocalTime(DateTimeFormatter formatter, K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        LocalTimeVarConverter conv = new LocalTimeVarConverter(formatter);
        return conv.from(value);
    }

    @Override
    public LocalTime getLocalTime(DateTimeFormatter formatter, K key, LocalTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        LocalTimeVarConverter conv = new LocalTimeVarConverter(formatter);
        try {
            return conv.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public LocalTime getLocalTime(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return LocalTimeVarConverter.INSTANCE.from(value);
    }

    @Override
    public LocalTime getLocalTime(K key, LocalTime defaultValue) {
        Object value = getScalar(key);
        if (value == null)
            return defaultValue;
        try {
            return LocalTimeVarConverter.INSTANCE.from(value);
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
