package net.bodz.bas.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleConverter
        implements
            IJsonVisitor<Map<String, Object>, List<Object>> {

    Object context;

    @Override
    public Map<String, Object> object(JsonObject jo) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (String key : jo.keySet()) {
            Object value = jo.get(key);
            Object converted = JsonObjects.accept(value, this);
            map.put(key, converted);
        }
        return map;
    }

    @Override
    public List<Object> array(JsonArray ja) {
        int n = ja.length();
        List<Object> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Object item = ja.get(i);
            Object converted = JsonObjects.accept(item, this);
            list.add(converted);
        }
        return list;
    }

    @Override
    public Object simpleValue(Object value) {
        return value;
    }

    @Override
    public Object _null() {
        return null;
    }

}
