package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
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
    public void readObjectBoxed(Object obj, JsonFormOptions opts)
            throws ParseException {
        if (!(obj instanceof JsonObject))
            throw new ParseException("not an object: " + obj);
        JsonObject jo = (JsonObject) obj;
        jsonIn(jo, opts);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        String code = buf.toString();
        out.verbatim(code);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts, boolean scalar)
            throws IOException, FormatException {
        if (scalar) {
            out.object();
            jsonOut(out, opts);
            out.endObject();
        } else {
            jsonOut(out, opts);
        }
    }

}
