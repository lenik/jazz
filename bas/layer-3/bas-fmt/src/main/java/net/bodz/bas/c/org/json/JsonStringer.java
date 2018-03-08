package net.bodz.bas.c.org.json;

import org.json.JSONException;
import org.json.JSONStringer;

import net.bodz.bas.fmt.json.IJsonOut;

public class JsonStringer
        extends JSONStringer
        implements IJsonOut {

    public JsonStringer() {
    }

    @Override
    public JsonStringer array()
            throws JSONException {
        super.array();
        return this;
    }

    @Override
    public JsonStringer endArray()
            throws JSONException {
        super.endArray();
        return this;
    }

    @Override
    public JsonStringer object()
            throws JSONException {
        super.object();
        return this;
    }

    @Override
    public JsonStringer endObject()
            throws JSONException {
        super.endObject();
        return this;
    }

    @Override
    public JsonStringer key(String string)
            throws JSONException {
        super.key(string);
        return this;
    }

    @Override
    public JsonStringer value(boolean b)
            throws JSONException {
        super.value(b);
        return this;
    }

    @Override
    public JsonStringer value(double d)
            throws JSONException {
        super.value(d);
        return this;
    }

    @Override
    public JsonStringer value(long l)
            throws JSONException {
        super.value(l);
        return this;
    }

    @Override
    public JsonStringer value(Object object)
            throws JSONException {
        super.value(object);
        return this;
    }

    public JsonStringer entry(String key, boolean value) {
        this.key(key);
        this.value(value);
        return this;
    }

    public JsonStringer entry(String key, double value) {
        this.key(key);
        this.value(value);
        return this;
    }

    public JsonStringer entry(String key, long value) {
        this.key(key);
        this.value(value);
        return this;
    }

    public JsonStringer entry(String key, Object value) {
        this.key(key);
        this.value(value);
        return this;
    }

}
