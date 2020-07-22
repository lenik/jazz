package net.bodz.bas.c.org.json;


import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.json.JSONObject;

public class DefaultJsonForm
        implements IJsonForm {

    IJsonSerializable obj;

    public DefaultJsonForm(IJsonSerializable obj) {
        this.obj = obj;
    }

    @Override
    public String readInStr() {
        if (obj == null)
            return null;
        else
            return JsonFn.toJson(obj);
    }

    @Override
    public void writeInStr(String jsonStr)
            throws ParseException {
        if (jsonStr == null) {
            obj.readObject(null);
        } else {
            JSONObject jsonObj = new JSONObject(jsonStr);
            obj.readObject(JsonObject.wrap(jsonObj));
        }
    }

}
