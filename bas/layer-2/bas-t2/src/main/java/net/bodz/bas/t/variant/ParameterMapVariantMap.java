package net.bodz.bas.t.variant;

import java.util.Map;
import java.util.Set;

public class ParameterMapVariantMap
        extends AbstractVariantMap<String> {

    private Map<String, String[]> map;

    public ParameterMapVariantMap(Map<String, String[]> parameterMap) {
        if (parameterMap == null)
            throw new NullPointerException("parameterMap");
        this.map = parameterMap;
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

    @Override
    public Object getScalar(String key, Object defaultValue) {
        String[] v = map.get(key);
        if (v == null || v.length == 0)
            return defaultValue;
        else
            return v[0];
    }

    @Override
    public Object[] getArray(String key, Object[] defaultValue) {
        String[] v = map.get(key);
        if (v == null)
            return defaultValue;
        else
            return v;
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
