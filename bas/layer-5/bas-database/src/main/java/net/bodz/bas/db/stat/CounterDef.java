package net.bodz.bas.db.stat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.bas.util.primitive.Primitives;

public abstract class CounterDef<T>
        extends AbstractElement
        implements ICounterDef<T> {

    private static final long serialVersionUID = 1L;

    private final Class<T> valueType;
    private final T initValue;
    private final int precision;
    private final int scale;
    private final String unit;
    private final SubCounterMode subCounterMode = SubCounterMode.sumUp;

    public CounterDef(String name, Class<T> valueType, T initValue, int precision, int scale) {
        this(name, valueType, initValue, precision, scale, null);
    }

    public CounterDef(String name, Class<T> valueType, T initValue, int precision, int scale, String unit) {
        super(name);

        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;

        if (initValue == null)
            throw new NullPointerException("initValue");
        this.initValue = initValue;

        this.precision = precision;
        this.scale = scale;
        this.unit = unit;
    }

    @Override
    public Class<T> getValueType() {
        return valueType;
    }

    @Override
    public T getInitValue() {
        return initValue;
    }

    @Override
    public int getPrecision() {
        return precision;
    }

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public SubCounterMode getSubCounterMode() {
        return subCounterMode;
    }

    @Override
    public ICounter<T> createCounter(String name) {
        return new Counter<T>(this, name, initValue);
    }

    @Override
    public ICounter<T> createSubCounter(ICounter<?> _parent, String name) {
        if (_parent == null)
            throw new NullPointerException("parent");

        @SuppressWarnings("unchecked")//
        ICounter<T> parent = (ICounter<T>) _parent;

        switch (subCounterMode) {
        case init:
            return new Counter<T>(this, name, initValue);

        case setUp:
            return new SetUpCounter<T>(parent, initValue);

        case sumUp:
            return new SumUpCounter<T>(parent, initValue);

        case averageUp:
        default:
            throw new NotImplementedException();
        }
    }

    public static final ByteCounterDef GENERIC_BYTE = new ByteCounterDef("generic");
    public static final ShortCounterDef GENERIC_SHORT = new ShortCounterDef("generic");
    public static final IntegerCounterDef GENERIC_INTEGER = new IntegerCounterDef("generic");
    public static final LongCounterDef GENERIC_LONG = new LongCounterDef("generic");
    public static final FloatCounterDef GENERIC_FLOAT = new FloatCounterDef("generic");
    public static final DoubleCounterDef GENERIC_DOUBLE = new DoubleCounterDef("generic");
    public static final BigIntegerCounterDef GENERIC_BIG_INTEGER = new BigIntegerCounterDef("generic");
    public static final BigDecimalCounterDef GENERIC_BIG_DECIMAL = new BigDecimalCounterDef("generic",
            MathContext.UNLIMITED);

    private static final int keyByte = 1;
    private static final int keyShort = 2;
    private static final int keyInteger = 3;
    private static final int keyLong = 4;
    private static final int keyFloat = 5;
    private static final int keyDouble = 6;
    private static final int keyBigInteger = 7;
    private static final int keyBigDecimal = 8;

    static Map<Class<?>, Integer> typeMap;
    static {
        typeMap = new HashMap<>();
        typeMap.put(byte.class, keyByte);
        typeMap.put(short.class, keyShort);
        typeMap.put(int.class, keyInteger);
        typeMap.put(long.class, keyLong);
        typeMap.put(float.class, keyFloat);
        typeMap.put(double.class, keyDouble);
        typeMap.put(BigInteger.class, keyBigInteger);
        typeMap.put(BigDecimal.class, keyBigDecimal);
    }

    @SuppressWarnings("unchecked")
    public static <T> ICounterDef<T> getGenericDef(Class<T> valueType) {
        Class<?> unboxed = Primitives.unbox(valueType);
        Integer keyIndex = typeMap.get(unboxed);

        ICounterDef<?> def = null;
        if (keyIndex != null)
            switch (keyIndex) {
            case keyByte:
                def = GENERIC_BYTE;
                break;
            case keyShort:
                def = GENERIC_SHORT;
                break;
            case keyInteger:
                def = GENERIC_INTEGER;
                break;
            case keyLong:
                def = GENERIC_LONG;
                break;
            case keyFloat:
                def = GENERIC_FLOAT;
                break;
            case keyDouble:
                def = GENERIC_DOUBLE;
                break;
            case keyBigInteger:
                def = GENERIC_BIG_INTEGER;
                break;
            case keyBigDecimal:
                def = GENERIC_BIG_DECIMAL;
                break;
            }

        return (ICounterDef<T>) def;
    }

    @SuppressWarnings("unchecked")
    public static <T> ICounterDef<T> create(String name, Class<T> valueType) {
        Class<?> unboxed = Primitives.unbox(valueType);
        Integer keyIndex = typeMap.get(unboxed);

        ICounterDef<?> def = null;
        if (keyIndex != null)
            switch (keyIndex) {
            case keyByte:
                def = new ByteCounterDef(name);
                break;
            case keyShort:
                def = new ShortCounterDef(name);
                break;
            case keyInteger:
                def = new IntegerCounterDef(name);
                break;
            case keyLong:
                def = new LongCounterDef(name);
                break;
            case keyFloat:
                def = new FloatCounterDef(name);
                break;
            case keyDouble:
                def = new DoubleCounterDef(name);
                break;
            case keyBigInteger:
                def = new BigIntegerCounterDef(name);
                break;
            case keyBigDecimal:
                def = new BigDecimalCounterDef(name, MathContext.UNLIMITED);
                break;
            }

        if (def == null)
            throw new IllegalArgumentException("Not supported counter value type: " + valueType);
        else
            return (ICounterDef<T>) def;
    }

}

