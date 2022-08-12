package net.bodz.bas.sms.ucpaas;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.sms.ISmsSendResponse;
import net.bodz.bas.sms.SmsSendState;

public class SendSmsResult
        extends AbstractUcpaasResult
        implements ISmsSendResponse {

    int ERROR_NO_ROW = -1;

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
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        String _count = o.getString("count");
        count = Integer.parseInt(_count);

        smsid = o.getString("smsid");
        mobile = o.getString("mobile");

        fix_noRow();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        super.jsonOut(out, opts);
        out.entry("count", count);
        out.entry("smsid", smsid);
        out.entry("mobile", mobile);
    }

    void fix_noRow() {
        if (this.count == 0) {
            if (this.code == 0) {
                this.code = ERROR_NO_ROW;
            }
        }
    }

    @Override
    public SmsSendState getSendState() {
        if (count == 0)
            return SmsSendState.IGNORED;
        if (isSucceeded())
            return SmsSendState.SUCCESS;
        else
            return SmsSendState.FAILED;
    }

    @Override
    public String getErrorMessage() {
        return msg;
    }

}
