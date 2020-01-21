package net.bodz.sms.ucpaas;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;

public class UcpaasId
        implements IJsonSerializable {

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
    }

    @Override
    public void writeObject(IJsonOut out) {
        out.entry("sid", sid);
        out.entry("token", token);
        out.entry("appid", appid);
    }

}
