package net.bodz.bas.c.org.json;

import java.io.StringWriter;

import org.json.JSONException;

import net.bodz.bas.fmt.json.JsonObject;

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

    public JsonObject reconstruct() {
        String json = sw.toString();
        try {
            return new JsonObject(json);
        } catch (JSONException e) {
            throw new RuntimeException("Failed to re-construct " + json, e);
        }
    }

}
