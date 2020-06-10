package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.StringWriter;

import net.bodz.bas.err.ParseException;

public class JsonVerbatimBuf
        implements IJsonSerializable {

    String key;
    StringWriter buf;

    public JsonVerbatimBuf(String key, StringWriter buf) {
        if (key == null)
            throw new NullPointerException("key");
        this.key = key;
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
        String js = buf.toString();
        out.key(key);
        out.verbatim(js);
    }

}
