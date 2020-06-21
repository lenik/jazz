package net.bodz.bas.sms.ucpaas;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonStruct;

public class UcpaasId
        extends JsonStruct {

    private static final long serialVersionUID = 1L;

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
    public void readObject(JsonObject o)
            throws ParseException {
        sid = o.getString("sid", sid);
        token = o.getString("token", token);
        appid = o.getString("appid", appid);
    }

    @Override
    public void writeObject(IJsonOut out) {
        out.entry("sid", sid);
        out.entry("token", token);
        out.entry("appid", appid);
    }

}
