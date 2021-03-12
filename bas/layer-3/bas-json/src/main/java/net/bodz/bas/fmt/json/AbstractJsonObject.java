package net.bodz.bas.fmt.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.*;
import net.bodz.json.JSONArray;
import net.bodz.json.JSONException;
import net.bodz.json.JSONObject;

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

    public byte[] getByteArray(String key, byte[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Byte> list = getBytes(key, null);
        byte[] v = new byte[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public short[] getShortArray(String key, short[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Short> list = getShorts(key, null);
        short[] v = new short[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public int[] getIntArray(String key, int[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Integer> list = getInts(key, null);
        int[] v = new int[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public long[] getLongArray(String key, long[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Long> list = getLongs(key, null);
        long[] v = new long[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public float[] getFloatArray(String key, float[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Float> list = getFloats(key, null);
        float[] v = new float[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public double[] getDoubleArray(String key, double[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Double> list = getDoubles(key, null);
        double[] v = new double[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Boolean> list = getBooleans(key, null);
        boolean[] v = new boolean[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public char[] getCharArray(String key, char[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Character> list = getChars(key, null);
        char[] v = new char[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public String[] getStringArray(String key, String[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<String> list = getStrings(key, null);
        String[] v = new String[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public List<Byte> getBytes(String key, List<Byte> list) {
        if (list == null)
            list = new ArrayList<Byte>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add((byte) array.getInt(i));
        }
        return list;
    }

    public List<Short> getShorts(String key, List<Short> list) {
        if (list == null)
            list = new ArrayList<Short>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add((short) array.getInt(i));
        }
        return list;
    }

    public List<Integer> getInts(String key, List<Integer> list) {
        if (list == null)
            list = new ArrayList<Integer>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add(array.getInt(i));
        }
        return list;
    }

    public List<Long> getLongs(String key, List<Long> list) {
        if (list == null)
            list = new ArrayList<Long>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add(array.getLong(i));
        }
        return list;
    }

    public List<Float> getFloats(String key, List<Float> list) {
        if (list == null)
            list = new ArrayList<Float>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add((float) array.getDouble(i));
        }
        return list;
    }

    public List<Double> getDoubles(String key, List<Double> list) {
        if (list == null)
            list = new ArrayList<Double>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add(array.getDouble(i));
        }
        return list;
    }

    public List<Boolean> getBooleans(String key, List<Boolean> list) {
        if (list == null)
            list = new ArrayList<Boolean>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add(array.getBoolean(i));
        }
        return list;
    }

    public List<Character> getChars(String key, List<Character> list) {
        if (list == null)
            list = new ArrayList<Character>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++) {
                String item = array.getString(i);
                list.add(item.isEmpty() ? '\0' : item.charAt(0));
            }
        }
        return list;
    }

    public List<String> getStrings(String key, List<String> list) {
        if (list == null)
            list = new ArrayList<String>();
        JsonArray array = getJsonArray(key);
        if (array != null) {
            int n = array.length();
            for (int i = 0; i < n; i++)
                list.add(array.getString(i));
        }
        return list;
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
