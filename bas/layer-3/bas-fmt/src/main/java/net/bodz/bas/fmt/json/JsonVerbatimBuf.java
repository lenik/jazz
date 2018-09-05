package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.StringWriter;

import net.bodz.bas.err.ParseException;

public class JsonVerbatimBuf
        implements IJsonSerializable {

    StringWriter buf;

    public JsonVerbatimBuf(StringWriter buf) {
        this.buf = buf;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.verbatim(buf.toString());
    }

}
