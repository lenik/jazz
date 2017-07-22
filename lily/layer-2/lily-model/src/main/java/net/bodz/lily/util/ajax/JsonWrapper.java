package net.bodz.lily.util.ajax;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import net.bodz.bas.t.variant.IVariantMap;

public class JsonWrapper {

    String key;
    Object obj;
    boolean includeNull = false;
    boolean includeFalse = false;
    int maxDepth = -1;
    Map<String, String> formats = new HashMap<>();

    public JsonWrapper(String key, Object obj) {
        this.key = key;
        this.obj = obj;
    }

    public JsonWrapper withNull() {
        includeNull = true;
        return this;
    }

    public JsonWrapper withFalse() {
        includeFalse = true;
        return this;
    }

    public JsonWrapper depth(int maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public JsonWrapper format(Map<String, String> formats) {
        if (formats == null)
            throw new NullPointerException("formats");
        this.formats.putAll(formats);
        return this;
    }

    public JsonWrapper format(String column, String format) {
        if (column == null)
            throw new NullPointerException("column");
        if (format == null)
            throw new NullPointerException("format");
        formats.put(column, format);
        return this;
    }

    public JsonWrapper format(String json) {
        if (json == null)
            return this;
        JSONObject obj = new JSONObject(json);
        for (String key : (Set<String>) obj.keySet()) {
            String format = obj.getString(key);
            formats.put(key, format);
        }
        return this;
    }

    public JsonWrapper params(IVariantMap<String> q) {
        String formatJson = q.getString("format");
        if (formatJson != null)
            format(formatJson);

        Integer depth = q.getInt("depth", null);
        if (depth != null)
            depth(depth);
        return this;
    }

    public static JsonWrapper wrap(Object obj) {
        return new JsonWrapper(null, obj);
    }

    public static JsonWrapper wrap(Object obj, String key) {
        return new JsonWrapper(key, obj);
    }

}
