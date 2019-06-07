package net.bodz.bas.site.json;

import java.util.AbstractList;

import org.json.JSONArray;

public class JsonArrayList
        extends AbstractList<Object>
        implements IJSONSupport {

    JSONArray array;

    public JsonArrayList(JSONArray array) {
        if (array == null)
            throw new NullPointerException("array");
        this.array = array;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= array.length())
            throw new IndexOutOfBoundsException("" + index);
        Object val = array.get(index);
        val = json.toVar(val);
        return val;
    }

    @Override
    public int size() {
        return array.length();
    }

    @Override
    public Object remove(int index) {
        Object jsonObj = array.remove(index);
        return json.toVar(jsonObj);
    }

}
