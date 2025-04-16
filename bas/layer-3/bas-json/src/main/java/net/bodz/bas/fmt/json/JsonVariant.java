package net.bodz.bas.fmt.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.variant.conv.BigDecimalVarConverter;
import net.bodz.bas.t.variant.conv.BigIntegerVarConverter;
import net.bodz.bas.t.variant.conv.BooleanVarConverter;
import net.bodz.bas.t.variant.conv.ByteVarConverter;
import net.bodz.bas.t.variant.conv.DoubleVarConverter;
import net.bodz.bas.t.variant.conv.FloatVarConverter;
import net.bodz.bas.t.variant.conv.IntegerVarConverter;
import net.bodz.bas.t.variant.conv.LocalDateTimeVarConverter;
import net.bodz.bas.t.variant.conv.LocalDateVarConverter;
import net.bodz.bas.t.variant.conv.LocalTimeVarConverter;
import net.bodz.bas.t.variant.conv.LongVarConverter;
import net.bodz.bas.t.variant.conv.OffsetDateTimeVarConverter;
import net.bodz.bas.t.variant.conv.OffsetTimeVarConverter;
import net.bodz.bas.t.variant.conv.ShortVarConverter;
import net.bodz.bas.t.variant.conv.StringVarConverter;
import net.bodz.bas.t.variant.conv.ZonedDateTimeVarConverter;

public final class JsonVariant {

    final JsonVariantType type;
    final Object value;

    public JsonVariant(JsonVariantType type, Object value) {
        this.type = value == null ? JsonVariantType.NULL : type;
        this.value = value;
    }

    public JsonVariant(Object value) {
        this.type = JsonVariantType.getType(value);
        this.value = value;
    }

    public JsonVariant(JsonObject value) {
        this(JsonVariantType.OBJECT, value);
    }

    public JsonVariant(JsonArray value) {
        this(JsonVariantType.ARRAY, value);
    }

    public static JsonVariant of(Object value) {
        return new JsonVariant(value);
    }

    public static JsonVariant of(JsonObject value) {
        return new JsonVariant(value);
    }

    public static JsonVariant of(JsonArray value) {
        return new JsonVariant(value);
    }

    public static JsonVariant object() {
        return of(new JsonObject());
    }

    public static JsonVariant array() {
        return of(new JsonArray());
    }

    public JsonVariantType getType() {
        return type;
    }

    public boolean isNull() {
        return type == JsonVariantType.NULL;
    }

    public boolean isObject() {
        return type == JsonVariantType.OBJECT;
    }

    public boolean isArray() {
        return type == JsonVariantType.ARRAY;
    }

    public boolean isScalar() {
        return type == JsonVariantType.SCALAR;
    }

    @NotNull
    public JsonObject getObject1() {
        switch (type) {
            case NULL:
                throw new IllegalUsageException("expect non-null");
            case OBJECT:
                return (JsonObject) value;
            default:
                throw new IllegalUsageException("expect Json Object.");
        }
    }

    public JsonObject getObject() {
        switch (type) {
            case NULL:
                return null;
            case OBJECT:
                return (JsonObject) value;
            default:
                throw new IllegalUsageException("expect Json Object.");
        }
    }

    @NotNull
    public JsonArray getArray1() {
        switch (type) {
            case NULL:
                throw new IllegalUsageException("expect non-null");
            case ARRAY:
                return (JsonArray) value;
            default:
                throw new IllegalUsageException("expect Json Array.");
        }
    }

    public JsonArray getArray() {
        switch (type) {
            case NULL:
                return null;
            case ARRAY:
                return (JsonArray) value;
            default:
                throw new IllegalUsageException("expect Json Array.");
        }
    }

    public Object getScalar() {
        switch (type) {
            case NULL:
                return null;
            case SCALAR:
                return value;
            default:
                throw new IllegalUsageException("expect scalar.");
        }
    }

    public JsonObject getObjectFor(String what) {
        if (type != JsonVariantType.OBJECT)
            throw new IllegalUsageException("expect Json Object for " + what);
        return (JsonObject) value;
    }

    public JsonArray getArrayFor(String what) {
        if (type != JsonVariantType.ARRAY)
            throw new IllegalUsageException("expect Json Array for " + what);
        return (JsonArray) value;
    }

