package net.bodz.bas.fmt.json;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
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

    public static String DEFAULT_ENCODING = "utf-8";

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

    public static void dump(IJsonForm obj, Appendable out)
            throws FormatException, IOException {
        String json = toJson(obj, JsonFormOptions.DEFAULT);
        out.append(json);
        out.append('\n');
    }

    public static String toJson(IJsonForm obj)
            throws FormatException {
        return toJson(obj, JsonFormOptions.DEFAULT);
    }

    /**
     * @param obj
     *            Non-<code>null</code> json-support object.
     */
    public static String toJson(IJsonForm obj, JsonFormOptions opts)
            throws FormatException {
        if (obj == null)
            throw new NullPointerException("obj");
        StringWriter buf = new StringWriter();
        JsonWriter out = new JsonWriter(buf);
        try {
            obj.jsonOut(out, opts, true);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

    /**
     * @param obj
     *            Non-<code>null</code> json-support object.
     * @param compact
     *            Format json in compact single line form.
     */
    public static String toJson(IJsonForm obj, String fallback, JsonFormOptions opts) {
        if (obj == null)
            throw new NullPointerException("obj");
        StringWriter buf = new StringWriter();
        JsonWriter out = new JsonWriter(buf);
        try {
            obj.jsonOut(out, opts, true);
        } catch (Exception e) {
            return fallback;
        }
        return buf.toString();
    }

    public static JsonObject toJsonObject(IJsonForm obj)
            throws FormatException, ParseException {
        String json = toJson(obj, JsonFormOptions.DEFAULT);
        JsonObject jo = parseObject(json);
        return jo;
    }

    public static <T extends IJsonForm> T fromJson(T obj, String json)
            throws ParseException {
        return fromJson(obj, json, JsonFormOptions.DEFAULT);
    }

    public static <T extends IJsonForm> T fromJson(T obj, String json, JsonFormOptions opts)
            throws ParseException {
        if (json == null) // XXX null ?
            obj.jsonIn(null, opts);
        else {
            JsonObject jsonObj = parseObject(json);
            obj.jsonIn(jsonObj, opts);
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

    public static JsonUnion union(IJsonForm... array) {
        return new JsonUnion(array);
    }

    public static <T extends IJsonForm> T readObject(T context, JsonObject node, JsonFormOptions opts)
            throws ParseException {
        if (node != null)
            context.jsonIn(node, opts);
        return context;
    }

    public static void writeObject(IJsonOut out, Object o, JsonFormOptions opts)
            throws IOException, FormatException {
        if (o == null) {
            out.value(null);
            return;
        }
        if (o instanceof IJsonForm) {
            IJsonForm jsVal = (IJsonForm) o;
            jsVal.jsonOut(out, opts, true);
            return;
        }
        if (o instanceof BigDecimal || o instanceof BigInteger) {
            out.value(o.toString());
            return;
        }
        out.value(o);
    }

    public static <T extends IJsonForm> T load(T obj, String fileName, JsonFormOptions opts)
            throws IOException, ParseException {
        File file = new File(fileName);
        return load(obj, file, opts);
    }

    public static <T extends IJsonForm> T load(T obj, File file, JsonFormOptions opts)
            throws IOException, ParseException {
        try (FileInputStream in = new FileInputStream(file)) {
            load(obj, in, DEFAULT_ENCODING, opts);
        }
        return obj;
    }

    public static <T extends IJsonForm> T load(T obj, InputStream in, String encoding, JsonFormOptions opts)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        byte[] block = new byte[4096];
        int cb;
        while ((cb = in.read(block)) != -1) {
            buf.write(block, 0, cb);
        }
        byte[] byteArray = buf.toByteArray();
        String json = new String(byteArray, charset);
        JsonObject root = JsonFn.parseObject(json);
        JsonFn.readObject(obj, root, opts);
        return obj;
    }

    public static void save(IJsonForm obj, String file, JsonFormOptions opts)
            throws IOException, FormatException {
        save(obj, file, DEFAULT_ENCODING, opts);
    }

    public static void save(IJsonForm obj, File file, JsonFormOptions opts)
            throws IOException, FormatException {
        save(obj, file, DEFAULT_ENCODING, opts);
    }

    public static void save(IJsonForm obj, String file, String encoding, JsonFormOptions opts)
            throws IOException, FormatException {
        save(obj, new File(file), encoding, opts);
    }

    public static void save(IJsonForm obj, File file, String encoding, JsonFormOptions opts)
            throws IOException, FormatException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            Writer writer = new OutputStreamWriter(fos, encoding);
            IJsonOut out = new JsonWriter(writer);
            obj.jsonOut(out, opts, true);
            writer.flush();
        }
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