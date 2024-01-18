package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;

public class JsonVerbatimBuf_Entry
        implements
            IJsonForm {

    String key;
    StringBuilder buf;

    public JsonVerbatimBuf_Entry(String key, StringBuilder buf) {
        this.key = key;
        this.buf = buf;
    }

    static final char CHAR_QQ = '"';

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        Object val = o.get(key);
        String json = JsonObject.valueToString(val);
        buf.setLength(0);
        buf.append(CHAR_QQ);
        buf.append(key);
        buf.append(CHAR_QQ);
        buf.append(": ");
        buf.append(json);
    }

    @Override
    public boolean wantObjectContext() {
        return true;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        String code = buf.toString();
        out.verbatim(code);
    }

}
