package net.bodz.bas.json;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;
import net.bodz.fork.org.json.JSONException;
import net.bodz.fork.org.json._JSONObject;

public class JsonObject
        extends _JSONObject {

    public JsonObject() {
        this(SortOrder.KEEP);
    }

    public JsonObject(SortOrder sortMode) {
        this(sortMode.newMap());
    }

    public JsonObject(Map<String, Object> map) {
        super(map);
    }

    public Object get(@NotNull String key, Object defaultValue)
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

    public JsonVariant getVariant(@NotNull String key) {
        Object any = super.get(key);
        if (any == null)
            return null; // JsonVariant.NULL;
        return JsonVariant.of(any);
    }

    public JsonVariant getVariant(@NotNull String key, Object defaultValue) {
        Object any = super.get(key, defaultValue);
        if (any == null)
            return null; // JsonVariant.NULL;
        return JsonVariant.of(any);
    }

    public <T extends IJsonForm> T getObject(@NotNull String key, Class<T> clazz)
            throws ParseException {
        return getObject(key, () -> {
            try {
                return clazz.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new CreateException(e.getMessage(), e);
            }
        });
    }

    public <T extends IJsonForm> T getObject(@NotNull String key, Supplier<T> factory)
            throws ParseException {
        JsonVariant jv = getVariant(key);
        if (jv == null)
            return null;
        T instance = factory.get();
        instance.jsonIn(jv);
        return instance;
    }

    public <T extends IJsonForm> T getObject(@NotNull String key, T defaultValue, Class<T> clazz) {
        return getObject(key, defaultValue, () -> {
            try {
                return clazz.getConstructor().newInstance();
            } catch (Exception e) {
                return defaultValue;
            }
        });
    }

    public <T extends IJsonForm> T getObject(@NotNull String key, T defaultValue, Supplier<T> factory) {
        JsonVariant jv = getVariant(key);
        if (jv == null)
            return defaultValue;

        T instance = factory.get();
        try {
            instance.jsonIn(jv);
        } catch (ParseException e) {
            return defaultValue;
        }
        return instance;
    }

    public byte[] getByteArray(@NotNull String key, byte[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Byte> list = getBytes(key, null);
        byte[] v = new byte[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public short[] getShortArray(@NotNull String key, short[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Short> list = getShorts(key, null);
        short[] v = new short[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public int[] getIntArray(@NotNull String key, int[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Integer> list = getInts(key, null);
        int[] v = new int[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public long[] getLongArray(@NotNull String key, long[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Long> list = getLongs(key, null);
        long[] v = new long[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public float[] getFloatArray(@NotNull String key, float[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Float> list = getFloats(key, null);
        float[] v = new float[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public double[] getDoubleArray(@NotNull String key, double[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Double> list = getDoubles(key, null);
        double[] v = new double[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public boolean[] getBooleanArray(@NotNull String key, boolean[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Boolean> list = getBooleans(key, null);
        boolean[] v = new boolean[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public char[] getCharArray(@NotNull String key, char[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<Character> list = getChars(key, null);
        char[] v = new char[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    @Override
    public String[] getStringArray(@NotNull String key, String[] defaultValue) {
        if (!has(key))
            return defaultValue;
        List<String> list = getStrings(key, null);
        String[] v = new String[list.size()];
        for (int i = 0; i < v.length; i++)
            v[i] = list.get(i);
        return v;
    }

    public List<Byte> getBytes(@NotNull String key, List<Byte> list) {
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

    public List<Short> getShorts(@NotNull String key, List<Short> list) {
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

    public List<Integer> getInts(@NotNull String key, List<Integer> list) {
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

    public List<Long> getLongs(@NotNull String key, List<Long> list) {
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

    public List<Float> getFloats(@NotNull String key, List<Float> list) {
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

    public List<Double> getDoubles(@NotNull String key, List<Double> list) {
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

    public List<Boolean> getBooleans(@NotNull String key, List<Boolean> list) {
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

    public List<Character> getChars(@NotNull String key, List<Character> list) {
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

    public List<String> getStrings(@NotNull String key, List<String> list) {
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

    public <T> T getVar(Class<T> type, @NotNull String key) {
        return getVar(type, key, null);
    }

    public <T> T getVar(Class<T> type, @NotNull String key, T defaultValue) {
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
    public Date getDate(@NotNull String key) {
        return getDate(key, null);
    }

    @Override
    public Date getDate(@NotNull String key, Date defaultValue) {
        return getVar(Date.class, key, defaultValue);
    }

    @Override
    public Instant getInstant(@NotNull String key) {
        return getInstant(key, null);
    }

    @Override
    public Instant getInstant(@NotNull String key, Instant defaultValue) {
        return getVar(Instant.class, key, defaultValue);
    }

    @Override
    public ZonedDateTime getZonedDateTime(@NotNull String key) {
        return getZonedDateTime(key, null);
    }

    @Override
    public ZonedDateTime getZonedDateTime(@NotNull String key, ZonedDateTime defaultValue) {
        return getVar(ZonedDateTime.class, key, defaultValue);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(@NotNull String key) {
        return getOffsetDateTime(key, null);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(@NotNull String key, OffsetDateTime defaultValue) {
        return getVar(OffsetDateTime.class, key, defaultValue);
    }

    @Override
    public LocalDateTime getLocalDateTime(@NotNull String key) {
        return getLocalDateTime(key, null);
    }

    @Override
    public LocalDateTime getLocalDateTime(@NotNull String key, LocalDateTime defaultValue) {
        return getVar(LocalDateTime.class, key, defaultValue);
    }

    @Override
    public LocalDate getLocalDate(@NotNull String key) {
        return getLocalDate(key, null);
    }

    @Override
    public LocalDate getLocalDate(@NotNull String key, LocalDate defaultValue) {
        return getVar(LocalDate.class, key, defaultValue);
    }

    @Override
    public LocalTime getLocalTime(@NotNull String key) {
        return getLocalTime(key, null);
    }

    @Override
    public LocalTime getLocalTime(@NotNull String key, LocalTime defaultValue) {
        return getVar(LocalTime.class, key, defaultValue);
    }

    public <T extends IJsonForm> T readInto(@NotNull String key, T context)
            throws ParseException {
        return readInto(key, context, (Supplier<T>) null);
    }

    public <T extends IJsonForm> T readInto(@NotNull String key, T context, Class<T> factory)
            throws ParseException {
        return readInto(key, context, () -> {
            try {
                return factory.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }

    public <T extends IJsonForm> T readInto(@NotNull String key, T context, Supplier<T> factory)
            throws ParseException {
        return readInto(key, context, factory, JsonFormOptions.DEFAULT);
    }

    public <T extends IJsonForm> T readInto(@NotNull String key, T context, Supplier<T> factory, JsonFormOptions opts)
            throws ParseException {
        if (!has(key)) // nothing to change
            return context;

        JsonVariant jv = getVariant(key);
        if (jv == null || jv.isNull()) // force set to null
            return null;

        if (context == null) {
            if (factory == null) // don't auto-create
                return null;
            else
                context = factory.get();
        }
        context.jsonIn(jv, opts);
        return context;
    }

    public <T> Set<T> getArraySet(@NotNull String key, Set<T> set, SortOrder order, IJsonParser<T> conv)
            throws ParseException {
        return readArrayInto(key, set, conv, order::newSet);
    }

    public final <T> List<T> getArrayList(@NotNull String key, IJsonParser<T> conv)
            throws ParseException {
        return getArrayList(key, null, conv);
    }

    public final <T> List<T> getArrayList(@NotNull String key, List<T> list, IJsonParser<T> conv)
            throws ParseException {
        return readArrayInto(key, list, conv, ArrayList::new);
    }

    public final <T extends IJsonForm> List<T> getArrayList(@NotNull String key, Supplier<T> itemFactory)
            throws ParseException {
        return getArrayList(key, null, itemFactory);
    }

    public final <T extends IJsonForm> List<T> getArrayList(@NotNull String key, List<T> list, Supplier<T> itemFactory)
            throws ParseException {
        return readArrayInto(key, list, (JsonVariant _item) -> {
            T item = itemFactory.get();
            item.jsonIn(_item);
            return item;
        }, ArrayList::new);
    }

    public final <T extends IJsonForm> List<T> getArrayList(@NotNull String key, @NotNull Class<T> itemType)
            throws ParseException {
        return getArrayList(key, null, itemType);
    }

    public final <T extends IJsonForm> List<T> getArrayList(@NotNull String key, List<T> list, @NotNull Class<T> itemType)
            throws ParseException {
        return getArrayList(key, list, () -> {
            try {
                return itemType.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }

    public <T extends IJsonForm> Set<T> //
    readArrayIntoSet(@NotNull String key, Set<T> set, @NotNull SortOrder order, @NotNull Supplier<T> valueFactory)
            throws ParseException {
        return readArrayInto(key, set, (JsonVariant jv) -> {
            T val = valueFactory.get();
            val.jsonIn(jv, JsonFormOptions.XXX);
            return val;
        }, order::newSet);
    }

    public <T extends IJsonForm> List<T> //
    readArrayIntoList(@NotNull String key, List<T> list, Supplier<T> valueFactory)
            throws ParseException {
        return readArrayInto(key, list, (JsonVariant jv) -> {
            T val = valueFactory.get();
            val.jsonIn(jv, JsonFormOptions.XXX);
            return val;
        }, ArrayList::new);
    }

    public <C extends Collection<T>, T> C //
    readArrayInto(@NotNull String key, C collection, IJsonParser<T> parser, Supplier<C> collectionFactory)
            throws ParseException {
        if (!has(key)) // nothing to change
            return collection;

        Object _node = get(key);
        if (_node == null) // force set to null
            return null;

        if (_node instanceof JsonArray) {
            JsonArray array = (JsonArray) _node;
            if (collection == null) {
                if (collectionFactory == null) // don't auto-create
                    return null;
                collection = collectionFactory.get();
            } else {
                collection.clear();
            }

            // set.readObject(array);
            int n = array.length();
            for (int i = 0; i < n; i++) {
                Object node = array.get(i);
                JsonVariant jv = JsonVariant.of(node);
                T obj = parser.parse(jv);
                collection.add(obj);
            }
        }

        return collection;
    }

    public Map<String, String> //
    getMap(@NotNull String key, SortOrder order)
            throws ParseException {
        return readIntoMap(key, null, Nullables::toString, order::newMap);
    }

    public Map<String, String> //
    getMap(@NotNull String key, Map<String, String> map, SortOrder order)
            throws ParseException {
        return readIntoMap(key, map, Nullables::toString, order::newMap);
    }

    public <T> Map<String, T> //
    getMap(@NotNull String key, Map<String, T> map, SortOrder order, IJsonParser<T> conv)
            throws ParseException {
        return readIntoMap(key, map, conv, order::newMapDefault);
    }

    public <T extends IJsonForm> Map<String, T> //
    readIntoMap(@NotNull String key, Map<String, T> map, SortOrder order, Supplier<T> vals)
            throws ParseException {
        return readIntoMap(key, map, jv -> {
            T val = vals.get();
            val.jsonIn(jv, JsonFormOptions.XXX);
            return val;
        }, order::newMap);
    }

    public <map_t extends Map<String, val_t>, val_t extends IJsonForm> map_t //
    readIntoJMap(@NotNull String key, map_t map, Supplier<val_t> factory)
            throws ParseException {
        return readIntoMap(key, map, jv -> {
            if (jv.isNull())
                return null;
            val_t val = factory.get();
            val.jsonIn(jv, null);
            return val;
        }, null);
    }

    public <map_t extends Map<String, val_t>, val_t> map_t //
    readIntoMap(@NotNull String key, map_t map, IJsonParser<val_t> parser)
            throws ParseException {
        return readIntoMap(key, map, parser, null);
    }

    public <map_t extends Map<String, val_t>, val_t> map_t //
    readIntoMap(@NotNull String key, map_t map, IJsonParser<val_t> parser, Supplier<map_t> mapSupplier)
            throws ParseException {
        if (!has(key)) // nothing to change
            return map;

        Object _node = get(key);
        if (_node == null) // force set to null
            return null;

        if (_node instanceof JsonObject) {
            JsonObject o = (JsonObject) _node;
            if (map == null) {
                if (mapSupplier == null) // don't auto-create
                    return null;
                map = mapSupplier.get();
            } else {
                map.clear();
            }

            for (String k : o.keySet()) {
                Object jsObj = o.get(k);
                if (jsObj != null) {
                    JsonVariant jsVar = JsonVariant.of(jsObj);
                    val_t obj = parser.parse(jsVar);
                    map.put(k, obj);
                }
            }
        }

        return map;
    }

}
