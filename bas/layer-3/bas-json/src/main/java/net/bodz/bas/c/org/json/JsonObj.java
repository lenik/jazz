package net.bodz.bas.c.org.json;

import net.bodz.bas.err.ParseException;
import net.bodz.json.JSONObject;

public class JsonObj
        implements IJsonForm {

    JSONObject obj;

    public JsonObj() {
    }

    public JsonObj(JSONObject obj) {
        this.obj = obj;
    }

    public JSONObject getWrapped() {
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
            obj = new JSONObject(jsonStr);
    }

    @Override
    public String toString() {
        if (obj == null)
            return null;
        else
            return toJsonStr();
    }

}
