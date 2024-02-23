package net.bodz.bas.fmt.json;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.form.SortOrder;

public class JsonVariant {

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

}