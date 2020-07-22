package net.bodz.bas.fmt.json;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.json.JSONArray;
import net.bodz.json.JSONException;
import net.bodz.json.JSONObject;

public class JSONSortedObject
        extends JSONObject {

    public JSONSortedObject(JSONObject orig) {
        super(orig, toArray(orig.names()));
    }

    @Override
    public Iterator<String> keys() {
        return keySet().iterator();
    }

    @Override
    public Set<String> keySet() {
        return new TreeSet<String>(super.keySet());
    }

    @Override
    public Object get(String key)
            throws JSONException {
        Object val = super.get(key);
        if (val instanceof JSONObject)
            return new JSONSortedObject((JSONObject) val);
        return val;
    }

    static String[] toArray(JSONArray j) {
        String[] v = new String[j.length()];
        for (int i = 0; i < v.length; i++)
            v[i] = j.getString(i);
        return v;
    }

}
