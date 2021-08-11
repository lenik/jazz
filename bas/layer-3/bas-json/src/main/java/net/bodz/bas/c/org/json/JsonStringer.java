package net.bodz.bas.c.org.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.json.JSONException;
import net.bodz.json.JSONStringer;

/**
 * @see JsonBuffer
 */
public class JsonStringer
        extends JSONStringer
        implements IJsonOut {

    public JsonStringer() {
    }

    @Override
    public JsonStringer verbatim(String code)
            throws JSONException {
        append(code);
        return this;
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
    public JsonStringer object(Object object)
            throws JSONException {
        try {
            fn.dumpTree(this, object);
        } catch (Exception e) {
            throw new JSONException(e);
        }
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

    @Override
    public JsonStringer entry(String key, boolean value) {
        this.key(key);
        this.value(value);
        return this;
    }

    @Override
    public JsonStringer entry(String key, double value) {
        this.key(key);
        this.value(value);
        return this;
    }

    @Override
    public JsonStringer entry(String key, long value) {
        this.key(key);
        this.value(value);
        return this;
    }

    @Override
    public JsonStringer entry(String key, Object value) {
        this.key(key);
        try {
            fn.dumpTree(this, value);
        } catch (IOException e) {
            throw new JSONException(e);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return this;
    }

    @Override
    public final IJsonOut entryNotNull(String key, Object value) {
        if (value != null)
            entry(key, value);
        return this;
    }

}
