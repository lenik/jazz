package net.bodz.bas.c.org.json;

import org.json.JSONObject;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonFn;

public class DefaultJsonForm
        implements IJsonForm {

    IJsonSerializable obj;

    public DefaultJsonForm(IJsonSerializable obj) {
        this.obj = obj;
    }

    public String readInStr() {
        if (obj == null)
            return null;
        else
            return JsonFn.toJson(obj);
    }

    public void writeInStr(String jsonStr)
            throws ParseException {
        if (jsonStr == null) {
            obj.readObject(null);
        } else {
            JSONObject jsonObject = new JSONObject(jsonStr);
            obj.readObject(jsonObject);
        }
    }

}
