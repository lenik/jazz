package net.bodz.bas.c.org.json;

import java.io.Writer;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.Stack;
import net.bodz.fork.org.json.JSONException;
import net.bodz.fork.org.json.JSONWriter;

public class JsonWriter
        extends JSONWriter
        implements
            IJsonOut {

    Stack<JsonFormOptions> optionsStack = new ArrayStack<>();

    char lastMode;
    int verbatimLevel;

    public JsonWriter(Appendable w) {
        super(w);
    }

    @Override
    public Stack<JsonFormOptions> getJsonFormOptionsStack() {
        return optionsStack;
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
            JsonFn.writeObject(this, value, getJsonFormOptions());
        } catch (Exception e) {
            throw new JSONException(e);
        }
        return this;
    }

}
