package net.bodz.bas.c.org.json;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.json.JSONException;
import net.bodz.json.JSONWriter;

public class JsonWriter
        extends JSONWriter
        implements IJsonOut {

    char lastMode;
    int verbatimLevel;

    public JsonWriter(Writer w) {
        super(w);
    }

    public static JsonBuffer buffer() {
        return new JsonBuffer();
    }

    public Writer getWriter() {
        return (Writer) super.writer;
    }

    @Override
    public JsonWriter verbatim(String code)
            throws JSONException {
        append(code);
        return this;
    }

    @Override
    public JsonWriter array()
            throws JSONException {
        super.array();
        return this;
    }

    @Override
    public JsonWriter endArray()
            throws JSONException {
        super.endArray();
        return this;
    }

    @Override
    public JsonWriter object()
            throws JSONException {
        super.object();
        return this;
    }

    @Override
    public JsonWriter object(Object value)
            throws JSONException {
        try {
            fn.dumpTree(this, value);
        } catch (IOException e) {
            throw new JSONException(e);
        }
        return this;
    }

    @Override
    public JsonWriter endObject()
            throws JSONException {
        super.endObject();
        return this;
    }

    @Override
    public JsonWriter key(String string)
            throws JSONException {
        super.key(string);
        return this;
    }

    @Override
    public JsonWriter value(boolean b)
            throws JSONException {
        super.value(b);
        return this;
    }

    @Override
    public JsonWriter value(double d)
            throws JSONException {
        super.value(d);
        return this;
    }

    @Override
    public JsonWriter value(long l)
            throws JSONException {
        super.value(l);
        return this;
    }

    @Override
    public JsonWriter value(Object object)
            throws JSONException {
        super.value(object);
        return this;
    }

    @Override
    public JsonWriter entry(String key, boolean value) {
        this.key(key);
        this.value(value);
        return this;
    }

    @Override
    public JsonWriter entry(String key, double value) {
        this.key(key);
        this.value(value);
        return this;
    }

    @Override
    public JsonWriter entry(String key, long value) {
        this.key(key);
        this.value(value);
        return this;
    }

    @Override
    public JsonWriter entry(String key, Object value) {
        this.key(key);
        try {
            fn.dumpTree(this, value);
        } catch (IOException e) {
            throw new JSONException(e);
        }
        return this;
    }

    @Override
    public IJsonOut entryNotNull(String key, Object value) {
        if (value != null)
            entry(key, value);
        return this;
    }

}
