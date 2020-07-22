package net.bodz.bas.fmt.json;

import java.util.*;


import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.t.factory.IFactory;
import net.bodz.json.JSONArray;
import net.bodz.json.JSONException;
import net.bodz.json.JSONObject;
import net.bodz.json.JSONTokener;

public class JsonObject
        extends AbstractJsonObject<JsonObject> {

    public JsonObject(JSONObject jsonObj) {
        super(jsonObj);
    }

    /**
     * Construct an empty JSONObject.
     */
    public JsonObject() {
        this(new JSONObject());
    }

    /**
     * Construct a JSONObject from a subset of another JSONObject. An array of strings is used to
     * identify the keys that should be copied. Missing keys are ignored.
     *
     * @param jo
     *            A JSONObject.
     * @param names
     *            An array of strings.
     * @throws JSONException
     * @exception JSONException
     *                If a value is a non-finite number or if a name is duplicated.
     */
    public JsonObject(JSONObject jo, String[] names) {
        this(new JSONObject(jo, names));
    }

    /**
     * Construct a JSONObject from a JSONTokener.
     *
     * @param x
     *            A JSONTokener object containing the source string.
     * @throws JSONException
     *             If there is a syntax error in the source string or a duplicated key.
     */
    public JsonObject(JSONTokener x)
            throws JSONException {
        this(new JSONObject(x));
    }

    /**
     * Construct a JSONObject from a Map.
     *
     * @param map
     *            A map object that can be used to initialize the contents of the JSONObject.
     * @throws JSONException
     */
    public JsonObject(Map<String, ?> map) {
        this(new JSONObject(map));
    }

    /**
     * Construct a JSONObject from an Object using bean getters. It reflects on all of the public
     * methods of the object. For each of the methods with no parameters and a name starting with
     * <code>"get"</code> or <code>"is"</code> followed by an uppercase letter, the method is
     * invoked, and a key and the value returned from the getter method are put into the new
     * JSONObject.
     *
     * The key is formed by removing the <code>"get"</code> or <code>"is"</code> prefix. If the
     * second remaining character is not upper case, then the first character is converted to lower
     * case.
     *
     * For example, if an object has a method named <code>"getName"</code>, and if the result of
     * calling <code>object.getName()</code> is <code>"Larry Fine"</code>, then the JSONObject will
     * contain <code>"name": "Larry Fine"</code>.
     *
     * @param bean
     *            An object that has getter methods that should be used to make a JSONObject.
     */
    public JsonObject(Object bean) {
        this(new JSONObject(bean));
    }

    /**
     * Construct a JSONObject from an Object, using reflection to find the public members. The
     * resulting JSONObject's keys will be the strings from the names array, and the values will be
     * the field values associated with those keys in the object. If a key is not found or not
     * visible, then it will not be copied into the new JSONObject.
     *
     * @param object
     *            An object that has fields that should be used to make a JSONObject.
     * @param names
     *            An array of strings, the names of the fields to be obtained from the object.
     */
    public JsonObject(Object object, String names[]) {
        this(new JSONObject(object, names));
    }

    /**
     * Construct a JSONObject from a source JSON text string. This is the most commonly used
     * JSONObject constructor.
     *
     * @param source
     *            A string beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *            with <code>}</code> &nbsp;<small>(right brace)</small>.
     * @exception JSONException
     *                If there is a syntax error in the source string or a duplicated key.
     */
    public JsonObject(String source)
            throws JSONException {
        this(new JSONObject(source));
    }

    /**
     * Construct a JSONObject from a ResourceBundle.
     *
     * @param baseName
     *            The ResourceBundle base name.
     * @param locale
     *            The Locale to load the ResourceBundle for.
     * @throws JSONException
     *             If any JSONExceptions are detected.
     */
    public JsonObject(String baseName, Locale locale)
            throws JSONException {
        this(new JSONObject(baseName, locale));
    }

    public static JsonObject wrap(JSONObject jsonObj) {
        return new JsonObject(jsonObj);
    }

    public <T extends IJsonSerializable> T readInto(String key, T obj)
            throws ParseException {
        return readInto(key, obj, null);
    }

    public <T extends IJsonSerializable> T readInto(String key, T obj, T newObj)
            throws ParseException {
        if (!has(key)) // nothing to change
            return obj;

        Object _node = get(key);
        if (_node == null) // force set to null
            return null;

        if (_node instanceof JSONObject) {
            JsonObject node = JsonObject.wrap((JSONObject) _node);
            if (obj == null) {
                if (newObj == null) // don't auto-create
                    return null;
                obj = newObj;
            }
            obj.readObject(node);
        }

        return obj;
    }

    public <T extends IJsonSerializable> List<T> readArrayInto(String key, List<T> list, IFactory<T> factory)
            throws ParseException {
        return readArrayInto(key, list, factory, ArrayList.class);
    }

    public <T extends IJsonSerializable> Set<T> readArrayInto(String key, Set<T> list, IFactory<T> factory)
            throws ParseException {
        return readArrayInto(key, list, factory, TreeSet.class);
    }

    public <C extends Collection<T>, T extends IJsonSerializable> C readArrayInto(String key, C set,
            IFactory<T> factory, Class<?> newSetClass)
            throws ParseException {
        if (!has(key)) // nothing to change
            return set;

        Object _node = get(key);
        if (_node == null) // force set to null
            return null;

        if (_node instanceof JSONArray) {
            JsonArray array = JsonArray.wrap((JSONArray) _node);
            if (set == null) {
                if (newSetClass == null) // don't auto-create
                    return null;
                try {
                    set = (C) newSetClass.newInstance();
                } catch (Exception e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
            } else {
                set.clear();
            }

            // set.readObject(array);
            int n = array.length();
            for (int i = 0; i < n; i++) {
                JsonObject node = array.getJsonObject(i);
                T obj = factory.create();
                obj.readObject(node);
                set.add(obj);
            }
        }

        return set;
    }

}
