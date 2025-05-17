package net.bodz.bas.sms.ucpaas;

import java.io.IOException;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonStruct;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.NotNull;

public class AbstractUcpaasResult
        extends JsonStruct {

    public int code;
    public String msg;
    public String createDate;
    public String uid;

    @Derived
    public boolean isSucceeded() {
        return code == 0;
    }

    @Derived
    public boolean isFailed() {
        return !isSucceeded();
    }

    public void ensureSucceded() {
        if (!isSucceeded())
            throw new IllegalStateException("failed");
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        String _code = o.getString("code");
        if (_code != null)
            code = Integer.parseInt(_code);
        msg = o.getString("msg");
        createDate = o.getString("create_date");
        uid = o.getString("uid");
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("code", code);
        out.entry("msg", msg);
        out.entry("createDate", createDate);
        out.entry("uid", uid);
    }

    @Override
    public String toString() {
        JsonBuffer js = new JsonBuffer();
        js.object();
        try {
            jsonOut(js, JsonFormOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        js.endObject();
        return js.toString().replace(",", ",\n");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