class ByteCounterDef
        extends CounterDef<Byte> {

    private static final long serialVersionUID = 1L;

    public ByteCounterDef(String name) {
        super(name, Byte.class, (byte) 0, 3, 0);
    }

    public ByteCounterDef(String name, byte initValue) {
        super(name, Byte.class, initValue, 3, 0);
    }

    @Override
    public Byte add(Byte a, Byte b) {
        return (byte) (a + b);
    }

    @Override
    public Byte sub(Byte a, Byte b) {
        return (byte) (a - b);
    }

    @Override
    public Byte incr(Byte a) {
        return (byte) (a + 1);
    }

    @Override
    public Byte decr(Byte a) {
        return (byte) (a - 1);
    }

    @Override
    public Byte multiply(Byte a, int k) {
        return (byte) (a * k);
    }

    @Override
    public Byte multiply(Byte a, double k) {
        return (byte) Math.round(a * k);
    }

    @Override
    public Byte divide(Byte a, int k) {
        return (byte) (a / k);
    }

    @Override
    public Byte divide(Byte a, double k) {
        return (byte) Math.round(a / k);
    }

}

class ShortCounterDef
        extends CounterDef<Short> {

    private static final long serialVersionUID = 1L;

    public ShortCounterDef(String name) {
        super(name, Short.class, (short) 0, 5, 0);
    }

    public ShortCounterDef(String name, short initValue) {
        super(name, Short.class, initValue, 5, 0);
    }

    @Override
    public Short add(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    public Short sub(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    public Short incr(Short a) {
        return (short) (a + 1);
    }

    @Override
    public Short decr(Short a) {
        return (short) (a - 1);
    }

    @Override
    public Short multiply(Short a, int k) {
        return (short) (a * k);
    }

    @Override
    public Short multiply(Short a, double k) {
        return (short) Math.round(a * k);
    }

    @Override
    public Short divide(Short a, int k) {
        return (short) (a / k);
    }

    @Override
    public Short divide(Short a, double k) {
        return (short) Math.round(a / k);
    }

}

class IntegerCounterDef
        extends CounterDef<Integer> {

    private static final long serialVersionUID = 1L;

    public IntegerCounterDef(String name) {
        super(name, Integer.class, 0, 10, 0);
    }

    public IntegerCounterDef(String name, int initValue) {
        super(name, Integer.class, initValue, 10, 0);
    }

    @Override
    public Integer add(Integer a, Integer b) {
        return (a + b);
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return (a - b);
    }

    @Override
    public Integer incr(Integer a) {
        return (a + 1);
    }

    @Override
    public Integer decr(Integer a) {
        return (a - 1);
    }

    @Override
    public Integer multiply(Integer a, int k) {
        return a * k;
    }

    @Override
    public Integer multiply(Integer a, double k) {
        return (int) Math.round(a * k);
    }

    @Override
    public Integer divide(Integer a, int k) {
        return a / k;
    }

    @Override
    public Integer divide(Integer a, double k) {
        return (int) Math.round(a / k);
    }

}

class LongCounterDef
        extends CounterDef<Long> {

    private static final long serialVersionUID = 1L;

    // unsigned: 18,446,744,073,709,551,616 (20 digits)
    // signed: 9,223,372,036,854,775,808 (19 digits)
    public LongCounterDef(String name) {
        super(name, Long.class, 0L, 19, 0);
    }

    public LongCounterDef(String name, long initValue) {
        super(name, Long.class, initValue, 19, 0);
    }

    @Override
    public Long add(Long a, Long b) {
        return (long) (a + b);
    }

    @Override
    public Long sub(Long a, Long b) {
        return (long) (a - b);
    }

    @Override
    public Long incr(Long a) {
        return (long) (a + 1);
    }

    @Override
    public Long decr(Long a) {
        return (long) (a - 1);
    }

    @Override
    public Long multiply(Long a, int k) {
        return (long) (a * k);
    }

    @Override
    public Long multiply(Long a, double k) {
        return (long) Math.round(a * k);
    }

    @Override
    public Long divide(Long a, int k) {
        return (long) (a / k);
    }

    @Override
    public Long divide(Long a, double k) {
        return (long) Math.round(a / k);
    }

}

class FloatCounterDef
        extends CounterDef<Float> {

    private static final long serialVersionUID = 1L;

    public FloatCounterDef(String name) {
        super(name, Float.class, 0.0f, 10, 3);
    }

    public FloatCounterDef(String name, float initValue) {
        super(name, Float.class, initValue, 10, 3);
    }

    @Override
    public Float add(Float a, Float b) {
        return (float) (a + b);
    }

    @Override
    public Float sub(Float a, Float b) {
        return (float) (a - b);
    }

    @Override
    public Float incr(Float a) {
        return (float) (a + 1.0f);
    }

    @Override
    public Float decr(Float a) {
        return (float) (a - 1.0f);
    }

    @Override
    public Float multiply(Float a, int k) {
        return (float) (a * k);
    }

    @Override
    public Float multiply(Float a, double k) {
        return (float) (a * k);
    }

    @Override
    public Float divide(Float a, int k) {
        return (float) (a / k);
    }

    @Override
    public Float divide(Float a, double k) {
        return (float) (a / k);
    }

}

class DoubleCounterDef
        extends CounterDef<Double> {

    private static final long serialVersionUID = 1L;

    public DoubleCounterDef(String name) {
        super(name, Double.class, 0.0, 20, 6);
    }

    public DoubleCounterDef(String name, double initValue) {
        super(name, Double.class, initValue, 20, 6);
    }

    @Override
    public Double add(Double a, Double b) {
        return (double) (a + b);
    }

    @Override
    public Double sub(Double a, Double b) {
        return (double) (a - b);
    }

    @Override
    public Double incr(Double a) {
        return (double) (a + 1.0);
    }

    @Override
    public Double decr(Double a) {
        return (double) (a - 1.0);
    }

    @Override
    public Double multiply(Double a, int k) {
        return (double) (a * k);
    }

    @Override
    public Double multiply(Double a, double k) {
        return (double) (a * k);
    }

    @Override
    public Double divide(Double a, int k) {
        return (double) (a / k);
    }

    @Override
    public Double divide(Double a, double k) {
        return (double) (a / k);
    }

}

class BigIntegerCounterDef
        extends CounterDef<BigInteger> {

    private static final long serialVersionUID = 1L;

    public BigIntegerCounterDef(String name) {
        super(name, BigInteger.class, BigInteger.ZERO, 0, 0);
    }

    public BigIntegerCounterDef(String name, BigInteger initValue) {
        super(name, BigInteger.class, initValue, 0, 6);
    }

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger sub(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger incr(BigInteger a) {
        return a.add(BigInteger.ONE);
    }

    @Override
    public BigInteger decr(BigInteger a) {
        return a.subtract(BigInteger.ONE);
    }

    @Override
    public BigInteger multiply(BigInteger a, int k) {
        return a.multiply(BigInteger.valueOf(k));
    }

    @Override
    public BigInteger multiply(BigInteger a, double k) {
        BigDecimal _a = new BigDecimal(a);
        BigDecimal result = _a.multiply(BigDecimal.valueOf(k));
        return result.toBigInteger();
    }

    @Override
    public BigInteger divide(BigInteger a, int k) {
        return a.divide(BigInteger.valueOf(k));
    }

    @Override
    public BigInteger divide(BigInteger a, double k) {
        BigDecimal _a = new BigDecimal(a);
        BigDecimal result = _a.divide(BigDecimal.valueOf(k));
        return result.toBigInteger();
    }

}

class BigDecimalCounterDef
        extends CounterDef<BigDecimal> {

    private static final long serialVersionUID = 1L;

    public BigDecimalCounterDef(String name, MathContext mathContext) {
        super(name, BigDecimal.class, BigDecimal.ZERO, mathContext.getPrecision(), 0);
    }

    public BigDecimalCounterDef(String name, BigDecimal initValue) {
        super(name, BigDecimal.class, initValue, //
                MathContext.UNLIMITED.getPrecision(), 0);
    }

    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Override
    public BigDecimal sub(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    @Override
    public BigDecimal incr(BigDecimal a) {
        return a.add(BigDecimal.ONE);
    }

    @Override
    public BigDecimal decr(BigDecimal a) {
        return a.subtract(BigDecimal.ONE);
    }

    @Override
    public BigDecimal multiply(BigDecimal a, int k) {
        return a.multiply(BigDecimal.valueOf(k));
    }

    @Override
    public BigDecimal multiply(BigDecimal a, double k) {
        return a.multiply(BigDecimal.valueOf(k));
    }

    @Override
    public BigDecimal divide(BigDecimal a, int k) {
        return a.divide(BigDecimal.valueOf(k));
    }

    @Override
    public BigDecimal divide(BigDecimal a, double k) {
        return a.divide(BigDecimal.valueOf(k));
    }

}
