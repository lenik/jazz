package net.bodz.bas.t.variant.conv;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.err.TypeConvertException;

public abstract class AbstractVarConverter<T>
        implements
            IVarConverter<T> {

    protected final Class<T> type;
    final Map<Class<?>, Function<Object, T>> frommap = new HashMap<Class<?>, Function<Object, T>>();
    final Map<Class<?>, Function<T, ?>> tomap = new HashMap<Class<?>, Function<T, ?>>();
    final Set<IVarConverterExtension<T>> extensions;

    public AbstractVarConverter(Class<T> type) {
        this.type = type;

        frommap.put(type, new CastTr());
        frommap.put(String.class, new FromStringTr());
        frommap.put(Number.class, new FromNumberTr());
        frommap.put(byte[].class, new FromByteArrayTr());
        frommap.put(String[].class, new FromStringArrayTr());

        tomap.put(Byte.class, new ToByteTr());
        tomap.put(Short.class, new ToShortTr());
        tomap.put(Integer.class, new ToIntTr());
        tomap.put(Long.class, new ToLongTr());

        tomap.put(type, Function.identity());
        tomap.put(Float.class, new ToFloatTr());
        tomap.put(Double.class, new ToDoubleTr());
        tomap.put(Boolean.class, new ToBooleanTr());
        tomap.put(Character.class, new ToCharTr());
        tomap.put(String.class, new ToStringTr());
        tomap.put(BigInteger.class, new ToBigIntegerTr());
        tomap.put(BigDecimal.class, new ToBigDecimalTr());
        tomap.put(Instant.class, new ToInstantTr());
        tomap.put(LocalDateTime.class, new ToLocalDateTimeTr());
        tomap.put(LocalDate.class, new ToLocalDateTr());
        tomap.put(LocalTime.class, new ToLocalTimeTr());

        extensions = VarConverterExtensions.getExtensions(type);
        Iterator<IVarConverterExtension<T>> it = extensions.iterator();
        while (it.hasNext()) {
            IVarConverterExtension<T> ext = it.next();

            Map<Class<?>, Function<Object, T>> fm = ext.getFromMap();
            for (Class<?> t : fm.keySet())
                if (! frommap.containsKey(t))
                    frommap.put(t, fm.get(t));

            Map<Class<?>, Function<T, Object>> tm = ext.getToMap();
            for (Class<?> t : tm.keySet())
                if (! tomap.containsKey(t))
                    tomap.put(t, tm.get(t));
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean canConvertFrom(Class<?> type) {
        Class<?> sup = type;
        while (sup != null) {
            if (frommap.containsKey(sup))
                return true;
            sup = sup.getSuperclass();
        }

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertFrom(type))
                return true;

        return false;
    }

    @Override
    public boolean canConvertTo(Class<?> type) {
        if (tomap.containsKey(type))
            return true;

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertTo(type))
                return true;

        return false;
    }

    @Override
    public final T from(Object obj)
            throws TypeConvertException {
        if (obj == null)
            return null;
        else
            return from(obj.getClass(), obj);
    }

    @Override
    public final T from(Class<?> type, Object obj)
            throws TypeConvertException {
        if (obj == null)
            throw new NullPointerException("in");
        if (this.type == type)
            return this.type.cast(obj);

        Class<?> sup = type;
        while (sup != null) {
            Function<Object, T> fn = frommap.get(sup);
            if (fn != null)
                return fn.apply(obj);
            sup = sup.getSuperclass();
        }

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertFrom(type))
                return ext.convertFrom(type, obj);

        return fromImpl(type, obj);
    }

    protected T fromImpl(Class<?> type, Object in) {
        throw new IllegalArgumentException(String.format(//
                "Can't convert to %s from %s. ", getType(), type));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> U to(T value, Class<U> type)
            throws TypeConvertException {
        if (type == null)
            throw new NullPointerException("type");
        if (this.type == type)
            return type.cast(value);

        Class<?> boxedType = Primitives.box(type);
        Function<T, ?> fn = tomap.get(boxedType);
        if (fn != null)
            return (U) fn.apply(value);

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertTo(type))
                return ext.convertTo(value, type);

        return toImpl(value, type);
    }

    protected <U> U toImpl(T value, Class<U> type)
            throws TypeConvertException {
        throw new IllegalArgumentException("Can't convert to this type: " + type);
    }

    @Override
    public T fromByteArray(byte[] in) {
        try {
            String s = new String(in, "utf-8");
            return fromString(s);
        } catch (UnsupportedEncodingException e) {
            throw new TypeConvertException("Can't decode utf-8 string.", e);
        }
    }

    @Override
    public T fromStringArray(String[] in) {
        if (in == null)
            throw new NullPointerException("in");
        if (in.length == 0)
            return null;
        String last = in[in.length - 1];
        return fromString(last);
    }

    @Override
    public byte toByte(T value) {
        return toNumber(value).byteValue();
    }

    @Override
    public short toShort(T value) {
        return toNumber(value).shortValue();
    }

    @Override
    public int toInt(T value) {
        return toNumber(value).intValue();
    }

    @Override
    public long toLong(T value) {
        return toNumber(value).longValue();
    }

    @Override
    public float toFloat(T value) {
        return toNumber(value).floatValue();
    }

    @Override
    public double toDouble(T value) {
        return toNumber(value).doubleValue();
    }

    @Override
    public char toChar(T value) {
        String s = toString(value);
        if (s == null)
            return 0;
        else if (s.isEmpty())
            return 0;
        else
            return s.charAt(0);
    }

    @Override
    public String toString(T value) {
        if (value == null)
            return null;
        return value.toString();
    }

    @Override
    public BigInteger toBigInteger(T value) {
        long lval = toLong(value);
        return BigInteger.valueOf(lval);
    }

    @Override
    public BigDecimal toBigDecimal(T value) {
        double fval = toDouble(value);
        return BigDecimal.valueOf(fval);
    }

    @Override
    public Instant toInstant(T value) {
        long epochMilli = toLong(value);
        return Instant.ofEpochMilli(epochMilli);
    }

    @Override
    public LocalDateTime toLocalDateTime(T value) {
        Instant instant = toInstant(value);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    @Override
    public LocalDate toLocalDate(T value) {
        Instant instant = toInstant(value);
        return LocalDate.ofInstant(instant, ZoneId.systemDefault());
    }

    @Override
    public LocalTime toLocalTime(T value) {
        Instant instant = toInstant(value);
        return LocalTime.ofInstant(instant, ZoneId.systemDefault());
    }

    class CastTr
            implements
                Function<Object, T> {
        @Override
        public T apply(Object input) {
            return AbstractVarConverter.this.type.cast(input);
        }
    }

    class FromStringTr
            implements
                Function<Object, T> {
        @Override
        public T apply(Object input) {
            return fromString((String) input);
        };
    }

    class FromNumberTr
            implements
                Function<Object, T> {
        @Override
        public T apply(Object input) {
            return fromNumber((Number) input);
        };
    }

    class FromByteArrayTr
            implements
                Function<Object, T> {
        @Override
        public T apply(Object input) {
            return fromByteArray((byte[]) input);
        };
    }

    class FromStringArrayTr
            implements
                Function<Object, T> {
        @Override
        public T apply(Object input) {
            return fromStringArray((String[]) input);
        };
    }

    class ToByteTr
            implements
                Function<T, Byte> {
        @Override
        public Byte apply(T input) {
            return toByte(input);
        }
    }

    class ToShortTr
            implements
                Function<T, Short> {
        @Override
        public Short apply(T input) {
            return toShort(input);
        }
    }

    class ToIntTr
            implements
                Function<T, Integer> {
        @Override
        public Integer apply(T input) {
            return toInt(input);
        }
    }

    class ToLongTr
            implements
                Function<T, Long> {
        @Override
        public Long apply(T input) {
            return toLong(input);
        }
    }

    class ToFloatTr
            implements
                Function<T, Float> {
        @Override
        public Float apply(T input) {
            return toFloat(input);
        }
    }

    class ToDoubleTr
            implements
                Function<T, Double> {
        @Override
        public Double apply(T input) {
            return toDouble(input);
        }
    }

    class ToBooleanTr
            implements
                Function<T, Boolean> {
        @Override
        public Boolean apply(T input) {
            return toBoolean(input);
        }
    }

    class ToCharTr
            implements
                Function<T, Character> {
        @Override
        public Character apply(T input) {
            return toChar(input);
        }
    }

    class ToStringTr
            implements
                Function<T, String> {
        @Override
        public String apply(T input) {
            return AbstractVarConverter.this.toString(input);
        }
    }

    class ToBigIntegerTr
            implements
                Function<T, BigInteger> {
        @Override
        public BigInteger apply(T input) {
            return toBigInteger(input);
        }
    }

    class ToBigDecimalTr
            implements
                Function<T, BigDecimal> {
        @Override
        public BigDecimal apply(T input) {
            return toBigDecimal(input);
        }
    }

    class ToInstantTr
            implements
                Function<T, Instant> {
        @Override
        public Instant apply(T input) {
            return toInstant(input);
        }
    }

    class ToLocalDateTimeTr
            implements
                Function<T, LocalDateTime> {
        @Override
        public LocalDateTime apply(T input) {
            return toLocalDateTime(input);
        }
    }

    class ToLocalDateTr
            implements
                Function<T, LocalDate> {
        @Override
        public LocalDate apply(T input) {
            return toLocalDate(input);
        }
    }

    class ToLocalTimeTr
            implements
                Function<T, LocalTime> {
        @Override
        public LocalTime apply(T input) {
            return toLocalTime(input);
        }
    }

}
