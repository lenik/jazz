package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonArrayBuilder;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;
import net.bodz.bas.meta.source.FnHelper;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.fork.org.json.JSONException;

@FnHelper
public class JsonFn {

    public static JsonVar parseAny(String json)
            throws ParseException {
        if (json == null)
            throw new NullPointerException("json");
        try {
            if (json.startsWith("[")) {
                JsonArray a = JsonArrayBuilder.getInstance().parse(json);
                return new JsonVar(JsonVarType.ARRAY, a);
            }
            if (json.startsWith("{")) {
                JsonObject o = JsonObjectBuilder.getInstance().parse(json);
                return new JsonVar(JsonVarType.OBJECT, o);
            }

            String v_json = "[" + json + "]";
            JsonArray jsonArray = JsonArrayBuilder.getInstance().parse(v_json);
            Object val = jsonArray.get(0);
            return new JsonVar(JsonVarType.SCALAR, val);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    public static JsonArray parseArray(String json)
            throws ParseException {
        if (json == null)
            throw new NullPointerException("json");
        try {
            return JsonArrayBuilder.getInstance().parse(json);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    public static JsonObject parseObject(String json)
            throws ParseException {
        if (json == null)
            throw new NullPointerException("json");
        try {
            return JsonObjectBuilder.getInstance().parse(json);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Without compact.
     *
     * @param obj
     *            Non-<code>null</code> json-support object.
     */
    public static String toJson(IJsonSerializable obj)
            throws FormatException {
        return toJson(obj, false);
    }

    /**
     * @param obj
     *            Non-<code>null</code> json-support object.
     * @param compact
     *            Format json in compact single line form.
     */
    public static String toJson(IJsonSerializable obj, boolean compact)
            throws FormatException {
        if (obj == null)
            throw new NullPointerException("obj");
        StringWriter buf = new StringWriter();
        JsonWriter out = new JsonWriter(buf);
        try {
            obj.writeObjectBoxed(out);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

    public static <T extends IJsonSerializable> T fromJson(T obj, String json)
            throws ParseException {
        if (json == null) // XXX null ?
            obj.readObject(null);
        else {
            JsonObject jsonObj = parseObject(json);
            obj.readObject(jsonObj);
        }
        return obj;
    }

    public static List<Object> toList(JsonArray json) {
        int n = json.length();
        List<Object> list = new ArrayList<Object>(n);
        for (int i = 0; i < n; i++) {
            Object val = json.get(i);
            Object unwrapped = unwrap(val);
            list.add(unwrapped);
        }
        return list;
    }

    public static Map<String, Object> toMap(String json) {
        JsonObject jsonObj = JsonObjectBuilder.getInstance().parse(json);
        return toMap(jsonObj);
    }

    public static Map<String, Object> toMap(Map<String, Object> map, String json) {
        JsonObject jsonObj = JsonObjectBuilder.getInstance().parse(json);
        return toMap(map, jsonObj);
    }

    public static Map<String, Object> toMap(JsonObject jsonObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        return toMap(map, jsonObj);
    }

    public static Map<String, Object> toMap(Map<String, Object> map, JsonObject jsonObj) {
        for (Object _key : jsonObj.keySet()) {
            String key = (String) _key;
            Object val = jsonObj.get(key);
            val = unwrap(val);
            map.put(key, val);
        }
        return map;
    }

    public static Object unwrap(Object json) {
        if (json instanceof JsonObject)
            json = toMap((JsonObject) json);
        if (json instanceof JsonArray)
            json = toList((JsonArray) json);
        return json;
    }

    public static <T> Iterable<T> iterate(final JsonArray array) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new JsonArrayIterator<T>(array);
            }
        };
    }

    public static JsonUnion union(IJsonSerializable... array) {
        return new JsonUnion(array);
    }

    public static <T extends IJsonSerializable> T readObject(T context, JsonObject node)
            throws ParseException {
        if (node != null)
            context.readObject(node);
        return context;
    }

    public static void writeObject(IJsonOut out, Object o)
            throws IOException, FormatException {
        IJsonOptions opts = null;
        if (o instanceof IJsonOptions)
            opts = (IJsonOptions) o;
        writeObject(out, o, opts);
    }

    public static void writeObject(IJsonOut out, Object o, IJsonOptions opts)
            throws IOException, FormatException {
        if (o == null) {
            out.value(null);
            return;
        }
        if (o instanceof IJsonSerializable) {
            IJsonSerializable jsVal = (IJsonSerializable) o;
            jsVal.writeObjectBoxed(out);
            return;
        }
        if (o instanceof BigDecimal || o instanceof BigInteger) {
            out.value(o.toString());
            return;
        }
        out.value(o);
    }

}

class JsonArrayIterator<T>
        extends PrefetchedIterator<T> {

    JsonArray array;
    int index = 0;

    public JsonArrayIterator(JsonArray array) {
        this.array = array;
    }

    @Override
    protected T fetch() {
        if (index >= array.length())
            return end();
        Object item = array.get(index++);
        @SuppressWarnings("unchecked")
        T retval = (T) item;
        return retval;
    }

}