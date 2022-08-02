package net.bodz.bas.fmt.json;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.t.list.LinkedStack;
import net.bodz.bas.t.list.Stack;
import net.bodz.bas.t.model.IWrapper;
import net.bodz.fork.org.json.JSONException;

public class AutofixJsonOut
        implements
            IJsonOut,
            IWrapper<IJsonOut> {

    private final IJsonOut out;

    Stack<JsonOutState> states = new LinkedStack<>();
    boolean autoFix = true;
    String autoFixPrefix = "AuToFiX_";

    static Random random = new Random();

    public AutofixJsonOut(IJsonOut impl) {
        this(impl, null);
    }

    public AutofixJsonOut(IJsonOut impl, JsonOutState startState) {
        if (impl == null)
            throw new NullPointerException("impl");
        this.out = impl;

        if (startState != null)
            states.push(startState);
    }

    @Override
    public IJsonOut getWrapped() {
        return out;
    }

    public JsonOutState state() {
        if (states.isEmpty())
            return JsonOutState.INIT;
        else
            return states.top();
    }

    public void enter(JsonOutState newState) {
        states.push(newState);
    }

    public JsonOutState leave() {
        return states.pop();
    }

    public IJsonOut verbatim(String code)
            throws JSONException {
        out.verbatim(code);
        return this;
    }

    void beforeValue() {
        switch (state()) {
        case OBJECT:
        case OBJECT_AUTOFIX:
            if (autoFix) {
                String randomKey = autoFixPrefix + random.nextInt(1000000000);
                out.key(randomKey);
            }
            break;
        default:
        }
    }

    void beforeEntry() {
        switch (state()) {
        case INIT:
        case ARRAY:
            if (autoFix) {
                out.object();
                enter(JsonOutState.OBJECT);
            }
            break;
        default:
        }
    }

    @Override
    public IJsonOut array()
            throws JSONException {
        beforeValue();
        enter(JsonOutState.ARRAY);
        out.array();
        return this;
    }

    @Override
    public IJsonOut endArray()
            throws JSONException {
        out.endArray();
        JsonOutState top = leave();
        assert top == JsonOutState.ARRAY;
        return this;
    }

    @Override
    public IJsonOut endObject()
            throws JSONException {
        JsonOutState top;
        do {
            out.endObject();
            top = leave();
        } while (top == JsonOutState.OBJECT_AUTOFIX);
        assert top == JsonOutState.OBJECT;
        return this;
    }

    public IJsonOut key(String string)
            throws JSONException {
        switch (state()) {
        case OBJECT:
        case OBJECT_AUTOFIX:
            break;

        case INIT:
        case ARRAY:
            if (autoFix) {
                out.object();
                enter(JsonOutState.OBJECT);
            }
            break;

        case KEY:
            if (autoFix) {
                out.value(StringQuote.qqJavaString(autoFixPrefix + "NOT_USED_KEY"));
            }
            break;

        }
        out.key(string);
        return this;
    }

    public IJsonOut object()
            throws JSONException {
        beforeValue();
        out.object();
        enter(JsonOutState.OBJECT);
        return this;
    }

    public IJsonOut value(boolean b)
            throws JSONException {
        beforeValue();
        out.value(b);
        return this;
    }

    public IJsonOut value(double d)
            throws JSONException {
        beforeValue();
        out.value(d);
        return this;
    }

    public IJsonOut value(long l)
            throws JSONException {
        beforeValue();
        out.value(l);
        return this;
    }

    public IJsonOut value(Object object)
            throws JSONException {
        beforeValue();
        out.value(object);
        return this;
    }

    public IJsonOut entry(String key, boolean value)
            throws JSONException {
        beforeEntry();
        out.entry(key, value);
        return this;
    }

    public IJsonOut entry(String key, double value)
            throws JSONException {
        beforeEntry();
        out.entry(key, value);
        return this;
    }

    public IJsonOut entry(String key, long value)
            throws JSONException {
        beforeEntry();
        out.entry(key, value);
        return this;
    }

    public IJsonOut entry(String key, Object value)
            throws JSONException {
        beforeEntry();
        out.entry(key, value);
        return this;
    }

    public IJsonOut entryNotNull(String key, Object value)
            throws JSONException {
        beforeEntry();
        out.entryNotNull(key, value);
        return this;
    }

    public IJsonOut object(Object object)
            throws JSONException {
        beforeValue();
        out.object(object);
        return this;
    }

    public IJsonOut array(String key, Object array) {
        beforeEntry();
        out.array(key, array);
        return this;
    }

    public IJsonOut array(Object array)
            throws JSONException {
        beforeValue();
        out.array(array);
        return this;
    }

    public IJsonOut array(String key, Collection<?> collection) {
        beforeEntry();
        out.array(key, collection);
        return this;
    }

    public IJsonOut array(Collection<?> collection)
            throws JSONException {
        beforeValue();
        out.array(collection);
        return this;
    }

    public IJsonOut map(String key, Map<String, ?> map) {
        beforeEntry();
        out.map(key, map);
        return this;
    }

    public IJsonOut map(Map<String, ?> map)
            throws JSONException {
        beforeValue();
        out.map(map);
        return this;
    }

    @Override
    public String toString() {
        return out.toString();
    }

}