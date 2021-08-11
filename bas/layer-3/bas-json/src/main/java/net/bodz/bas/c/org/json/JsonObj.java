package net.bodz.bas.c.org.json;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;

public class JsonObj
        implements
            IJsonForm {

    JsonObject obj;

    public JsonObj() {
    }

    public JsonObj(JsonObject obj) {
        this.obj = obj;
    }

    public JsonObject getWrapped() {
        return obj;
    }

    @Override
    public String toJsonStr() {
        if (obj == null)
            return null;
        else
            return obj.toString();
    }

    @Override
    public void fromJsonStr(String jsonStr)
            throws ParseException {
        if (jsonStr == null)
            obj = null;
        else
            obj = JsonObjectBuilder.getInstance().parse(jsonStr);
    }

    @Override
    public String toString() {
        if (obj == null)
            return null;
        else
            return toJsonStr();
    }

}