    public Object getScalarFor(String what) {
        if (type != JsonVariantType.SCALAR)
            throw new IllegalUsageException("expect scalar for " + what);
        return value;
    }

    public Object getAny() {
        return value;
    }

    @Deprecated
    public Object getValue() {
        return getAny();
    }

    /**
     * @return <code>null</code> if the item is <code>null</code>.
     * @throws NullPointerException
     *             If the object is null.
     */
    public JsonVariant get(String key) {
        if (type == null)
            throw new NullPointerException("null object");
        if (type == JsonVariantType.OBJECT) {
            JsonObject jo = (JsonObject) value;
            Object any = jo.get(key);
            if (any == null)
                return null;
            return new JsonVariant(any);
        }
        return null;
    }

    /**
     * @return <code>null</code> if the array item is <code>null</code>.
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     *             If the array is null.
     */
    public JsonVariant get(int index) {
        if (type == JsonVariantType.NULL)
            throw new NullPointerException("null array");
        if (type == JsonVariantType.ARRAY) {
            JsonArray ja = (JsonArray) value;
            Object any = ja.get(index);
            if (any == null)
                return null;
            return new JsonVariant(any);
        }
        return null;
    }

    public JsonVariant sort() {
        return sort(SortOrder.ASCENDING);
    }

    public JsonVariant sort(SortOrder order) {
        if (type == JsonVariantType.OBJECT) {
            JsonObject hash = (JsonObject) value;
            JsonObject sorted = new JsonObject(order);
            for (String key : hash.keySet())
                sorted.put(key, hash.get(key));
            return of(sorted);
        }
        return this;
    }

    public String toJson() {
        switch (type) {
            case NULL:
                return "null";
            case OBJECT:
                JsonObject jo = (JsonObject) value;
                return jo.toString();
            case ARRAY:
                JsonArray ja = (JsonArray) value;
                return ja.toString();
            case SCALAR:
            default:
                JsonBuffer buf = new JsonBuffer();
                buf.value(value);
                return buf.toString();
        }
    }

    @Override
    public String toString() {
        if (type == JsonVariantType.NULL)
            return "null";
        else
            return type + ": " + value;
    }

    public static final JsonVariant NULL = new JsonVariant(JsonVariantType.NULL, null);

    public static Byte castToByte(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return ByteVarConverter.INSTANCE.from(scalar);
    }

    public static Short castToShort(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return ShortVarConverter.INSTANCE.from(scalar);
    }

    public static Integer castToInt(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return IntegerVarConverter.INSTANCE.from(scalar);
    }

    public static Long castToLong(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return LongVarConverter.INSTANCE.from(scalar);
    }

    public static Float castToFloat(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return FloatVarConverter.INSTANCE.from(scalar);
    }

    public static Double castToDouble(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return DoubleVarConverter.INSTANCE.from(scalar);
    }

    public static BigDecimal castToBigDecimal(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return BigDecimalVarConverter.INSTANCE.from(scalar);
    }

    public static BigInteger castToBigInteger(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return BigIntegerVarConverter.INSTANCE.from(scalar);
    }

    public static Boolean castToBoolean(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return BooleanVarConverter.INSTANCE.from(scalar);
    }

    public static String castToString(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return StringVarConverter.INSTANCE.from(scalar);
    }

    public static LocalDate castToLocalDate(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return LocalDateVarConverter.INSTANCE.from(scalar);
    }

    public static LocalTime castToLocalTime(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return LocalTimeVarConverter.INSTANCE.from(scalar);
    }

    public static LocalDateTime castToLocalDateTime(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return LocalDateTimeVarConverter.INSTANCE.from(scalar);
    }

    public static OffsetDateTime castToOffsetDateTime(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return OffsetDateTimeVarConverter.INSTANCE.from(scalar);
    }

    public static ZonedDateTime castToZonedDateTime(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return ZonedDateTimeVarConverter.INSTANCE.from(scalar);
    }

    public static OffsetTime castToOffsetTime(JsonVariant jv) {
        Object scalar = jv.getScalar();
        return OffsetTimeVarConverter.INSTANCE.from(scalar);
    }

}