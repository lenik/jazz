package net.bodz.bas.json;

import java.util.*;

import org.joda.time.DateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.factory.IFactory;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;
import net.bodz.fork.org.json.JSONException;
import net.bodz.fork.org.json._JSONObject;

public class JsonObject
        extends _JSONObject {

    public JsonObject() {
        this(SortOrder.NONE);
    }

    public JsonObject(SortOrder sortMode) {
        this(sortMode.newMap());
    }

    public JsonObject(Map<String, Object> map) {
        super(map);
    }

    public Object get(String key, Object defaultValue)
            throws JSONException {
        if (!super.has(key))
            return defaultValue;
        Object val = super.get(key);
        if (val == _JSONObject.NULL)
            return null;
        // if (val instanceof JSONObject)
        // return new JsonObject((JSONObject) val);
        return val;
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

    @Override
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
                list.add((byte) array.getInt(i, 0));
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
                list.add((short) array.getInt(i, 0));
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
                list.add((float) array.getDouble(i, 0));
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
        Object converted = converter.from(val);
        // } catch (TypeConvertException e) {
        // throw new JSONException(e);
        // }
        T var = type.cast(converted);
        return var;
    }

    @Override
    public Date getDate(String key) {
        return getDate(key, null);
    }

    @Override
    public Date getDate(String key, Date defaultValue) {
        return getVar(Date.class, key, defaultValue);
    }

    public DateTime getDateTime(String key) {
        return getDateTime(key, null);
    }

    public DateTime getDateTime(String key, DateTime defaultValue) {
        return getVar(DateTime.class, key, defaultValue);
    }

    public <T extends IJsonSerializable> T readInto(String key, T obj)
            throws ParseException {
        return readInto(key, obj, null);
    }

    public <T extends IJsonSerializable> T readInto(String key, T obj, T newObj)
            throws ParseException {
        if (!has(key)) // nothing to change
            return obj;

        JsonObject node = null;
        Object _node = get(key);
        if (_node == null) // force set to null
            return null;
        if (_node instanceof JsonObject)
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

    public <T extends IJsonSerializable> List<T> readArrayInto(String key, List<T> list, IFactory<T> factory)
            throws ParseException {
        return readArrayInto(key, list, factory, ArrayList.class);
    }

    public <T extends IJsonSerializable> Set<T> readArrayInto(String key, Set<T> list, IFactory<T> factory)
            throws ParseException {
        return readArrayInto(key, list, factory, TreeSet.class);
    }

    public <C extends Collection<T>, T extends IJsonSerializable> C readArrayInto(String key, C set,
            IFactory<T> factory, Class<?> newSetClass)
            throws ParseException {
        if (!has(key)) // nothing to change
            return set;

        Object _node = get(key);
        if (_node == null) // force set to null
            return null;

        if (_node instanceof JsonArray) {
            JsonArray array = (JsonArray) _node;
            if (set == null) {
                if (newSetClass == null) // don't auto-create
                    return null;
                try {
                    @SuppressWarnings("unchecked")
                    C instance = (C) newSetClass.newInstance();
                    set = instance;
                } catch (Exception e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
            } else {
                set.clear();
            }

            // set.readObject(array);
            int n = array.length();
            for (int i = 0; i < n; i++) {
                JsonObject node = array.getJsonObject(i);
                T obj = factory.create();
                obj.readObject(node);
                set.add(obj);
            }
        }

        return set;
    }

}
