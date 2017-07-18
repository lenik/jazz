package net.bodz.lily.util.ajax;

import org.json.JSONObject;

import net.bodz.bas.t.variant.VariantMapImpl;

public class JsonVarMap
        extends VariantMapImpl<String>
        implements IJSONSupport {

    public JsonVarMap(JSONObject obj) {
        super(new JsonLookupMap(obj));
    }

    @Override
    public Object get(Object key) {
        Object jsonChild = super.get(key);
        Object child = json.toVar(jsonChild);
        return child;
    }

}
