package net.bodz.bas.c.org.json;

import java.io.Writer;

import org.json.JSONException;
import org.json.JSONWriter;

import net.bodz.bas.fmt.json.IJsonOut;

public class JsonWriter
        extends JSONWriter
        implements IJsonOut {

    public JsonWriter(Writer w) {
        super(w);
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

    public JsonWriter entry(String key, boolean value) {
        this.key(key);
        this.value(value);
        return this;
    }

    public JsonWriter entry(String key, double value) {
        this.key(key);
        this.value(value);
        return this;
    }

    public JsonWriter entry(String key, long value) {
        this.key(key);
        this.value(value);
        return this;
    }

    public JsonWriter entry(String key, Object value) {
        this.key(key);
        this.value(value);
        return this;
    }

}
