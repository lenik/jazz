package net.bodz.fork.org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonArrayBuilder;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;
import net.bodz.bas.t.variant.AbstractVariantMap;
/*
  Copyright (c) 2002 JSON.org

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 The Software shall be used for Good, not Evil.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

/**
 * A _JSONObject is an unordered collection of name/value pairs. Its external form is a string
 * wrapped in curly braces with colons between the names and values, and commas between the values
 * and names. The internal form is an object having <code>get</code> and <code>opt</code> methods
 * for accessing the values by name, and <code>put</code> methods for adding or replacing values by
 * name. The values can be any of these types: <code>Boolean</code>, <code>_JSONArray</code>,
 * <code>_JSONObject</code>, <code>Number</code>, <code>String</code>, or the
 * <code>_JSONObject.NULL</code> object. A _JSONObject constructor can be used to convert an
 * external form JSON text into an internal form whose values can be retrieved with the
 * <code>get</code> and <code>opt</code> methods, or to convert values into a JSON text using the
 * <code>put</code> and <code>toString</code> methods. A <code>get</code> method returns a value if
 * one can be found, and throws an exception if one cannot be found. An <code>opt</code> method
 * returns a default value instead of throwing an exception, and so is useful for obtaining optional
 * values.
 * <p>
 * The generic <code>get()</code> and <code>opt()</code> methods return an object, which you can
 * cast or query for type. There are also typed <code>get</code> and <code>opt</code> methods that
 * do type checking and type coercion for you. The opt methods differ from the get methods in that
 * they do not throw. Instead, they return a specified value, such as null.
 * <p>
 * The <code>put</code> methods add or replace values in an object. For example,
 *
 * <pre>
 * myString = new _JSONObject().put(&quot;JSON&quot;, &quot;Hello, World!&quot;).toString();
 * </pre>
 *
 * produces the string <code>{"JSON": "Hello, World"}</code>.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to the JSON syntax
 * rules. The constructors are more forgiving in the texts they will accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just before the closing
 * brace.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a quote or single quote,
 * and if they do not contain leading or trailing spaces, and if they do not contain any of these
 * characters: <code>{ } [ ] / \ : , #</code> and if they do not look like numbers and if they are
 * not the reserved words <code>true</code>, <code>false</code>, or <code>null</code>.</li>
 * </ul>
 *
 * @author JSON.org
 * @version 2016-08-15
 */
