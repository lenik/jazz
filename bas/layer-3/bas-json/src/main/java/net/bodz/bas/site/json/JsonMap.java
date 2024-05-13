package net.bodz.bas.site.json;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.org.json.JsonValueWrapper;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonBuilder;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.typer.std.MutableTypedAttributes;

import section.obj;

/**
 * @see net.bodz.bas.c.org.json.JsonStrFormTypeHandler
 */
public class JsonMap
        extends MutableTypedAttributes
        implements
            Serializable,
            IJsonForm {

    private static final long serialVersionUID = 1L;

    JsonFormOptions opts = JsonFormOptions.DEFAULT;
    final Map<String, Object> map = super.attributeMap;

    public JsonMap() {
        super();
    }

    public JsonMap(SortOrder order) {
        super(order);
    }

    public JsonMap(Map<String, Object> map) {
        super(map);
    }

    @Transient
    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        for (String key : o.keySet()) {
            Object val = o.get(key);
            if (! parseJsonEntry(key, JsonVariant.of(val), opts))
                map.put(key, JsonFn.unwrap(val));
        }
    }

    /**
     * @return <code>true</code> if the key is handled.
     */
    protected boolean parseJsonEntry(String key, JsonVariant j, JsonFormOptions opts)
            throws ParseException {
        return false;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            out.key(key);
            // out.value(value);
            JsonFn.writeObject(out, value, opts);
        }
    }

    public JsonVariant getJsonVar()
            throws FormatException {
        return JsonFn.toJsonVar(this);
    }

    public void setJsonVar(JsonVariant jv)
            throws ParseException {
        map.clear();
        if (jv == null)
            return;
        jsonIn(jv, opts);
    }

    public String getJson()
            throws FormatException {
        String json = JsonFn.toJson(this, opts);
        return json;
    }

    public void setJson(String json)
            throws ParseException {
        map.clear();
        if (json == null)
            return;
        JsonVariant jv = JsonFn.parseAny(json);
        jsonIn(jv, opts);
    }

    @Deprecated
    public JsonValueWrapper getJsonStr()
            throws FormatException {
        String json = JsonFn.toJson(this, opts);
        Object j_val = JsonBuilder.getInstance().parse(json);
        return new JsonValueWrapper(j_val);
    }

    @Deprecated
    public synchronized void setJsonStr(JsonValueWrapper form)
            throws ParseException {
        map.clear();
        Object j_val = form.getWrapped();
        if (j_val == null)
            return;
        JsonVariant jv = JsonVariant.of(j_val);
        jsonIn(jv, opts);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if ((Class<?>) obj.class != JsonMap.class)
            return false;
        JsonMap o = (JsonMap) obj;
        if (! Nullables.equals(map, o.map))
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
