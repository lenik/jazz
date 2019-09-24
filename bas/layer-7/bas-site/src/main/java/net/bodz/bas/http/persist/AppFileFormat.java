package net.bodz.bas.http.persist;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.joda.time.DateTime;
import org.json.JSONException;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.c.javax.servlet.AttributesIterable;
import net.bodz.bas.c.javax.servlet.IAttributes;
import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonPersistor;

public class AppFileFormat {

    String encoding = "utf-8";

    int majorVersion = 1;
    int minorVersion = 0;

    JsonPersistor jsonPersistor;

    public AppFileFormat() {
        jsonPersistor = new JsonPersistor();
    }

    public void saveAttributes(OutputStream out, IAttributes attributes)
            throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(out, encoding);
        try {
            saveAttributes(writer, attributes);
        } finally {
            writer.flush();
        }
    }

    public void saveAttributes(Writer writer, IAttributes attributes)
            throws IOException {
        JsonWriter jsOut = new JsonWriter(writer);
        saveAttributes(jsOut, attributes);
    }

    public JsonWriter saveAttributes(JsonWriter out, IAttributes attributes)
            throws IOException {
        out.object();

        out.key("version");
        {
            out.object();
            out.entry("major", majorVersion);
            out.entry("minor", minorVersion);
            out.endObject();
        }

        String timestamp = DateTimes.ISO8601.print(new DateTime());
        out.entry("timestamp", timestamp);

        out.key("attributes");
        {
            out.object();
            for (Map.Entry<String, ?> entry : new AttributesIterable(attributes)) {
                String key = entry.getKey();
                Object value = entry.getValue();
                out.key(key);
                jsonPersistor.writeTyped(out, value);
            }
            out.endObject();
        }

        out.endObject();
        return out;
    }

    public void loadAttributes(String json, IAttributes attributes)
            throws IOException, ParseException {
        if (json == null)
            return;
        json = json.trim();
        if (json.isEmpty())
            return;
        JsonObject in;
        try {
            in = new JsonObject(json);
        } catch (JSONException e) {
            throw new ParseException(e);
        }

        JsonObject version = in.getChild("version");
        version.getInt("major");
        version.getInt("minor");

        String timestamp = in.getString("timestamp");
        DateTime time = DateTimes.ISO8601.parseDateTime(timestamp);
        assert time != null;

        JsonObject attrs = in.getChild("attributes");
        for (String name : attrs.keySet()) {
            JsonObject typedNode = attrs.getChild(name);
            Object value = jsonPersistor.readTyped(typedNode);
            attributes.setAttribute(name, value);
        }
    }

}
