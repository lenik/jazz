package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.StringWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;

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
                return new JSONObject(json);

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

    public static JSONObject parseObject(String json)
            throws ParseException {
        if (json == null)
            throw new NullPointerException("json");
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            throw new ParseException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    public static String toJson(IJsonSerializable obj) {
        StringWriter buf = new StringWriter();
        JSONWriter writer = new JSONWriter(buf);
        try {
            obj.writeObject(writer);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

    public static <T extends IJsonSerializable> T fromJson(T obj, String json)
            throws ParseException {
        JSONObject jsonObj = parseObject(json);
        obj.readObject(jsonObj);
        return obj;
    }

    public static void entry(JSONWriter out, String key, boolean value) {
        out.key(key);
        out.value(value);
    }

    public static void entry(JSONWriter out, String key, double value) {
        out.key(key);
        out.value(value);
    }

    public static void entry(JSONWriter out, String key, long value) {
        out.key(key);
        out.value(value);
    }

    public static void entry(JSONWriter out, String key, Object value) {
        out.key(key);
        out.value(value);
    }

}
