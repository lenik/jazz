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
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonFn;
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
            IJsonSerializable,
            IAttributed {

    private static final long serialVersionUID = 1L;

    Map<String, Object> map;

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
    public void readObject(JsonObject o)
            throws ParseException {
        map.clear();
        for (String key : o.keySet()) {
            Object val = o.get(key);
            if (!readFromJson(key, val))
                map.put(key, JsonFn.unwrap(val));
        }
    }

    protected boolean readFromJson(String key, Object val)
            throws ParseException {
        return false;
    }

    @Override
    public void writeObject(IJsonOut out)
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
        String json = JsonFn.toJson(this);
        return json;
    }

    public void setJson(String json)
            throws ParseException {
        Object j_val = JsonBuilder.getInstance().parse(json);
        readObjectBoxed(j_val);
    }

    public JsonValueWrapper getJsonStr()
            throws FormatException {
        String json = JsonFn.toJson(this);
        Object j_val = JsonBuilder.getInstance().parse(json);
        return new JsonValueWrapper(j_val);
    }

    public synchronized void setJsonStr(JsonValueWrapper form)
            throws ParseException {
        Object j_val = form.getWrapped();
        readObjectBoxed(j_val);
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
