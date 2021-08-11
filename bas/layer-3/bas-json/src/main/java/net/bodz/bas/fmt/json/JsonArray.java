package net.bodz.bas.fmt.json;

import java.io.Writer;
import java.util.Collection;
import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.json.JSONArray;
import net.bodz.json.JSONException;
import net.bodz.json.JSONObject;

public class JsonArray {

    private final JSONArray array;

    public JsonArray(JSONArray array) {
        this.array = array;
    }

    public JsonArray() {
        this(new JSONArray());
    }

    public JsonArray(String source) {
        this(new JSONArray(source));
    }

    public static JsonArray wrap(JSONArray array) {
        return new JsonArray(array);
    }

    @Override
    public int hashCode() {
        return array.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return array.equals(obj);
    }

    public Object get(int index)
            throws JSONException {
        Object val = array.get(index);
        if (val instanceof JSONObject) {
            JSONObject jo = (JSONObject) val;
            return JsonObject.wrap(jo);
        }
        return val;
    }

    public boolean getBoolean(int index)
            throws JSONException {
        return array.getBoolean(index);
    }

    public double getDouble(int index)
            throws JSONException {
        return array.getDouble(index);
    }

    public int getInt(int index)
            throws JSONException {
        return array.getInt(index);
    }

    public JsonArray getJsonArray(int index)
            throws JSONException {
        JSONArray a = array.getJSONArray(index);
        return JsonArray.wrap(a);
    }

    public JsonObject getJsonObject(int index)
            throws JSONException {
        JSONObject obj = array.getJSONObject(index);
        return JsonObject.wrap(obj);
    }

    public long getLong(int index)
            throws JSONException {
        return array.getLong(index);
    }

    public String getString(int index)
            throws JSONException {
        return array.getString(index);
    }

    public boolean isNull(int index) {
        return array.isNull(index);
    }

    public String join(String separator)
            throws JSONException {
        return array.join(separator);
    }

    public int length() {
        return array.length();
    }

    public Object opt(int index) {
        return array.opt(index);
    }

    public boolean optBoolean(int index) {
        return array.optBoolean(index);
    }

    public boolean optBoolean(int index, boolean defaultValue) {
        return array.optBoolean(index, defaultValue);
    }

    public double optDouble(int index) {
        return array.optDouble(index);
    }

    public double optDouble(int index, double defaultValue) {
        return array.optDouble(index, defaultValue);
    }

    public int optInt(int index) {
        return array.optInt(index);
    }

    public int optInt(int index, int defaultValue) {
        return array.optInt(index, defaultValue);
    }

    public JsonArray optJsonArray(int index) {
        JSONArray a = array.optJSONArray(index);
        return JsonArray.wrap(a);
    }

    public JsonObject optJsonObject(int index) {
        JSONObject obj = array.optJSONObject(index);
        return JsonObject.wrap(obj);
    }

    public long optLong(int index) {
        return array.optLong(index);
    }

    public long optLong(int index, long defaultValue) {
        return array.optLong(index, defaultValue);
    }

    public String optString(int index) {
        return array.optString(index);
    }

    public String optString(int index, String defaultValue) {
        return array.optString(index, defaultValue);
    }

    public JsonArray put(boolean value) {
        array.put(value);
        return this;
    }

    public JsonArray put(Collection<?> value) {
        array.put(value);
        return this;
    }

    public JsonArray put(double value)
            throws JSONException {
        array.put(value);
        return this;
    }

    public JsonArray put(int value) {
        array.put(value);
        return this;
    }

    public JsonArray put(long value) {
        array.put(value);
        return this;
    }

    public JsonArray put(Map<?, ?> value) {
        array.put(value);
        return this;
    }

    public JsonArray put(Object value) {
        array.put(value);
        return this;
    }

    public JsonArray put(int index, boolean value)
            throws JSONException {
        array.put(index, value);
        return this;
    }

    public JsonArray put(int index, Collection<?> value)
            throws JSONException {
        array.put(index, value);
        return this;
    }

    public JsonArray put(int index, double value)
            throws JSONException {
        array.put(index, value);
        return this;
    }

    public JsonArray put(int index, int value)
            throws JSONException {
        array.put(index, value);
        return this;
    }

    public JsonArray put(int index, long value)
            throws JSONException {
        array.put(index, value);
        return this;
    }

    public JsonArray put(int index, Map<?, ?> value)
            throws JSONException {
        array.put(index, value);
        return this;
    }

    public JsonArray put(int index, Object value)
            throws JSONException {
        array.put(index, value);
        return this;
    }

    public Object remove(int index) {
        array.remove(index);
        return this;
    }

    public JsonObject toJsonObject(JsonArray names)
            throws JSONException {
        return toJsonObject(names.array);
    }

    public JsonObject toJsonObject(JSONArray names)
            throws JSONException {
        JSONObject jsonObj = array.toJSONObject(names);
        return JsonObject.wrap(jsonObj);
    }

    @Override
    public String toString() {
        return array.toString();
    }

    public String toString(int indentFactor)
            throws JSONException {
        return array.toString(indentFactor);
    }

    public Writer write(Writer writer)
            throws JSONException {
        return array.write(writer);
    }

    public <T extends IJsonSerializable> T readInto(int index, T obj)
            throws ParseException {
        return readInto(index, obj, null);
    }

    public <T extends IJsonSerializable> T readInto(int index, T obj, T newObj)
            throws ParseException {
        if (index >= length())
            return obj;

        JsonObject node = null;
        Object _node = get(index);
        if (_node == null) // force set to null
            return null;
        if (_node instanceof JSONObject)
            node = JsonObject.wrap((JSONObject) _node);
        else if (_node instanceof JsonObject)
            node = (JsonObject) _node;

        if (node != null) {
            if (obj == null) {
                if (newObj == null) // don't auto-create
                    return null;
                obj = newObj;
            }
            obj.readObject(node);
        }

        return obj;
    }

}
