package net.bodz.bas.sms.ucpaas;

import java.io.IOException;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonSupport;
import net.bodz.bas.meta.cache.Derived;

public class AbstractUcpaasResult
        extends JsonSupport {

    public String _code;
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
    public void readObject(JsonObject o)
            throws ParseException {
        _code = o.getString("code");
        if (_code != null)
            code = Integer.parseInt(_code);
        msg = o.getString("msg");
        createDate = o.getString("create_date");
        uid = o.getString("uid");
    }

    @Override
    public String toString() {
        JsonBuffer js = new JsonBuffer();
        js.object();
        try {
            writeObject(js);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        js.endObject();
        return js.toString().replace(",", ",\n");
    }

    public String get_code() {
        return _code;
    }

    public void set_code(String _code) {
        this._code = _code;
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
