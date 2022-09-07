package net.bodz.bas.site.json;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.org.json.JsonValueWrapper;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonBuilder;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.rtx.IAttributed;

import section.obj;

/**
 * @see net.bodz.bas.c.org.json.JsonStrFormTypeHandler
 */
public class JsonMap
        implements
            Serializable,
            IJsonForm,
            IAttributed {

    private static final long serialVersionUID = 1L;

    Map<String, Object> map;
    JsonFormOptions opts = JsonFormOptions.DEFAULT;

    public JsonMap() {
        this(new TreeMap<String, Object>());
    }

    public JsonMap(Map<String, Object> map) {
        this.map = map;
    }

    @Transient
    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        map.clear();
        for (String key : o.keySet()) {
            Object val = o.get(key);
            if (!parseJsonEntry(key, val, opts))
                map.put(key, JsonFn.unwrap(val));
        }
    }

    /**
     * @return <code>true</code> if the key is handled.
     */
    protected boolean parseJsonEntry(String key, Object val, JsonFormOptions opts)
            throws ParseException {
        return false;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        for (Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            out.key(key);
            out.value(value);
        }
    }

    /** ⇱ Implementation Of {@link IAttributed}. */
    /* _____________________________ */static section.iface __ATTRS__;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String name) {
        return (T) map.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        @SuppressWarnings("unchecked")
        T val = (T) map.get(name);
        if (val == null)
            val = defaultValue;
        return val;
    }

    @Override
    public void setAttribute(String name, Object value) {
        map.put(name, value);
    }

    public String getJson()
            throws FormatException {
        String json = JsonFn.toJson(this, opts);
        return json;
    }

    public void setJson(String json)
            throws ParseException {
        Object j_val = JsonBuilder.getInstance().parse(json);
        readObjectBoxed(j_val, opts);
    }

    public JsonValueWrapper getJsonStr()
            throws FormatException {
        String json = JsonFn.toJson(this, opts);
        Object j_val = JsonBuilder.getInstance().parse(json);
        return new JsonValueWrapper(j_val);
    }

    public synchronized void setJsonStr(JsonValueWrapper form)
            throws ParseException {
        Object j_val = form.getWrapped();
        if (j_val == null)
            return;
        readObjectBoxed(j_val, opts);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if ((Class<?>) obj.class != JsonMap.class)
            return false;
        JsonMap o = (JsonMap) obj;
        if (!Nullables.equals(map, o.map))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int h = 0xc693df06;
        h = h * 17 + map.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public static JsonMap empty() {
        return EmptyJsonMap.getInstance();
    }

}
