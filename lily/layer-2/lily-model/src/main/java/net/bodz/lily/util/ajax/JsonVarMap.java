package net.bodz.lily.util.ajax;

import org.json.JSONObject;

import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.t.variant.VariantMapImpl;

public class JsonVarMap
        extends VariantMapImpl<String>
        implements IJSONSupport {

    public JsonVarMap(JsonObject obj) {
        this(obj.getWrapped());
    }

    public JsonVarMap(JSONObject obj) {
        super(new JsonLookupMap(obj));
    }

    @Override
    public Object get(Object _key) {
        if (_key == null)
            throw new NullPointerException("_key");
        String key = _key.toString();
        int dot = key.indexOf('.');
        String tail;
        if (dot == -1)
            tail = null;
        else {
            tail = key.substring(dot + 1);
            key = key.substring(0, dot);
        }

        Object jsonChild = super.get(key);
        Object child = json.toVar(jsonChild);
        if (tail == null)
            return child;

        if (!(child instanceof JsonVarMap))
            return null;

        JsonVarMap childMap = (JsonVarMap) child;
        return childMap.get(tail);
    }

}
