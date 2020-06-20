package net.bodz.bas.fmt.json;

import java.util.Date;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.*;

public abstract class AbstractJsonObject<self_t>
        extends JSONObjectWrapper<self_t> {

    public AbstractJsonObject(JSONObject orig) {
        super(orig);
    }

    public boolean containsKey(String key) {
        return wrapped.has(key);
    }

    public Object strictGet(String key)
            throws JSONException {
        return super.get(key);
    }

    @Override
    public Object get(String key)
            throws JSONException {
        return get(key, null);
    }

    public Object get(String key, Object defaultValue)
            throws JSONException {
        if (!wrapped.has(key))
            return defaultValue;
        Object val = wrapped.get(key);
        if (val == JSONObject.NULL)
            return null;
        // if (val instanceof JSONObject)
        // return new JsonObject((JSONObject) val);
        return val;
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue)
            throws JSONException {
        Object val = get(key);
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
        return getFloat(key, 0.0f);
    }

    public float getFloat(String key, float defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        try {
            return FloatVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble(String key)
            throws JSONException {
        return getDouble(key, 0.0);
    }

    public double getDouble(String key, double defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        try {
            return DoubleVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public byte getByte(String key)
            throws JSONException {
        return getByte(key, (byte) 0);
    }

    public byte getByte(String key, byte defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        try {
            return ByteVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public short getShort(String key)
            throws JSONException {
        return getShort(key, (short) 0);
    }

    public short getShort(String key, short defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        try {
            return ShortVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt(String key)
            throws JSONException {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        try {
            return IntegerVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong(String key)
            throws JSONException {
        return getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        try {
            return LongVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public char getChar(String key)
            throws JSONException {
        return getChar(key, '\0');
    }

    public char getChar(String key, char defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        try {
            return CharacterVarConverter.INSTANCE.from(val);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public String getString(String key)
            throws JSONException {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue)
            throws JSONException {
        Object val = get(key);
        if (val == null)
            return defaultValue;
        return StringVarConverter.INSTANCE.from(val);
    }

    @Override
    public JSONArray getJSONArray(String key)
            throws JSONException {
        return wrapped.getJSONArray(key);
    }

    public JSONArray getJSONArray(String key, JSONArray defaultValue)
            throws JSONException {
        if (!has(key))
            return defaultValue;
        return wrapped.getJSONArray(key);
    }

    @Override
    public JSONObject getJSONObject(String key)
            throws JSONException {
        return wrapped.getJSONObject(key);
    }

    public JSONObject getJSONObject(String key, JSONObject defaultValue)
            throws JSONException {
        if (!has(key))
            return defaultValue;
        return wrapped.getJSONObject(key);
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
        Object _val = get(key);
        if (_val instanceof JSONObject) {
            JSONObject val = (JSONObject) _val;
            return new JsonObject(val);
        }
        return null;
    }

    public <T> T getVar(Class<T> type, String key) {
        return getVar(type, key, null);
    }

    public <T> T getVar(Class<T> type, String key, T defaultValue) {
        if (!has(key))
            return defaultValue;
        Object val = get(key);
        IVarConverter<Object> converter = VarConverters.getConverter(type);
        if (converter == null)
            throw new UnsupportedOperationException(String.format(//
                    "No converter for class %s.", type.getName()));
        // try {
        T var = (T) converter.from(val);
        // } catch (TypeConvertException e) {
        // throw new JSONException(e);
        // }
        return var;
    }

    public Date getDate(String key) {
        return getDate(key, null);
    }

    public Date getDate(String key, Date defaultValue) {
        return getVar(Date.class, key, defaultValue);
    }

    public DateTime getDateTime(String key) {
        return getDateTime(key, null);
    }

    public DateTime getDateTime(String key, DateTime defaultValue) {
        return getVar(DateTime.class, key, defaultValue);
    }

}
