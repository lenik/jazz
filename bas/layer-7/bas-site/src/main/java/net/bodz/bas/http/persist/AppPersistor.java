package net.bodz.bas.http.persist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.json.JSONException;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.javax.servlet.AttributesIterable;
import net.bodz.bas.c.javax.servlet.DecoratedServletContext;
import net.bodz.bas.c.javax.servlet.IAttributes;
import net.bodz.bas.c.javax.servlet.http.DecoratedHttpSession;
import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonPersistor;
import net.bodz.bas.io.res.builtin.FileResource;

public class AppPersistor {

    RegistryScheme registry;
    String encoding = "utf-8";

    int majorVersion = 1;
    int minorVersion = 0;

    JsonPersistor jsonPersistor;

    public AppPersistor(RegistryScheme registry) {
        if (registry == null)
            throw new NullPointerException("registry");
        this.registry = registry;
        this.jsonPersistor = new JsonPersistor();
    }

    public void save(final ServletContext servletContext)
            throws IOException {
        File contextFile = registry.getContextFile();
        save(contextFile, new DecoratedServletContext(servletContext));
    }

    public void save(final HttpSession session)
            throws IOException {
        String id = session.getId();
        File sessionFile = registry.getSessionFile(id);
        save(sessionFile, new DecoratedHttpSession(session));
    }

    public void save(File file, IAttributes attributes)
            throws IOException {
        OutputStream out = new FileOutputStream(file);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(out, encoding);
            JsonWriter jsOut = new JsonWriter(writer);
            jsOut.object();

            jsOut.key("version");
            {
                jsOut.object();
                jsOut.entry("major", majorVersion);
                jsOut.entry("minor", minorVersion);
                jsOut.endObject();
            }

            String timestamp = Dates.ISO8601.format(new DateTime());
            jsOut.entry("timestamp", timestamp);

            jsOut.key("attributes");
            {
                jsOut.object();
                for (Map.Entry<String, ?> entry : new AttributesIterable(attributes)) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    jsOut.key(key);
                    jsonPersistor.writeTyped(jsOut, value);
                }
                jsOut.endObject();
            }
        } finally {
            out.close();
        }
    }

    public void loadServletContext(ServletContext servletContext)
            throws IOException, ParseException {
        File file = registry.getContextFile();
        load(file, new DecoratedServletContext(servletContext));
    }

    public void loadSession(HttpSession session)
            throws IOException, ParseException {
        File file = registry.getSessionFile(session.getId());
        load(file, new DecoratedHttpSession(session));
    }

    public void load(File file, IAttributes attributes)
            throws IOException, ParseException {
        String json = new FileResource(file).read().readString();
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
