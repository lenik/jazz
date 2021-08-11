package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;

public class JsonVerbatimBuf
        implements
            IJsonSerializable,
            IJsonOptions {

    String key;
    StringBuilder buf;

    public JsonVerbatimBuf(String key, StringBuilder buf) {
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
        if (key != null) {
            Object val = o.get(key);
            String json = JsonObject.valueToString(val);
            buf.setLength(0);
            buf.append(json);
        }
    }

    @Override
    public void readObjectBoxed(Object obj)
            throws ParseException {
        if (key != null) {
            if (!(obj instanceof JsonObject))
                throw new ParseException("not an object: " + obj);
            JsonObject jo = (JsonObject) obj;
            readObject(jo);
        } else {
            String json = JsonObject.valueToString(obj);
            buf.setLength(0);
            buf.append(json);
        }
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        if (key != null) {
            out.key(key);
            String js = buf.toString();
            out.verbatim(js);
        }
    }

    @Override
    public void writeObjectBoxed(IJsonOut out)
            throws IOException, FormatException {
        if (key != null) {
            out.object();
            writeObject(out);
            out.endObject();
        } else {
            writeObject(out);
        }
    }

}
