package net.bodz.bas.site.json;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import section.obj;

import org.json.JSONObject;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.org.json.JsonFormTypeHandler;
import net.bodz.bas.c.org.json.JsonObj;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.rtx.IAttributed;

/**
 * @see JsonFormTypeHandler
 */
public class JsonMap
        implements Serializable, IJsonSerializable, IAttributed {

    private static final long serialVersionUID = 1L;

    Map<String, Object> map;

    public JsonMap() {
        this(new TreeMap<String, Object>());
    }

    public JsonMap(Map<String, Object> map) {
        this.map = map;
    }

    public final void readObject(JSONObject o)
            throws ParseException {
        readObject(JsonObject.wrap(o));
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        map.clear();
        for (Object _key : o.keySet()) {
            String key = (String) _key;
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
        out.value(map);
    }

    /** ⇱ Implementation Of {@link IAttributed}. */
    /* _____________________________ */static section.iface __ATTRS__;

    @Override
    public <T> T getAttribute(String name) {
        return (T) map.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        T val = (T) map.get(name);
        if (val == null)
            val = defaultValue;
        return val;
    }

    @Override
    public void setAttribute(String name, Object value) {
        map.put(name, value);
    }

    public JsonObj getJsonForm() {
        JSONObject obj = new JSONObject(map);
        return new JsonObj(obj);
    }

    public synchronized void setJsonForm(JsonObj form) {
        map.clear();
        JsonFn.toMap(map, form.getWrapped());
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

}
