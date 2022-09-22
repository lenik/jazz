package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
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

    static final String K_FORMATS = "formats";

    String key;
    Object obj;

    JsonFormOptions jsonFormOptions = new JsonFormOptions();
    Map<String, String> formats = new HashMap<String, String>();

    public JsonWrapper(String key, Object obj) {
        this.key = key;
        this.obj = obj;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getWrapped() {
        return obj;
    }

    public void setWrapped(Object obj) {
        this.obj = obj;
    }

    public JsonFormOptions getOptions() {
        return jsonFormOptions;
    }

    public void setOptions(JsonFormOptions options) {
        this.jsonFormOptions = options;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public JsonWrapper format(Map<String, String> formats) {
        if (formats == null)
            throw new NullPointerException("formats");
        formats.putAll(formats);
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

    @Override
    public void readObject(IVariantMap<String> map) {
        jsonFormOptions.readObject(map);

        String formatsStr = map.getString(K_FORMATS);
        if (formatsStr != null) {
            if (formatsStr.startsWith("{")) {
                JsonObject jo = JsonObjectBuilder.getInstance().parse(formatsStr);
                for (String key : jo.keySet()) {
                    String format = jo.getString(key);
                    formats.put(key, format);
                }
            }
        }
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonObject j_formats = o.getJsonObject(K_FORMATS);
        if (j_formats != null)
            for (String key : j_formats.keySet()) {
                String format = j_formats.getString(key);
                formats.put(key, format);
            }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        throw new IllegalUsageException("expect scalar mode");
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts, boolean scalar)
            throws IOException, FormatException {
        if (scalar) {
            if (key != null) {
                out.object();
                out.key(key);
            }

            JsonFn.writeObject(out, obj, opts);

            if (key != null)
                out.endObject();
        } else
            jsonOut(out, opts);
    }

    public static JsonWrapper wrap(Object obj) {
        return new JsonWrapper(null, obj);
    }

    public static JsonWrapper wrap(Object obj, String key) {
        return new JsonWrapper(key, obj);
    }

}
