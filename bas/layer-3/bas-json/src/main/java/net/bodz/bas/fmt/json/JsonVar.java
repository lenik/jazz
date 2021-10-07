package net.bodz.bas.fmt.json;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.form.SortOrder;

public class JsonVar {

    JsonVarType type;

    Object value;

    public JsonVar(JsonVarType type, Object value) {
        setValue(type, value);
    }

    public JsonVar(Object value) {
        setValue(value);
    }

    public JsonVar(JsonObject value) {
        setValue(JsonVarType.OBJECT, value);
    }

    public JsonVar(JsonArray value) {
        setValue(JsonVarType.ARRAY, value);
    }

    public JsonVarType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
        if (value instanceof JsonObject)
            type = JsonVarType.OBJECT;
        else if (value instanceof JsonArray)
            type = JsonVarType.ARRAY;
        else
            type = JsonVarType.SCALAR;
    }

    public void setValue(JsonVarType type, Object value) {
        if (type == null)
            throw new NullPointerException("type'");
        if (value == null)
            throw new NullPointerException("value");
        this.type = type;
        this.value = value;
    }

    public JsonVar get(String key) {
        if (type == JsonVarType.OBJECT) {
            JsonObject jo = (JsonObject) value;
            Object any = jo.get(key);
            return new JsonVar(any);
        }
        return null;
    }

    public JsonVar get(int index) {
        if (type == JsonVarType.ARRAY) {
            JsonArray ja = (JsonArray) value;
            Object any = ja.get(index);
            return new JsonVar(any);
        }
        return null;
    }

    public void sort() {
        sort(SortOrder.ASCENDING);
    }

    public void sort(SortOrder order) {
        if (type == JsonVarType.OBJECT) {
            JsonObject hash = (JsonObject) value;
            JsonObject sorted = new JsonObject(order);
            for (String key : hash.keySet())
                sorted.put(key, hash.get(key));
            value = sorted;
        }
    }

}