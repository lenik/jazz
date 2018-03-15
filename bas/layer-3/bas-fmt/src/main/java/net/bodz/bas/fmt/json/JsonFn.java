package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;

public class JsonFn {

    public static Object parseAny(String json)
            throws ParseException {
        if (json == null)
            throw new NullPointerException("json");
        try {
            if (json.startsWith("["))
                return new JSONArray(json);
            if (json.startsWith("{"))
                return new JsonObject(json);

            String v_json = "[" + json + "]";
            JSONArray jsonArray = new JSONArray(v_json);
            return jsonArray.get(0);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    public static JSONArray parseArray(String json)
            throws ParseException {
        if (json == null)
            throw new NullPointerException("json");
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    public static JsonObject parseObject(String json)
            throws ParseException {
        if (json == null)
            throw new NullPointerException("json");
        try {
            return new JsonObject(json);
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
    public static String toJson(IJsonSerializable obj) {
        return toJson(obj, false);
    }

    /**
     * @param obj
     *            Non-<code>null</code> json-support object.
     * @param compact
     *            Format json in compact single line form.
     */
    public static String toJson(IJsonSerializable obj, boolean compact) {
        if (obj == null)
            throw new NullPointerException("obj");
        StringWriter buf = new StringWriter();
        JsonWriter writer = new JsonWriter(buf);
        try {
            obj.writeObject(writer);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

    public static <T extends IJsonSerializable> T fromJson(T obj, String json)
            throws ParseException {
        if (json == null)
            obj.readObject(null);
        else {
            JsonObject jsonObj = parseObject(json);
            obj.readObject(jsonObj);
        }
        return obj;
    }

    public static List<Object> toList(JSONArray json) {
        int n = json.length();
        List<Object> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Object val = json.get(i);
            Object unwrapped = unwrap(val);
            list.add(unwrapped);
        }
        return list;
    }

    public static Map<String, Object> toMap(String json) {
        JSONObject jsonObj = new JSONObject(json);
        return toMap(jsonObj);
    }

    public static Map<String, Object> toMap(Map<String, Object> map, String json) {
        JSONObject jsonObj = new JSONObject(json);
        return toMap(map, jsonObj);
    }

    public static Map<String, Object> toMap(JsonObject jsonObj) {
        return toMap(jsonObj.getWrapped());
    }

    public static Map<String, Object> toMap(JSONObject jsonObj) {
        Map<String, Object> map = new HashMap<>();
        return toMap(map, jsonObj);
    }

    public static Map<String, Object> toMap(Map<String, Object> map, JsonObject jsonObj) {
        return toMap(map, jsonObj.getWrapped());
    }

    public static Map<String, Object> toMap(Map<String, Object> map, JSONObject jsonObj) {
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
        if (json instanceof JSONObject)
            json = toMap((JSONObject) json);
        if (json instanceof JSONArray)
            json = toList((JSONArray) json);
        return json;
    }

}
