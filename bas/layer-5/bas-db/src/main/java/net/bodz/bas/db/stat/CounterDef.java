package net.bodz.bas.db.stat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.i18n.dom1.AbstractElement;

public abstract class CounterDef<T extends Number>
        extends AbstractElement
        implements ICounterDef<T> {

    private static final long serialVersionUID = 1L;

    private String name;
    private int userLevel;

    private final Class<T> valueType;
    private final T zero;
    private final T one;

    private int precision;
    private int scale;
    private String unit;

    private SubCounterMode subCounterMode = SubCounterMode.sumUp;

    public CounterDef(String name, Class<T> valueType, T zero, T one) {
        this.name = name;

        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;

        if (zero == null)
            throw new NullPointerException("initValue");
        this.zero = zero;

        if (one == null)
            throw new NullPointerException("one");
        this.one = one;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getVerboseLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public Class<T> getValueType() {
        return valueType;
    }

    public boolean appxEquals(T a, T b) {
        return a.equals(b);
    }

    @Override
    public T getZero() {
        return zero;
    }

    @Override
    public T getOne() {
        return one;
    }

    @Override
    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Override
    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public SubCounterMode getSubCounterMode() {
        return subCounterMode;
    }

    public void setSubCounterMode(SubCounterMode subCounterMode) {
        if (subCounterMode == null)
            throw new NullPointerException("subCounterMode");
        this.subCounterMode = subCounterMode;
    }

    @Override
    public ICounter<T> createCounter(String name) {
        return new Counter<T>(this, name, zero);
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
    public static <T extends Number> ICounterDef<T> getGenericDef(Class<T> valueType) {
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
    public static <T extends Number> ICounterDef<T> create(String name, Class<T> valueType) {
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
        super(name, Byte.class, (byte) 0, (byte) 1);
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

    @Override
    public Byte min(Byte a, Byte b) {
        return a <= b ? a : b;
    }

    @Override
    public Byte max(Byte a, Byte b) {
        return a >= b ? a : b;
    }
}

class ShortCounterDef
        extends CounterDef<Short> {

    private static final long serialVersionUID = 1L;

    public ShortCounterDef(String name) {
        super(name, Short.class, (short) 0, (short) 1);
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

    @Override
    public Short min(Short a, Short b) {
        return a <= b ? a : b;
    }

    @Override
    public Short max(Short a, Short b) {
        return a >= b ? a : b;
    }

}

class IntegerCounterDef
        extends CounterDef<Integer> {

    private static final long serialVersionUID = 1L;

    public IntegerCounterDef(String name) {
        super(name, Integer.class, 0, 1);
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

    @Override
    public Integer min(Integer a, Integer b) {
        return a <= b ? a : b;
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return a >= b ? a : b;
    }

}

class LongCounterDef
        extends CounterDef<Long> {

    private static final long serialVersionUID = 1L;

    // unsigned: 18,446,744,073,709,551,616 (20 digits)
    // signed: 9,223,372,036,854,775,808 (19 digits)
    public LongCounterDef(String name) {
        super(name, Long.class, 0L, 1L);
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

    @Override
    public Long min(Long a, Long b) {
        return a <= b ? a : b;
    }

    @Override
    public Long max(Long a, Long b) {
        return a >= b ? a : b;
    }

}

class FloatCounterDef
        extends CounterDef<Float> {

    private static final long serialVersionUID = 1L;

    static final float epsilon = Float.MIN_VALUE * 10;

    public FloatCounterDef(String name) {
        super(name, Float.class, 0.0f, 1.0f);
    }

    @Override
    public boolean appxEquals(Float a, Float b) {
        float diff = Math.abs(a - b);
        return diff < epsilon;
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

    @Override
    public Float min(Float a, Float b) {
        return a <= b ? a : b;
    }

    @Override
    public Float max(Float a, Float b) {
        return a >= b ? a : b;
    }

}

class DoubleCounterDef
        extends CounterDef<Double> {

    private static final long serialVersionUID = 1L;

    static final double epsilon = Double.MIN_VALUE * 10;

    public DoubleCounterDef(String name) {
        super(name, Double.class, 0.0, 1.0);
    }

    @Override
    public boolean appxEquals(Double a, Double b) {
        double diff = Math.abs(a - b);
        return diff < epsilon;
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

    @Override
    public Double min(Double a, Double b) {
        return a <= b ? a : b;
    }

    @Override
    public Double max(Double a, Double b) {
        return a >= b ? a : b;
    }

}

class BigIntegerCounterDef
        extends CounterDef<BigInteger> {

    private static final long serialVersionUID = 1L;

    public BigIntegerCounterDef(String name) {
        super(name, BigInteger.class, BigInteger.ZERO, BigInteger.ONE);
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

    @Override
    public BigInteger min(BigInteger a, BigInteger b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    @Override
    public BigInteger max(BigInteger a, BigInteger b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

}

class BigDecimalCounterDef
        extends CounterDef<BigDecimal> {

    private static final long serialVersionUID = 1L;

    public BigDecimalCounterDef(String name, MathContext mathContext) {
        super(name, BigDecimal.class, BigDecimal.ZERO, BigDecimal.ONE);
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

    @Override
    public BigDecimal min(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    @Override
    public BigDecimal max(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

}
