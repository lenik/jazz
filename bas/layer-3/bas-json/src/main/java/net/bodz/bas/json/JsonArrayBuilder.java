package net.bodz.bas.json;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import net.bodz.fork.org.json.JSONException;
import net.bodz.fork.org.json.JSONTokener;
import net.bodz.fork.org.json._JSONObject;

public class JsonArrayBuilder {

    protected ArrayList<Object> createList() {
        return createList(4);
    }

    protected ArrayList<Object> createList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

    public JsonArray create() {
        ArrayList<Object> list = createList();
        return new JsonArray(list);
    }

    /**
     * Construct a JSONArray from a JSONTokener.
     *
     * @param x
     *            A JSONTokener
     * @throws JSONException
     *             If there is a syntax error.
     */
    public JsonArray parse(JSONTokener x) {
        ArrayList<Object> list = _parse(x);
        return new JsonArray(list);
    }

    public ArrayList<Object> _parse(JSONTokener x)
            throws JSONException {
        ArrayList<Object> list = createList();
        if (x.nextClean() != '[') {
            throw x.syntaxError("A JSONArray text must start with '['");
        }

        char nextChar = x.nextClean();
        if (nextChar == 0) {
            // array is unclosed. No ']' found, instead EOF
            throw x.syntaxError("Expected a ',' or ']'");
        }
        if (nextChar != ']') {
            x.back();
            for (;;) {
                if (x.nextClean() == ',') {
                    x.back();
                    list.add(_JSONObject.NULL);
                } else {
                    x.back();
                    list.add(x.nextValue());
                }
                switch (x.nextClean()) {
                case 0:
                    // array is unclosed. No ']' found, instead EOF
                    throw x.syntaxError("Expected a ',' or ']'");
                case ',':
                    nextChar = x.nextClean();
                    if (nextChar == 0) {
                        // array is unclosed. No ']' found, instead EOF
                        throw x.syntaxError("Expected a ',' or ']'");
                    }
                    if (nextChar == ']') {
                        return list;
                    }
                    x.back();
                    break;
                case ']':
                    return list;
                default:
                    throw x.syntaxError("Expected a ',' or ']'");
                }
            }
        }
        return list;
    }

    /**
     * Construct a JSONArray from a source JSON text.
     *
     * @param source
     *            A string that begins with <code>[</code>&nbsp;<small>(left bracket)</small> and
     *            ends with <code>]</code> &nbsp;<small>(right bracket)</small>.
     * @throws JSONException
     *             If there is a syntax error.
     */
    public JsonArray parse(String source)
            throws JSONException {
        return parse(new JSONTokener(source));
    }

    /**
     * Construct a JSONArray from a Collection.
     *
     * @param collection
     *            A Collection.
     */
    public JsonArray convert(Collection<?> collection) {
        ArrayList<Object> list;
        if (collection == null) {
            list = createList();
        } else {
            list = createList(collection.size());
            for (Object o : collection) {
                list.add(_JSONObject.wrap(o));
            }
        }
        return new JsonArray(list);
    }

    /**
     * Construct a JSONArray from an array.
     *
     * @param array
     *            Array. If the parameter passed is null, or not an array, an exception will be
     *            thrown.
     *
     * @throws JSONException
     *             If not an array or if an array value is non-finite number.
     * @throws NullPointerException
     *             Thrown if the array parameter is null.
     */
    public JsonArray convert(Object array)
            throws JSONException {
        if (!array.getClass().isArray())
            throw new JSONException("Not an array.");

        int length = Array.getLength(array);
        ArrayList<Object> list = createList(length);
        JsonArray _this = new JsonArray(list);
        for (int i = 0; i < length; i += 1) {
            _this.put(_JSONObject.wrap(Array.get(array, i)));
        }
        return _this;
    }

    private JsonArrayBuilder() {
    }

    static JsonArrayBuilder instance = new JsonArrayBuilder();

    public static JsonArrayBuilder getInstance() {
        return instance;
    }

}
