package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.StringWriter;

import org.json.JSONObject;
import org.json.JSONWriter;

import net.bodz.bas.err.ParseException;

public class JsonFn {

    public static String toJson(IJsonSerializable obj)
            throws IOException {
        StringWriter buf = new StringWriter();
        JSONWriter writer = new JSONWriter(buf);
        obj.writeObject(writer);
        return buf.toString();
    }

    public static <T extends IJsonSerializable> T fromJson(T obj, String json)
            throws ParseException {
        JSONObject jsonObj = new JSONObject(json);
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
