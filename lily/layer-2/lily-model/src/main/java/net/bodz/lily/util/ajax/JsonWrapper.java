package net.bodz.lily.util.ajax;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class JsonWrapper {

    String key;
    Object obj;
    int maxDepth = -1;
    Map<String, String> formats = new HashMap<>();

    public JsonWrapper(String key, Object obj) {
        this.key = key;
        this.obj = obj;
    }

    public JsonWrapper depth(int maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        if (formats == null)
            throw new NullPointerException("formats");
        this.formats = formats;
    }

    public void setFormat(String column, String format) {
        if (column == null)
            throw new NullPointerException("column");
        if (format == null)
            throw new NullPointerException("format");
        formats.put(column, format);
    }

    public void parseFormats(String json) {
        JSONObject obj = new JSONObject(json);
        for (String key : (Set<String>) obj.keySet()) {
            String format = obj.getString(key);
            formats.put(key, format);
        }
    }

    public static JsonWrapper wrap(Object obj) {
        return new JsonWrapper(null, obj);
    }

    public static JsonWrapper wrap(Object obj, String key) {
        return new JsonWrapper(key, obj);
    }

}
