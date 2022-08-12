package net.bodz.bas.sms.ucpaas;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonStruct;
import net.bodz.bas.json.JsonObject;

public class UcpaasId
        extends JsonStruct {

    public String sid;
    public String token;
    public String appid;

    public UcpaasId initEmpty() {
        sid = "";
        token = "";
        appid = "";
        return this;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        sid = o.getString("sid", sid);
        token = o.getString("token", token);
        appid = o.getString("appid", appid);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts) {
        out.entry("sid", sid);
        out.entry("token", token);
        out.entry("appid", appid);
    }

}
