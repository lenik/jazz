package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * Add json support to pojo.
 *
 * @see JsonWrapper_json
 */
public class JsonWrapper
        implements
            IVarMapForm,
            IJsonForm {

    String key;
    Object obj;
    boolean includeNull = false;
    boolean includeFalse = false;
    int maxDepth = -1;
    Map<String, String> formats = new HashMap<String, String>();

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
        JsonObject obj = JsonObjectBuilder.getInstance().parse(json);
        for (String key : obj.keySet()) {
            String format = obj.getString(key);
            formats.put(key, format);
        }
        return this;
    }

    @Override
    public void readObject(IVariantMap<String> map) {
        String formatJson = map.getString("format");
        if (formatJson != null)
            format(formatJson);

        Integer depth = map.getInt("depth", null);
        if (depth != null)
            depth(depth);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    public static JsonWrapper wrap(Object obj) {
        return new JsonWrapper(null, obj);
    }

    public static JsonWrapper wrap(Object obj, String key) {
        return new JsonWrapper(key, obj);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts, boolean scalar)
            throws IOException, FormatException {
        if (key != null) {
            out.object();
            out.key(key);
        }

        JsonFn.writeObject(out, obj, opts);

        if (key != null)
            out.endObject();
    }

}
