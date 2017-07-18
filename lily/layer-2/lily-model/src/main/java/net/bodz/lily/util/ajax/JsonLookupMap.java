package net.bodz.lily.util.ajax;

import java.util.Set;

import org.json.JSONObject;

import net.bodz.bas.t.variant.ILookupMap;

public class JsonLookupMap
        implements ILookupMap<String, Object>, IJSONSupport {

    JSONObject jsonObj;

    public JsonLookupMap(JSONObject obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        this.jsonObj = obj;
    }

    @Override
    public Set<String> keySet() {
        return jsonObj.keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return jsonObj.keySet().contains(key);
    }

    @Override
    public Object get(Object _key) {
        String key = _key.toString();
        Object child = jsonObj.opt(key);
        Object var = json.toVar(child);
        return var;
    }

}
