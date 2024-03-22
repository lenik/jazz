package net.bodz.bas.fmt.json;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class JsonCloner {

    private boolean ignoreRefs;
    private int refCount;

    public JsonCloner ignoreRefs() {
        this.ignoreRefs = true;
        return this;
    }

    public int getRefCount() {
        return refCount;
    }

    public Object copy(Object any) {
        return copy(JsonVariant.of(any)).value;
    }

    public JsonVariant copy(JsonVariant jv) {
        switch (jv.type) {
        case OBJECT:
            return JsonVariant.of(copyObject(jv.getObject()));
        case ARRAY:
            return JsonVariant.of(copyArray(jv.getArray()));
        case NULL:
        case SCALAR:
        default:
            return jv;
        }
    }

    public JsonObject copyObject(JsonObject jo) {
        return copyObject(jo, new JsonObject());
    }

    public JsonObject copyObject(JsonObject jo, JsonObject copy) {
        for (String key : jo.keySet()) {
            Object val = jo.get(key);
            if (ignoreRefs && isRef(val)) {
                refCount++;
                continue;
            }
            Object valCopy = copy(val);
            copy.put(key, valCopy);
        }
        return copy;
    }

    public JsonArray copyArray(JsonArray ja) {
        return copyArray(ja, new JsonArray());
    }

    public JsonArray copyArray(JsonArray ja, JsonArray copy) {
        int n = ja.length();
        for (int i = 0; i < n; i++) {
            Object val = ja.get(i);
            if (ignoreRefs && isRef(val)) {
                refCount++;
                continue;
            }
            Object valCopy = copy(val);
            copy.put(i, valCopy);
        }
        return copy;
    }

    static boolean isRef(Object any) {
        if (any instanceof String) {
            String s = (String) any;
            if (s.startsWith("<ref:") && s.endsWith(">"))
                return true;
        }
        return false;
    }

}
