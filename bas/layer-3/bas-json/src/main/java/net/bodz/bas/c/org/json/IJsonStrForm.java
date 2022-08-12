package net.bodz.bas.c.org.json;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;

public interface IJsonStrForm {

    String toJsonStr(JsonFormOptions opts);

    void fromJsonStr(String jsonStr, JsonFormOptions opts)
            throws ParseException;

}
