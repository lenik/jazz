package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.variant.AbstractVariantMap;

public class JsonSection
        extends AbstractVariantMap<String>
        implements
            IJsonSerializable {

    final Map<String, Object> map;
    final SortOrder order;

    public JsonSection() {
        this(SortOrder.KEEP);
    }

    public JsonSection(SortOrder order) {
        this.map = order.newMap();
        this.order = order;
    }

    public JsonSection(Map<String, Object> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
        this.order = SortOrder.detect(map);
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
            Object value = o.get(key);
            if (value instanceof JsonObject) {
                JsonObject child = (JsonObject) value;
                JsonSection section = new JsonSection(order);
                section.readObject(child);
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