public class _JSONObject
        extends AbstractVariantMap<String> {

    /**
     * _JSONObject.NULL is equivalent to the value that JavaScript calls null, whilst Java's null is
     * equivalent to the value that JavaScript calls undefined.
     */
    private static final class Null {

        /**
         * There is only intended to be a single instance of the NULL object, so the clone method
         * returns itself.
         *
         * @return NULL.
         */
        @Override
        protected final Object clone() {
            return this;
        }

        /**
         * A Null object is equal to the null value and to itself.
         *
         * @param object
         *            An object to test for nullness.
         * @return true if the object parameter is the _JSONObject.NULL object or null.
         */
        @Override
        public boolean equals(Object object) {
            return object == null || object == this;
        }

        /**
         * A Null object is equal to the null value and to itself.
         *
         * @return always returns 0.
         */
        @Override
        public int hashCode() {
            return 0;
        }

        /**
         * Get the "null" string value.
         *
         * @return The string "null".
         */
        @Override
        public String toString() {
            return "null";
        }
    }

    /**
     * Regular Expression Pattern that matches JSON Numbers. This is primarily used for output to
     * guarantee that we are always writing valid JSON.
     */
    static final Pattern NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");

    /**
     * The map where the _JSONObject's properties are kept.
     */
    private final Map<String, Object> map;

    /**
     * It is sometimes more convenient and less ambiguous to have a <code>NULL</code> object than to
     * use Java's <code>null</code> value. <code>_JSONObject.NULL.equals(null)</code> returns
     * <code>true</code>. <code>_JSONObject.NULL.toString()</code> returns <code>"null"</code>.
     */
    public static final Object NULL = new Null();

    public _JSONObject(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException("key");
        return map.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        if (key == null)
            throw new NullPointerException("key");
        Object val = map.get(key);
        if (val == NULL)
            return null;
        return val;
    }

    @Override
    public Object get(Object key, Object defaultValue) {
        if (key == null)
            throw new NullPointerException("key");
        Object val = map.get(key);
        if (val == NULL)
            return null;
        if (val == null)
            return defaultValue;
        return val;
    }

    /**
     * Accumulate values under a key. It is similar to the put method except that if there is
     * already an object stored under the key then a _JSONArray is stored under the key to hold all
     * of the accumulated values. If there is already a _JSONArray, then the new value is appended
     * to it. In contrast, the put method replaces the previous value.
     *
     * If only one value is accumulated that is not a _JSONArray, then the result will be the same
     * as using put. But if multiple values are accumulated, then the result will be like append.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object to be accumulated under the key.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject accumulate(String key, Object value)
            throws JSONException {
        testValidity(value);
        Object object = this.get(key);
        if (object == null) {
            this.put(key, value instanceof _JSONArray ? new _JSONArray().put(value) : value);
        } else if (object instanceof _JSONArray) {
            ((_JSONArray) object).put(value);
        } else {
            this.put(key, new _JSONArray().put(object).put(value));
        }
        return this;
    }

    /**
     * Append values to the array under a key. If the key does not exist in the _JSONObject, then
     * the key is put in the _JSONObject with its value being a _JSONArray containing the value
     * parameter. If the key was already associated with a _JSONArray, then the value parameter is
     * appended to it.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object to be accumulated under the key.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number or if the current value associated with the key
     *             is not a _JSONArray.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject append(String key, Object value)
            throws JSONException {
        testValidity(value);
        Object object = this.get(key);
        if (object == null) {
            this.put(key, new _JSONArray().put(value));
        } else if (object instanceof _JSONArray) {
            this.put(key, ((_JSONArray) object).put(value));
        } else {
            throw wrongValueFormatException(key, "_JSONArray", null, null);
        }
        return this;
    }

    /**
     * Produce a string from a double. The string "null" will be returned if the number is not
     * finite.
     *
     * @param d
     *            A double.
     * @return A String.
     */
    public static String doubleToString(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return "null";
        }

// Shave off trailing zeros and decimal point, if possible.

        String string = Double.toString(d);
        if (string.indexOf('.') > 0 && string.indexOf('e') < 0 && string.indexOf('E') < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }

    /**
     * Get an array of field names from a _JSONObject.
     *
     * @param jo
     *            JSON object
     * @return An array of field names, or null if there are no names.
     */
    public static String[] getNames(_JSONObject jo) {
        if (jo.isEmpty()) {
            return null;
        }
        return jo.keySet().toArray(new String[jo.length()]);
    }

    /**
     * Get an array of public field names from an Object.
     *
     * @param object
     *            object to read
     * @return An array of field names, or null if there are no names.
     */
    public static String[] getNames(Object object) {
        if (object == null) {
            return null;
        }
        Class<?> klass = object.getClass();
        Field[] fields = klass.getFields();
        int length = fields.length;
        if (length == 0) {
            return null;
        }
        String[] names = new String[length];
        for (int i = 0; i < length; i += 1) {
            names[i] = fields[i].getName();
        }
        return names;
    }

    /**
     * Determine if the _JSONObject contains a specific key.
     *
     * @param key
     *            A key string.
     * @return true if the key exists in the _JSONObject.
     */
    public boolean has(String key) {
        return this.map.containsKey(key);
    }

    public JsonObject getJsonObject(String key) {
        return getJsonObject(key, null);
    }

    public JsonObject getJsonObject(String key, JsonObject defaultValue) {
        Object value = get(key);
        if (value == NULL)
            return null;
        if (value == null)
            return defaultValue;
        if (value instanceof JsonObject)
            return (JsonObject) value;
        throw new IllegalArgumentException("Child isn'ot a json object: " + key);
    }

    public JsonArray getJsonArray(String key) {
        return getJsonArray(key, null);
    }

    public JsonArray getJsonArray(String key, JsonArray defaultValue) {
        Object value = get(key);
        if (value == NULL)
            return null;
        if (value == null)
            return defaultValue;
        if (value instanceof JsonArray)
            return (JsonArray) value;
        throw new IllegalArgumentException("Child isn'ot a json array: " + key);
    }

    /**
     * Increment a property of a _JSONObject. If there is no such property, create one with a value
     * of 1 (Integer). If there is such a property, and if it is an Integer, Long, Double, Float,
     * BigInteger, or BigDecimal then add one to it. No overflow bounds checking is performed, so
     * callers should initialize the key prior to this call with an appropriate type that can handle
     * the maximum expected value.
     *
     * @param key
     *            A key string.
     * @return this.
     * @throws JSONException
     *             If there is already a property with this name that is not an Integer, Long,
     *             Double, or Float.
     */
    public _JSONObject increment(String key)
            throws JSONException {
        Object value = this.get(key);
        if (value == null) {
            this.put(key, 1);
        } else if (value instanceof Integer) {
            this.put(key, ((Integer) value).intValue() + 1);
        } else if (value instanceof Long) {
            this.put(key, ((Long) value).longValue() + 1L);
        } else if (value instanceof BigInteger) {
            this.put(key, ((BigInteger) value).add(BigInteger.ONE));
        } else if (value instanceof Float) {
            this.put(key, ((Float) value).floatValue() + 1.0f);
        } else if (value instanceof Double) {
            this.put(key, ((Double) value).doubleValue() + 1.0d);
        } else if (value instanceof BigDecimal) {
            this.put(key, ((BigDecimal) value).add(BigDecimal.ONE));
        } else {
            throw new JSONException("Unable to increment [" + quote(key) + "].");
        }
        return this;
    }

    /**
     * Determine if the value associated with the key is <code>null</code> or if there is no value.
     *
     * @param key
     *            A key string.
     * @return true if there is no value associated with the key or if the value is the
     *         _JSONObject.NULL object.
     */
    public boolean isNull(String key) {
        return _JSONObject.NULL.equals(this.get(key));
    }

    /**
     * Get an enumeration of the keys of the _JSONObject. Modifying this key Set will also modify
     * the _JSONObject. Use with caution.
     *
     * @see Set#iterator()
     *
     * @return An iterator of the keys.
     */
    public Iterator<String> keys() {
        return this.keySet().iterator();
    }

    /**
     * Get a set of keys of the _JSONObject. Modifying this key Set will also modify the
     * _JSONObject. Use with caution.
     *
     * @see Map#keySet()
     *
     * @return A keySet.
     */
    public Set<String> keySet() {
        return this.map.keySet();
    }

    /**
     * Get a set of entries of the _JSONObject. These are raw values and may not match what is
     * returned by the _JSONObject get* and opt* functions. Modifying the returned EntrySet or the
     * Entry objects contained therein will modify the backing _JSONObject. This does not return a
     * clone or a read-only view.
     *
     * Use with caution.
     *
     * @see Map#entrySet()
     *
     * @return An Entry Set
     */
    protected Set<Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    /**
     * Get the number of keys stored in the _JSONObject.
     *
     * @return The number of keys in the _JSONObject.
     */
    public int length() {
        return this.map.size();
    }

    /**
     * Check if _JSONObject is empty.
     *
     * @return true if _JSONObject is empty, otherwise false.
     */
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    /**
     * Produce a _JSONArray containing the names of the elements of this _JSONObject.
     *
     * @return A _JSONArray containing the key strings, or null if the _JSONObject is empty.
     */
    public JsonArray names() {
        if (this.map.isEmpty()) {
            return null;
        }
        return JsonArrayBuilder.getInstance().convert(this.map.keySet());
    }

    /**
     * Produce a string from a Number.
     *
     * @param number
     *            A Number
     * @return A String.
     * @throws JSONException
     *             If n is a non-finite number.
     */
    public static String numberToString(Number number)
            throws JSONException {
        if (number == null) {
            throw new JSONException("Null pointer");
        }
        testValidity(number);

        // Shave off trailing zeros and decimal point, if possible.

        String string = number.toString();
        if (string.indexOf('.') > 0 && string.indexOf('e') < 0 && string.indexOf('E') < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }

    /**
     * Put a key/boolean pair in the _JSONObject.
     *
     * @param key
     *            A key string.
     * @param value
     *            A boolean which is the value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, boolean value)
            throws JSONException {
        return this.put(key, value ? Boolean.TRUE : Boolean.FALSE);
    }

    /**
     * Put a key/value pair in the _JSONObject, where the value will be a _JSONArray which is
     * produced from a Collection.
     *
     * @param key
     *            A key string.
     * @param value
     *            A Collection value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, Collection<?> value)
            throws JSONException {
        return this.put(key, JsonArrayBuilder.getInstance().convert(value));
    }

    /**
     * Put a key/double pair in the _JSONObject.
     *
     * @param key
     *            A key string.
     * @param value
     *            A double which is the value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, double value)
            throws JSONException {
        return this.put(key, Double.valueOf(value));
    }

    /**
     * Put a key/float pair in the _JSONObject.
     *
     * @param key
     *            A key string.
     * @param value
     *            A float which is the value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, float value)
            throws JSONException {
        return this.put(key, Float.valueOf(value));
    }

    /**
     * Put a key/int pair in the _JSONObject.
     *
     * @param key
     *            A key string.
     * @param value
     *            An int which is the value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, int value)
            throws JSONException {
        return this.put(key, Integer.valueOf(value));
    }

    /**
     * Put a key/long pair in the _JSONObject.
     *
     * @param key
     *            A key string.
     * @param value
     *            A long which is the value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, long value)
            throws JSONException {
        return this.put(key, Long.valueOf(value));
    }

    /**
     * Put a key/value pair in the _JSONObject, where the value will be a _JSONObject which is
     * produced from a Map.
     *
     * @param key
     *            A key string.
     * @param value
     *            A Map value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, Map<?, ?> value)
            throws JSONException {
        return this.put(key, JsonObjectBuilder.getInstance().fromMap(value));
    }

    /**
     * Put a key/value pair in the _JSONObject. If the value is <code>null</code>, then the key will
     * be removed from the _JSONObject if it is present.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object which is the value. It should be of one of these types: Boolean, Double,
     *            Integer, _JSONArray, _JSONObject, Long, String, or the _JSONObject.NULL object.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     * @throws NullPointerException
     *             If the key is <code>null</code>.
     */
    public _JSONObject put(String key, Object value)
            throws JSONException {
        if (key == null) {
            throw new NullPointerException("Null key.");
        }
        if (value != null) {
            testValidity(value);
            this.map.put(key, value);
        } else {
            this.remove(key);
        }
        return this;
    }

    /**
     * Put a key/value pair in the _JSONObject, but only if the key and the value are both non-null,
     * and only if there is not already a member with that name.
     *
     * @param key
     *            key to insert into
     * @param value
     *            value to insert
     * @return this.
     * @throws JSONException
     *             if the key is a duplicate
     */
    public _JSONObject putOnce(String key, Object value)
            throws JSONException {
        if (key != null && value != null) {
            if (this.get(key) != null) {
                throw new JSONException("Duplicate key \"" + key + "\"");
            }
            return this.put(key, value);
        }
        return this;
    }

    /**
     * Put a key/value pair in the _JSONObject, but only if the key and the value are both non-null.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object which is the value. It should be of one of these types: Boolean, Double,
     *            Integer, _JSONArray, _JSONObject, Long, String, or the _JSONObject.NULL object.
     * @return this.
     * @throws JSONException
     *             If the value is a non-finite number.
     */
    public _JSONObject putOpt(String key, Object value)
            throws JSONException {
        if (key != null && value != null) {
            return this.put(key, value);
        }
        return this;
    }

    /**
     * Creates a JSONPointer using an initialization string and tries to match it to an item within
     * this _JSONObject. For example, given a _JSONObject initialized with this document:
     *
     * <pre>
     * {
     *     "a":{"b":"c"}
     * }
     * </pre>
     *
     * and this JSONPointer string:
     *
     * <pre>
     * "/a/b"
     * </pre>
     *
     * Then this method will return the String "c". A JSONPointerException may be thrown from code
     * called by this method.
     *
     * @param jsonPointer
     *            string that can be used to create a JSONPointer
     * @return the item matched by the JSONPointer, otherwise null
     */
    public Object query(String jsonPointer) {
        return query(new JSONPointer(jsonPointer));
    }

    /**
     * Uses a user initialized JSONPointer and tries to match it to an item within this _JSONObject.
     * For example, given a _JSONObject initialized with this document:
     *
     * <pre>
     * {
     *     "a":{"b":"c"}
     * }
     * </pre>
     *
     * and this JSONPointer:
     *
     * <pre>
     * "/a/b"
     * </pre>
     *
     * Then this method will return the String "c". A JSONPointerException may be thrown from code
     * called by this method.
     *
     * @param jsonPointer
     *            string that can be used to create a JSONPointer
     * @return the item matched by the JSONPointer, otherwise null
     */
    public Object query(JSONPointer jsonPointer) {
        return jsonPointer.queryFrom(this);
    }

    /**
     * Queries and returns a value from this object using {@code jsonPointer}, or returns null if
     * the query fails due to a missing key.
     *
     * @param jsonPointer
     *            the string representation of the JSON pointer
     * @return the queried value or {@code null}
     * @throws IllegalArgumentException
     *             if {@code jsonPointer} has invalid syntax
     */
    public Object optQuery(String jsonPointer) {
        return optQuery(new JSONPointer(jsonPointer));
    }

    /**
     * Queries and returns a value from this object using {@code jsonPointer}, or returns null if
     * the query fails due to a missing key.
     *
     * @param jsonPointer
     *            The JSON pointer
     * @return the queried value or {@code null}
     * @throws IllegalArgumentException
     *             if {@code jsonPointer} has invalid syntax
     */
    public Object optQuery(JSONPointer jsonPointer) {
        try {
            return jsonPointer.queryFrom(this);
        } catch (JSONPointerException e) {
            return null;
        }
    }

    /**
     * Produce a string in double quotes with backslash sequences in all the right places. A
     * backslash will be inserted within &lt;/, producing &lt;\/, allowing JSON text to be delivered
     * in HTML. In JSON text, a string cannot contain a control character or an unescaped quote or
     * backslash.
     *
     * @param string
     *            A String
     * @return A String correctly formatted for insertion in a JSON text.
     */
    public static String quote(String string) {
        StringWriter sw = new StringWriter();
        synchronized (sw.getBuffer()) {
            try {
                return quote(string, sw).toString();
            } catch (IOException ignored) {
                // will never happen - we are writing to a string writer
                return "";
            }
        }
    }

    public static Writer quote(String string, Writer w)
            throws IOException {
        if (string == null || string.isEmpty()) {
            w.write("\"\"");
            return w;
        }

        char b;
        char c = 0;
        String hhhh;
        int i;
        int len = string.length();

        w.write('"');
        for (i = 0; i < len; i += 1) {
            b = c;
            c = string.charAt(i);
            switch (c) {
            case '\\':
            case '"':
                w.write('\\');
                w.write(c);
                break;
            case '/':
                if (b == '<') {
                    w.write('\\');
                }
                w.write(c);
                break;
            case '\b':
                w.write("\\b");
                break;
            case '\t':
                w.write("\\t");
                break;
            case '\n':
                w.write("\\n");
                break;
            case '\f':
                w.write("\\f");
                break;
            case '\r':
                w.write("\\r");
                break;
            default:
                if (c < ' ' || (c >= '\u0080' && c < '\u00a0') || (c >= '\u2000' && c < '\u2100')) {
                    w.write("\\u");
                    hhhh = Integer.toHexString(c);
                    w.write("0000", 0, 4 - hhhh.length());
                    w.write(hhhh);
                } else {
                    w.write(c);
                }
            }
        }
        w.write('"');
        return w;
    }

    /**
     * Remove a name and its value, if present.
     *
     * @param key
     *            The name to be removed.
     * @return The value that was associated with the name, or null if there was no value.
     */
    public Object remove(String key) {
        return this.map.remove(key);
    }

    /**
     * Determine if two JSONObjects are similar. They must contain the same set of names which must
     * be associated with similar values.
     *
     * @param other
     *            The other _JSONObject
     * @return true if they are equal
     */
    public boolean similar(Object other) {
        try {
            if (!(other instanceof _JSONObject)) {
                return false;
            }
            if (!this.keySet().equals(((_JSONObject) other).keySet())) {
                return false;
            }
            for (final Entry<String, ?> entry : this.entrySet()) {
                String name = entry.getKey();
                Object valueThis = entry.getValue();
                Object valueOther = ((_JSONObject) other).get(name);
                if (valueThis == valueOther) {
                    continue;
                }
                if (valueThis == null || valueOther == null) {
                    return false;
                }
                if (valueThis instanceof _JSONObject) {
                    if (!((_JSONObject) valueThis).similar(valueOther)) {
                        return false;
                    }
                } else if (valueThis instanceof _JSONArray) {
                    if (!((_JSONArray) valueThis).similar(valueOther)) {
                        return false;
                    }
                } else if (!valueThis.equals(valueOther)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable exception) {
            return false;
        }
    }

    /**
     * Tests if the value should be tried as a decimal. It makes no test if there are actual digits.
     *
     * @param val
     *            value to test
     * @return true if the string is "-0" or if it contains '.', 'e', or 'E', false otherwise.
     */
    protected static boolean isDecimalNotation(final String val) {
        return val.indexOf('.') > -1 || val.indexOf('e') > -1 || val.indexOf('E') > -1 || "-0".equals(val);
    }

    /**
     * Converts a string to a number using the narrowest possible type. Possible returns for this
     * function are BigDecimal, Double, BigInteger, Long, and Integer. When a Double is returned, it
     * should always be a valid Double and not NaN or +-infinity.
     *
     * @param val
     *            value to convert
     * @return Number representation of the value.
     * @throws NumberFormatException
     *             thrown if the value is not a valid number. A public caller should catch this and
     *             wrap it in a {@link JSONException} if applicable.
     */
    protected static Number stringToNumber(final String val)
            throws NumberFormatException {
        char initial = val.charAt(0);
        if ((initial >= '0' && initial <= '9') || initial == '-') {
            // decimal representation
            if (isDecimalNotation(val)) {
                // quick dirty way to see if we need a BigDecimal instead of a Double
                // this only handles some cases of overflow or underflow
                if (val.length() > 14) {
                    return new BigDecimal(val);
                }
                final Double d = Double.valueOf(val);
                if (d.isInfinite() || d.isNaN()) {
                    // if we can't parse it as a double, go up to BigDecimal
                    // this is probably due to underflow like 4.32e-678
                    // or overflow like 4.65e5324. The size of the string is small
                    // but can't be held in a Double.
                    return new BigDecimal(val);
                }
                return d;
            }
            // integer representation.
            // This will narrow any values to the smallest reasonable Object representation
            // (Integer, Long, or BigInteger)

            // string version
            // The compare string length method reduces GC,
            // but leads to smaller integers being placed in larger wrappers even though not
            // needed. i.e. 1,000,000,000 -> Long even though it's an Integer
            // 1,000,000,000,000,000,000 -> BigInteger even though it's a Long
            // if(val.length()<=9){
            // return Integer.valueOf(val);
            // }
            // if(val.length()<=18){
            // return Long.valueOf(val);
            // }
            // return new BigInteger(val);

            // BigInteger version: We use a similar bitLength compare as
            // BigInteger#intValueExact uses. Increases GC, but objects hold
            // only what they need. i.e. Less runtime overhead if the value is
            // long lived. Which is the better tradeoff? This is closer to what's
            // in stringToValue.
            BigInteger bi = new BigInteger(val);
            if (bi.bitLength() <= 31) {
                return Integer.valueOf(bi.intValue());
            }
            if (bi.bitLength() <= 63) {
                return Long.valueOf(bi.longValue());
            }
            return bi;
        }
        throw new NumberFormatException("val [" + val + "] is not a valid number.");
    }

    /**
     * Try to convert a string into a number, boolean, or null. If the string can't be converted,
     * return the string.
     *
     * @param string
     *            A String. can not be null.
     * @return A simple JSON value.
     * @throws NullPointerException
     *             Thrown if the string is null.
     */
    // Changes to this method must be copied to the corresponding method in
    // the XML class to keep full support for Android
    public static Object stringToValue(String string) {
        if ("".equals(string)) {
            return string;
        }

        // check JSON key words true/false/null
        if ("true".equalsIgnoreCase(string)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(string)) {
            return Boolean.FALSE;
        }
        if ("null".equalsIgnoreCase(string)) {
            return _JSONObject.NULL;
        }

        /*
         * If it might be a number, try converting it. If a number cannot be produced, then the
         * value will just be a string.
         */

        char initial = string.charAt(0);
        if ((initial >= '0' && initial <= '9') || initial == '-') {
            try {
                // if we want full Big Number support the contents of this
                // `try` block can be replaced with:
                // return stringToNumber(string);
                if (isDecimalNotation(string)) {
                    Double d = Double.valueOf(string);
                    if (!d.isInfinite() && !d.isNaN()) {
                        return d;
                    }
                } else {
                    Long myLong = Long.valueOf(string);
                    if (string.equals(myLong.toString())) {
                        if (myLong.longValue() == myLong.intValue()) {
                            return Integer.valueOf(myLong.intValue());
                        }
                        return myLong;
                    }
                }
            } catch (Exception ignore) {
            }
        }
        return string;
    }

    /**
     * Throw an exception if the object is a NaN or infinite number.
     *
     * @param o
     *            The object to test.
     * @throws JSONException
     *             If o is a non-finite number.
     */
    public static void testValidity(Object o)
            throws JSONException {
        if (o != null) {
            if (o instanceof Double) {
                if (((Double) o).isInfinite() || ((Double) o).isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            } else if (o instanceof Float) {
                if (((Float) o).isInfinite() || ((Float) o).isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    /**
     * Produce a _JSONArray containing the values of the members of this _JSONObject.
     *
     * @param names
     *            A _JSONArray containing a list of key strings. This determines the sequence of the
     *            values in the result.
     * @return A _JSONArray of values.
     * @throws JSONException
     *             If any of the values are non-finite numbers.
     */
    public _JSONArray toJSONArray(_JSONArray names)
            throws JSONException {
        if (names == null || names.isEmpty()) {
            return null;
        }
        _JSONArray ja = new _JSONArray();
        for (int i = 0; i < names.length(); i += 1) {
            ja.put(this.get(names.getString(i)));
        }
        return ja;
    }

    /**
     * Make a JSON text of this _JSONObject. For compactness, no whitespace is added. If this would
     * not result in a syntactically correct JSON text, then null will be returned instead.
     * <p>
     * <b> Warning: This method assumes that the data structure is acyclical. </b>
     *
     * @return a printable, displayable, portable, transmittable representation of the object,
     *         beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending with
     *         <code>}</code>&nbsp;<small>(right brace)</small>.
     */
    @Override
    public String toString() {
        try {
            return this.toString(0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Make a pretty-printed JSON text of this _JSONObject.
     *
     * <p>
     * If <code>indentFactor > 0</code> and the {@link _JSONObject} has only one key, then the
     * object will be output on a single line:
     *
     * <pre>
     * {@code {"key": 1}}
     * </pre>
     *
     * <p>
     * If an object has 2 or more keys, then it will be output across multiple lines: <code><pre>{
     *  "key1": 1,
     *  "key2": "value 2",
     *  "key3": 3
     * }</pre></code>
     * <p>
     * <b> Warning: This method assumes that the data structure is acyclical. </b>
     *
     * @param indentFactor
     *            The number of spaces to add to each level of indentation.
     * @return a printable, displayable, portable, transmittable representation of the object,
     *         beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending with
     *         <code>}</code>&nbsp;<small>(right brace)</small>.
     * @throws JSONException
     *             If the object contains an invalid number.
     */
    public String toString(int indentFactor)
            throws JSONException {
        StringWriter w = new StringWriter();
        synchronized (w.getBuffer()) {
            return this.write(w, indentFactor, 0).toString();
        }
    }

    /**
     * Make a JSON text of an Object value. If the object has an value.toJSONString() method, then
     * that method will be used to produce the JSON text. The method is required to produce a
     * strictly conforming text. If the object does not contain a toJSONString method (which is the
     * most common case), then a text will be produced by other means. If the value is an array or
     * Collection, then a _JSONArray will be made from it and its toJSONString method will be
     * called. If the value is a MAP, then a _JSONObject will be made from it and its toJSONString
     * method will be called. Otherwise, the value's toString method will be called, and the result
     * will be quoted.
     *
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @param value
     *            The value to be serialized.
     * @return a printable, displayable, transmittable representation of the object, beginning with
     *         <code>{</code>&nbsp;<small>(left brace)</small> and ending with
     *         <code>}</code>&nbsp;<small>(right brace)</small>.
     * @throws JSONException
     *             If the value is or contains an invalid number.
     */
    public static String valueToString(Object value)
            throws JSONException {
        // moves the implementation to JSONWriter as:
        // 1. It makes more sense to be part of the writer class
        // 2. For Android support this method is not available. By implementing it in the Writer
        // Android users can use the writer with the built in Android _JSONObject implementation.
        return JSONWriter.valueToString(value);
    }

    /**
     * Wrap an object, if necessary. If the object is <code>null</code>, return the NULL object. If
     * it is an array or collection, wrap it in a _JSONArray. If it is a map, wrap it in a
     * _JSONObject. If it is a standard property (Double, String, et al) then it is already wrapped.
     * Otherwise, if it comes from one of the java packages, turn it into a string. And if it
     * doesn't, try to wrap it in a _JSONObject. If the wrapping fails, then null is returned.
     *
     * @param object
     *            The object to wrap
     * @return The wrapped value
     */
    public static Object wrap(Object object) {
        try {
            if (object == null) {
                return NULL;
            }
            if (object instanceof _JSONObject || object instanceof _JSONArray || NULL.equals(object)
                    || object instanceof JSONString || object instanceof Byte || object instanceof Character
                    || object instanceof Short || object instanceof Integer || object instanceof Long
                    || object instanceof Boolean || object instanceof Float || object instanceof Double
                    || object instanceof String || object instanceof BigInteger || object instanceof BigDecimal
                    || object instanceof Enum) {
                return object;
            }

            if (object instanceof Collection) {
                Collection<?> coll = (Collection<?>) object;
                return JsonArrayBuilder.getInstance().convert(coll);
            }
            if (object.getClass().isArray()) {
                return JsonArrayBuilder.getInstance().convert(object);
            }
            if (object instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) object;
                return JsonObjectBuilder.getInstance().fromMap(map);
            }
            Package objectPackage = object.getClass().getPackage();
            String objectPackageName = objectPackage != null ? objectPackage.getName() : "";
            if (objectPackageName.startsWith("java.") || objectPackageName.startsWith("javax.")
                    || object.getClass().getClassLoader() == null) {
                return object.toString();
            }
            return JsonObjectBuilder.getInstance().fromBean(object);
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Write the contents of the _JSONObject as JSON text to a writer. For compactness, no
     * whitespace is added.
     * <p>
     * <b> Warning: This method assumes that the data structure is acyclical. </b>
     *
     * @return The writer.
     * @throws JSONException
     */
    public Writer write(Writer writer)
            throws JSONException {
        return this.write(writer, 0, 0);
    }

    static final Writer writeValue(Writer writer, Object value, int indentFactor, int indent)
            throws JSONException, IOException {
        if (value == null || value.equals(null)) {
            writer.write("null");
        } else if (value instanceof JSONString) {
            Object o;
            try {
                o = ((JSONString) value).toJSONString();
            } catch (Exception e) {
                throw new JSONException(e);
            }
            writer.write(o != null ? o.toString() : quote(value.toString()));
        } else if (value instanceof Number) {
            // not all Numbers may match actual JSON Numbers. i.e. fractions or Imaginary
            final String numberAsString = numberToString((Number) value);
            if (NUMBER_PATTERN.matcher(numberAsString).matches()) {
                writer.write(numberAsString);
            } else {
                // The Number value is not a valid JSON number.
                // Instead we will quote it as a string
                quote(numberAsString, writer);
            }
        } else if (value instanceof Boolean) {
            writer.write(value.toString());
        } else if (value instanceof Enum<?>) {
            writer.write(quote(((Enum<?>) value).name()));
        } else if (value instanceof _JSONObject) {
            ((_JSONObject) value).write(writer, indentFactor, indent);
        } else if (value instanceof _JSONArray) {
            ((_JSONArray) value).write(writer, indentFactor, indent);
        } else if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            JsonObject jo = JsonObjectBuilder.getInstance().fromMap(map);
            jo.write(writer, indentFactor, indent);
        } else if (value instanceof Collection) {
            Collection<?> coll = (Collection<?>) value;
            JsonArray ja = JsonArrayBuilder.getInstance().convert(coll);
            ja.write(writer, indentFactor, indent);
        } else if (value.getClass().isArray()) {
            JsonArray ja = JsonArrayBuilder.getInstance().convert(value);
            ja.write(writer, indentFactor, indent);
        } else {
            quote(value.toString(), writer);
        }
        return writer;
    }

    static final void indent(Writer writer, int indent)
            throws IOException {
        for (int i = 0; i < indent; i += 1) {
            writer.write(' ');
        }
    }

    /**
     * Write the contents of the _JSONObject as JSON text to a writer.
     *
     * <p>
     * If <code>indentFactor > 0</code> and the {@link _JSONObject} has only one key, then the
     * object will be output on a single line:
     *
     * <pre>
     * {@code {"key": 1}}
     * </pre>
     *
     * <p>
     * If an object has 2 or more keys, then it will be output across multiple lines: <code><pre>{
     *  "key1": 1,
     *  "key2": "value 2",
     *  "key3": 3
     * }</pre></code>
     * <p>
     * <b> Warning: This method assumes that the data structure is acyclical. </b>
     *
     * @param writer
     *            Writes the serialized JSON
     * @param indentFactor
     *            The number of spaces to add to each level of indentation.
     * @param indent
     *            The indentation of the top level.
     * @return The writer.
     * @throws JSONException
     */
    public Writer write(Writer writer, int indentFactor, int indent)
            throws JSONException {
        try {
            boolean needsComma = false;
            final int length = this.length();
            writer.write('{');

            if (length == 1) {
                final Entry<String, ?> entry = this.entrySet().iterator().next();
                final String key = entry.getKey();
                writer.write(quote(key));
                writer.write(':');
                if (indentFactor > 0) {
                    writer.write(' ');
                }
                try {
                    writeValue(writer, entry.getValue(), indentFactor, indent);
                } catch (Exception e) {
                    throw new JSONException("Unable to write _JSONObject value for key: " + key, e);
                }
            } else if (length != 0) {
                final int newIndent = indent + indentFactor;
                for (final Entry<String, ?> entry : this.entrySet()) {
                    if (needsComma) {
                        writer.write(',');
                    }
                    if (indentFactor > 0) {
                        writer.write('\n');
                    }
                    indent(writer, newIndent);
                    final String key = entry.getKey();
                    writer.write(quote(key));
                    writer.write(':');
                    if (indentFactor > 0) {
                        writer.write(' ');
                    }
                    try {
                        writeValue(writer, entry.getValue(), indentFactor, newIndent);
                    } catch (Exception e) {
                        throw new JSONException("Unable to write _JSONObject value for key: " + key, e);
                    }
                    needsComma = true;
                }
                if (indentFactor > 0) {
                    writer.write('\n');
                }
                indent(writer, indent);
            }
            writer.write('}');
            return writer;
        } catch (IOException exception) {
            throw new JSONException(exception);
        }
    }

    /**
     * Returns a java.util.Map containing all of the entries in this object. If an entry in the
     * object is a _JSONArray or _JSONObject it will also be converted.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @return a java.util.Map containing the entries of this object
     */
    public Map<String, Object> toMap() {
        Map<String, Object> results = new HashMap<String, Object>();
        for (Entry<String, Object> entry : this.entrySet()) {
            Object value;
            if (entry.getValue() == null || NULL.equals(entry.getValue())) {
                value = null;
            } else if (entry.getValue() instanceof _JSONObject) {
                value = ((_JSONObject) entry.getValue()).toMap();
            } else if (entry.getValue() instanceof _JSONArray) {
                value = ((_JSONArray) entry.getValue()).toList();
            } else {
                value = entry.getValue();
            }
            results.put(entry.getKey(), value);
        }
        return results;
    }

    /**
     * Create a new JSONException in a common format for incorrect conversions.
     *
     * @param key
     *            name of the key
     * @param valueType
     *            the type of value being coerced to
     * @param cause
     *            optional cause of the coercion failure
     * @return JSONException that can be thrown.
     */
    private static JSONException wrongValueFormatException(String key, String valueType, Throwable cause) {
        return new JSONException("_JSONObject[" + quote(key) + "] is not a " + valueType + ".", cause);
    }

    /**
     * Create a new JSONException in a common format for incorrect conversions.
     *
     * @param key
     *            name of the key
     * @param valueType
     *            the type of value being coerced to
     * @param cause
     *            optional cause of the coercion failure
     * @return JSONException that can be thrown.
     */
    private static JSONException wrongValueFormatException(String key, String valueType, Object value,
            Throwable cause) {
        return new JSONException("_JSONObject[" + quote(key) + "] is not a " + valueType + " (" + value + ").", cause);
    }
}
