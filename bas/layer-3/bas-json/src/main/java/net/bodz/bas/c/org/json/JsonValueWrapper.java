package net.bodz.bas.c.org.json;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonBuilder;
import net.bodz.bas.json.JsonObject;

public class JsonValueWrapper
        implements
            IJsonStrForm {

    Object j_val;

    public JsonValueWrapper() {
    }

    public JsonValueWrapper(Object j_val) {
        this.j_val = j_val;
    }

    public Object getWrapped() {
        return j_val;
    }

    @Override
    public String toJsonStr() {
        if (j_val == null)
            return null;
        return JsonObject.valueToString(j_val);
    }

    @Override
    public void fromJsonStr(String jsonStr)
            throws ParseException {
        if (jsonStr == null)
            j_val = null;
        else
            j_val = JsonBuilder.getInstance().parse(jsonStr);
    }

    @Override
    public String toString() {
        if (j_val == null)
            return null;
        else
            return toJsonStr();
    }

}
