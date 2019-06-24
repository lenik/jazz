package net.bodz.bas.fmt.json;

import java.io.Writer;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.model.IWrapper;
import net.bodz.bas.t.variant.conv.*;

public class JsonObject
        implements IWrapper<JSONObject> {

    private final JSONObject jsonObj;

    public JsonObject(JSONObject jsonObj) {
        if (jsonObj == null)
            throw new NullPointerException("jsonObj");
        this.jsonObj = jsonObj;
    }

    /**
     * Construct an empty JSONObject.
     */
    public JsonObject() {
        this(new JSONObject());
    }

    /**
     * Construct a JSONObject from a subset of another JSONObject. An array of strings is used to
     * identify the keys that should be copied. Missing keys are ignored.
     *
     * @param jo
     *            A JSONObject.
     * @param names
     *            An array of strings.
     * @throws JSONException
     * @exception JSONException
     *                If a value is a non-finite number or if a name is duplicated.
     */
    public JsonObject(JSONObject jo, String[] names) {
        this(new JSONObject(jo, names));
    }

    /**
     * Construct a JSONObject from a JSONTokener.
     *
     * @param x
     *            A JSONTokener object containing the source string.
     * @throws JSONException
     *             If there is a syntax error in the source string or a duplicated key.
     */
    public JsonObject(JSONTokener x)
            throws JSONException {
        this(new JSONObject(x));
    }

    /**
     * Construct a JSONObject from a Map.
     *
     * @param map
     *            A map object that can be used to initialize the contents of the JSONObject.
     * @throws JSONException
     */
    public JsonObject(Map<String, ?> map) {
        this(new JSONObject(map));
    }

    /**
     * Construct a JSONObject from an Object using bean getters. It reflects on all of the public
     * methods of the object. For each of the methods with no parameters and a name starting with
     * <code>"get"</code> or <code>"is"</code> followed by an uppercase letter, the method is
     * invoked, and a key and the value returned from the getter method are put into the new
     * JSONObject.
     *
     * The key is formed by removing the <code>"get"</code> or <code>"is"</code> prefix. If the
     * second remaining character is not upper case, then the first character is converted to lower
     * case.
     *
     * For example, if an object has a method named <code>"getName"</code>, and if the result of
     * calling <code>object.getName()</code> is <code>"Larry Fine"</code>, then the JSONObject will
     * contain <code>"name": "Larry Fine"</code>.
     *
     * @param bean
     *            An object that has getter methods that should be used to make a JSONObject.
     */
    public JsonObject(Object bean) {
        this(new JSONObject(bean));
    }

    /**
     * Construct a JSONObject from an Object, using reflection to find the public members. The
     * resulting JSONObject's keys will be the strings from the names array, and the values will be
     * the field values associated with those keys in the object. If a key is not found or not
     * visible, then it will not be copied into the new JSONObject.
     *
     * @param object
     *            An object that has fields that should be used to make a JSONObject.
     * @param names
     *            An array of strings, the names of the fields to be obtained from the object.
     */
    public JsonObject(Object object, String names[]) {
        this(new JSONObject(object, names));
    }

    /**
     * Construct a JSONObject from a source JSON text string. This is the most commonly used
     * JSONObject constructor.
     *
     * @param source
     *            A string beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *            with <code>}</code> &nbsp;<small>(right brace)</small>.
     * @exception JSONException
     *                If there is a syntax error in the source string or a duplicated key.
     */
    public JsonObject(String source)
            throws JSONException {
        this(new JSONObject(source));
    }

    /**
     * Construct a JSONObject from a ResourceBundle.
     *
     * @param baseName
     *            The ResourceBundle base name.
     * @param locale
     *            The Locale to load the ResourceBundle for.
     * @throws JSONException
     *             If any JSONExceptions are detected.
     */
    public JsonObject(String baseName, Locale locale)
            throws JSONException {
        this(new JSONObject(baseName, locale));
    }

    public static JsonObject wrap(JSONObject jsonObj) {
        return new JsonObject(jsonObj);
    }

    @Override
    public JSONObject getWrapped() {
        return jsonObj;
    }

    public boolean containsKey(String key) {
        return jsonObj.has(key);
    }

    public boolean has(String key) {
        return jsonObj.has(key);
    }

    public boolean isNull(String key) {
        return jsonObj.isNull(key);
    }

    public Set<String> keySet() {
        return jsonObj.keySet();
    }

    public int length() {
        return jsonObj.length();
    }

    public JSONArray names() {
        return jsonObj.names();
    }

    public Object _get(String key)
            throws JSONException {
        Object val = _get(key, null);
        if (val == JSONObject.NULL)
            return null;
        return val;
    }

    public Object _get(String key, Object defaultValue)
            throws JSONException {
        if (!jsonObj.has(key))
            return defaultValue;
        Object val = jsonObj.get(key);
        // if (val instanceof JSONObject)
        // return new JsonObject((JSONObject) val);
        return val;
    }

    public Boolean getBoolean(String key) {
        Object val = _get(key);
        if (val == null)
            return null;
        return BooleanVarConverter.INSTANCE.from(val);
    }

    public boolean getBoolean(String key, boolean defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return BooleanVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Float getFloat(String key)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return null;
        return FloatVarConverter.INSTANCE.from(val);
    }

    public float getFloat(String key, float defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return FloatVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Double getDouble(String key)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return null;
        return DoubleVarConverter.INSTANCE.from(val);
    }

    public double getDouble(String key, double defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return DoubleVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Byte getByte(String key)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return null;
        return ByteVarConverter.INSTANCE.from(val);
    }

    public byte getByte(String key, byte defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return ByteVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Short getShort(String key)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return null;
        return ShortVarConverter.INSTANCE.from(val);
    }

    public short getShort(String key, short defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return ShortVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Integer getInt(String key)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return null;
        return IntegerVarConverter.INSTANCE.from(val);
    }

    public int getInt(String key, int defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return IntegerVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Long getLong(String key)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return null;
        return LongVarConverter.INSTANCE.from(val);
    }

    public long getLong(String key, long defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return LongVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Character getChar(String key)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return null;
        return CharacterVarConverter.INSTANCE.from(val);
    }

    public char getChar(String key, char defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        try {
            return CharacterVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public String getString(String key)
            throws JSONException {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue)
            throws JSONException {
        Object val = _get(key);
        if (val == null)
            return defaultValue;
        return StringVarConverter.INSTANCE.from(val);
    }

    public JSONArray getJSONArray(String key)
            throws JSONException {
        return jsonObj.getJSONArray(key);
    }

    public JSONArray getJSONArray(String key, JSONArray defaultValue)
            throws JSONException {
        if (!has(key))
            return defaultValue;
        return jsonObj.getJSONArray(key);
    }

    public JSONObject getJSONObject(String key)
            throws JSONException {
        return jsonObj.getJSONObject(key);
    }

    public JSONObject getJSONObject(String key, JSONObject defaultValue)
            throws JSONException {
        if (!has(key))
            return defaultValue;
        return jsonObj.getJSONObject(key);
    }

    public final JsonArray getJsonArray(String key)
            throws JSONException {
        return getJsonArray(key, null);
    }

    public JsonArray getJsonArray(String key, JsonArray defaultValue)
            throws JSONException {
        if (!has(key))
            return defaultValue;
        JSONArray array = getJSONArray(key);
        return new JsonArray(array);
    }

    /**
     * See {@link #getChild(String)}
     *
     * @deprecated
     */
    @Deprecated
    public final JsonObject getJsonObject(String key)
            throws JSONException {
        return getChild(key);
    }

    /**
     * See {@link #getChild(String, JsonObject)}
     *
     * @deprecated
     */
    @Deprecated
    public JsonObject getJsonObject(String key, JsonObject defaultValue)
            throws JSONException {
        return getChild(key, defaultValue);
    }

    public JsonObject getChild(String key)
            throws JSONException {
        return getChild(key, null);
    }

    public JsonObject getChild(String key, JsonObject defaultValue)
            throws JSONException {
        if (!has(key))
            return defaultValue;
        JSONObject val = jsonObj.getJSONObject(key);
        return new JsonObject(val);
    }

    public JSONObject put(String key, boolean value)
            throws JSONException {
        return jsonObj.put(key, value);
    }

    public JSONObject put(String key, Collection<?> value)
            throws JSONException {
        return jsonObj.put(key, value);
    }

    public JSONObject put(String key, double value)
            throws JSONException {
        return jsonObj.put(key, value);
    }

    public JSONObject put(String key, int value)
            throws JSONException {
        return jsonObj.put(key, value);
    }

    public JSONObject put(String key, long value)
            throws JSONException {
        return jsonObj.put(key, value);
    }

    public JSONObject put(String key, Map<String, ?> value)
            throws JSONException {
        return jsonObj.put(key, value);
    }

    public JSONObject put(String key, Object value)
            throws JSONException {
        return jsonObj.put(key, value);
    }

    public JSONObject putOnce(String key, Object value)
            throws JSONException {
        return jsonObj.putOnce(key, value);
    }

    public JSONObject putOpt(String key, Object value)
            throws JSONException {
        return jsonObj.putOpt(key, value);
    }

    public JSONObject accumulate(String key, Object value)
            throws JSONException {
        return jsonObj.accumulate(key, value);
    }

    public JSONObject append(String key, Object value)
            throws JSONException {
        return jsonObj.append(key, value);
    }

    public JSONObject increment(String key)
            throws JSONException {
        return jsonObj.increment(key);
    }

    public Object remove(String key) {
        return jsonObj.remove(key);
    }

    public Writer write(Writer writer)
            throws JSONException {
        return jsonObj.write(writer);
    }

    public JSONArray toJSONArray(JSONArray names)
            throws JSONException {
        return jsonObj.toJSONArray(names);
    }

    @Override
    public String toString() {
        return jsonObj.toString();
    }

    public String toString(int indentFactor)
            throws JSONException {
        return jsonObj.toString(indentFactor);
    }

    @Override
    public int hashCode() {
        int hash = 0xc59e4544;
        hash += jsonObj.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != JsonObject.class)
            return false;
        JsonObject o = (JsonObject) obj;
        return jsonObj.equals(o.jsonObj);
    }

}
