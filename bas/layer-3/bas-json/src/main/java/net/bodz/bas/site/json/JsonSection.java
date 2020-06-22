package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONObject;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.t.variant.AbstractVariantMap;

public class JsonSection
        extends AbstractVariantMap<String>
        implements IJsonSerializable {

    final Map<String, Object> map;
    final Boolean order;

    public JsonSection() {
        this(false);
    }

    public JsonSection(Boolean order) {
        this.map = createMap(order);
        this.order = order;
    }

    public JsonSection(Map<String, Object> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;

        if (map instanceof LinkedHashMap<?, ?>)
            order = false;
        else if (map instanceof TreeMap<?, ?>)
            order = true;
        else
            order = null;
    }

    protected <T> Map<String, T> createMap(Boolean order) {
        if (order == null) {
            return new HashMap<String, T>();
        } else if (order) {
            return new TreeMap<String, T>();
        } else {
            return new LinkedHashMap<String, T>();
        }
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    public JsonSection section(String key) {
        return getSection(key);
    }

    public JsonSection getSection(String key) {
        Object value = get(key);
        if (value instanceof JsonSection)
            return (JsonSection) value;
        JsonSection section = new JsonSection();
        map.put(key, section);
        return section;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        for (String key : o.keySet()) {
            Object value = o.opt(key);
            if (value instanceof JSONObject) {
                JSONObject jo = (JSONObject) value;
                JsonObject js = new JsonObject(jo);
                JsonSection section = new JsonSection(order);
                section.readObject(js);
                value = section;
            }
            map.put(key, value);
        }
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            out.key(key);
            if (value instanceof JsonSection) {
                JsonSection js = (JsonSection) value;
                out.object();
                js.writeObject(out);
                out.endObject();
            } else {
                out.value(value);
            }
        }
    }

}
