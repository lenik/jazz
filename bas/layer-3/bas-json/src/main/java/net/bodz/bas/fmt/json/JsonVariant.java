package net.bodz.bas.fmt.json;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.form.SortOrder;

public class JsonVariant {

    JsonVariantType type;

    Object value;

    public JsonVariant(JsonVariantType type, Object value) {
        setValue(type, value);
    }

    public JsonVariant(Object value) {
        setValue(value);
    }

    public JsonVariant(JsonObject value) {
        setValue(JsonVariantType.OBJECT, value);
    }

    public JsonVariant(JsonArray value) {
        setValue(JsonVariantType.ARRAY, value);
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
        if (type != JsonVariantType.OBJECT)
            throw new IllegalUsageException("expect Json Object.");
        return (JsonObject) value;
    }

    public JsonArray getArray() {
        if (type != JsonVariantType.ARRAY)
            throw new IllegalUsageException("expect Json Array.");
        return (JsonArray) value;
    }

    public Object getScalar() {
        if (type != JsonVariantType.ARRAY)
            throw new IllegalUsageException("expect scalar.");
        return value;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
        if (value instanceof JsonObject)
            type = JsonVariantType.OBJECT;
        else if (value instanceof JsonArray)
            type = JsonVariantType.ARRAY;
        else
            type = JsonVariantType.SCALAR;
    }

    public void setValue(JsonVariantType type, Object value) {
        if (type == null)
            throw new NullPointerException("type'");
        if (value == null)
            throw new NullPointerException("value");
        this.type = type;
        this.value = value;
    }

    public JsonVariant get(String key) {
        if (type == JsonVariantType.OBJECT) {
            JsonObject jo = (JsonObject) value;
            Object any = jo.get(key);
            return new JsonVariant(any);
        }
        return null;
    }

    public JsonVariant get(int index) {
        if (type == JsonVariantType.ARRAY) {
            JsonArray ja = (JsonArray) value;
            Object any = ja.get(index);
            return new JsonVariant(any);
        }
        return null;
    }

    public void sort() {
        sort(SortOrder.ASCENDING);
    }

    public void sort(SortOrder order) {
        if (type == JsonVariantType.OBJECT) {
            JsonObject hash = (JsonObject) value;
            JsonObject sorted = new JsonObject(order);
            for (String key : hash.keySet())
                sorted.put(key, hash.get(key));
            value = sorted;
        }
    }

    public String toJson() {
        switch (type) {
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
        return type + ": " + value;
    }

}