package net.bodz.bas.c.org.json;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;

public class DefaultJsonStrForm
        implements
            IJsonStrForm {

    IJsonForm obj;

    public DefaultJsonStrForm(IJsonForm obj) {
        this.obj = obj;
    }

    @Override
    public String toJsonStr(JsonFormOptions opts) {
        if (obj == null)
            return null;
        else
            try {
                return JsonFn.toJson(obj, opts);
            } catch (FormatException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
    }

    @Override
    public void fromJsonStr(String jsonStr, JsonFormOptions opts)
            throws ParseException {
        if (jsonStr == null) {
            obj.jsonIn((JsonVariant) null, opts);
        } else {
            JsonVariant jv = JsonFn.parseAny(jsonStr);
            obj.jsonIn(jv, opts);
        }
    }

}
