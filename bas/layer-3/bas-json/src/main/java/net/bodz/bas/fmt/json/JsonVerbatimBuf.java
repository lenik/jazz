package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.StringWriter;

import net.bodz.bas.err.ParseException;

public class JsonVerbatimBuf
        implements IJsonSerializable, IJsonOptions {

    String key;
    StringWriter buf;

    public JsonVerbatimBuf(String key, StringWriter buf) {
        this.key = key;
        this.buf = buf;
    }

    @Override
    public boolean isMixedIn() {
        return key == null;
    }

    @Override
    public boolean isSelfContained() {
        return key == null;
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
        if (key != null)
            out.key(key);
        out.verbatim(js);
    }

}
