package net.bodz.bas.c.org.json;

import java.io.StringWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonVariant;

/**
 * @see JsonStringer
 */
public class JsonBuffer
        extends JsonWriter {

    StringWriter sw;

    public JsonBuffer() {
        this(new StringWriter());
    }

    JsonBuffer(StringWriter buf) {
        super(buf);
        this.sw = buf;
    }

    @Override
    public String toString() {
        return sw.toString();
    }

    public JsonVariant reconstruct()
            throws ParseException {
        String json = sw.toString();
        return JsonFn.parseAny(json);
    }

}
