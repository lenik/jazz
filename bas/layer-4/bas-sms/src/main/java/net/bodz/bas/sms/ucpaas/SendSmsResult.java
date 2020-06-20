package net.bodz.bas.sms.ucpaas;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonObject;

public class SendSmsResult
        extends AbstractUcpaasResult {

    public int count;
    public String smsid;
    public String mobile;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSmsid() {
        return smsid;
    }

    public void setSmsid(String smsid) {
        this.smsid = smsid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);

        String _count = o.getString("count");
        count = Integer.parseInt(_count);

        smsid = o.getString("smsid");
        mobile = o.getString("mobile");
    }

}
