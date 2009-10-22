package net.bodz.xfo.types;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.types.util.Strings;

public class MapAttributes implements AttributedElement {

    protected final Map<String, String> map;

    public MapAttributes(Map<String, String> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    @Override
    public boolean hasAttribute(String id) {
        return map.containsKey(id);
    }

    @Override
    public String getAttributeText(String id) {
        return map.get(id);
    }

    @Override
    public Set<String> getAttributeIds() {
        return map.keySet();
    }

    @Override
    public Set<Entry<String, String>> getAttributeEntries() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut(map.size() * 100);
        for (Entry<String, String> e : map.entrySet()) {
            String key = e.getKey();
            String val = e.getValue();
            String esc = Strings.escape(val);
            out.println(key + " = \"" + esc + "\"");
        }
        return out.toString();
    }

}
