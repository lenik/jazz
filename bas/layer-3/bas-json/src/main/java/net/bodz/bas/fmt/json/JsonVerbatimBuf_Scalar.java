package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;

public class JsonVerbatimBuf_Scalar
        implements
            IJsonForm {

    StringBuilder buf;

    public JsonVerbatimBuf_Scalar(StringBuilder buf) {
        this.buf = buf;
    }

    @Override
    public void jsonIn(JsonVariant j, JsonFormOptions opts)
            throws ParseException {
        String json = j.toJson();
        buf.setLength(0);
        buf.append(json);
    }

    @Override
    public final void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        jsonIn(JsonVariant.of(o), opts);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts, boolean scalar)
            throws IOException, FormatException {
        if (scalar) {
            String code = buf.toString();
            out.verbatim(code);
        }
    }

}
