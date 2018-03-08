package net.bodz.bas.c.org.json;

import org.json.JSONObject;

import net.bodz.bas.err.ParseException;

public class JsonObj
        implements IJsonForm {

    JSONObject obj;

    public JsonObj() {
    }

    public JsonObj(JSONObject obj) {
        this.obj = obj;
    }

    @Override
    public String readInStr() {
        if (obj == null)
            return null;
        else
            return obj.toString();
    }

    @Override
    public void writeInStr(String jsonStr)
            throws ParseException {
        if (jsonStr == null)
            obj = null;
        else
            obj = new JSONObject(jsonStr);
    }

}
