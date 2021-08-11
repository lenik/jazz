package net.bodz.fork.org.json;

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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonArrayBuilder;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;
import net.bodz.bas.t.variant.AbstractVariantList;

/**
 * A JSONArray is an ordered sequence of values. Its external text form is a string wrapped in
 * square brackets with commas separating the values. The internal form is an object having
 * <code>get</code> and <code>opt</code> methods for accessing the values by index, and
 * <code>put</code> methods for adding or replacing values. The values can be any of these types:
 * <code>Boolean</code>, <code>JSONArray</code>, <code>JSONObject</code>, <code>Number</code>,
 * <code>String</code>, or the <code>JSONObject.NULL object</code>.
 * <p>
 * The constructor can convert a JSON text into a Java object. The <code>toString</code> method
 * converts to JSON text.
 * <p>
 * A <code>get</code> method returns a value if one can be found, and throws an exception if one
 * cannot be found. An <code>opt</code> method returns a default value instead of throwing an
 * exception, and so is useful for obtaining optional values.
 * <p>
 * The generic <code>get()</code> and <code>opt()</code> methods return an object which you can cast
 * or query for type. There are also typed <code>get</code> and <code>opt</code> methods that do
 * type checking and type coercion for you.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to JSON syntax rules.
 * The constructors are more forgiving in the texts they will accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just before the closing
 * bracket.</li>
 * <li>The <code>null</code> value will be inserted when there is <code>,</code>
 * &nbsp;<small>(comma)</small> elision.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a quote or single quote,
 * and if they do not contain leading or trailing spaces, and if they do not contain any of these
 * characters: <code>{ } [ ] / \ : , #</code> and if they do not look like numbers and if they are
 * not the reserved words <code>true</code>, <code>false</code>, or <code>null</code>.</li>
 * </ul>
 *
 * @author JSON.org
 * @version 2016-08/15
 */
public class _JSONArray
        extends AbstractVariantList
        implements
            Iterable<Object> {

    /**
     * The arrayList where the JSONArray's properties are kept.
     */
    private final ArrayList<Object> myArrayList;

    /**
     * Construct an empty JSONArray.
     */
    public _JSONArray() {
        this.myArrayList = new ArrayList<Object>();
    }

    public _JSONArray(ArrayList<Object> list) {
        this.myArrayList = list;
    }

    @Override
    public int size() {
        return myArrayList.size();
    }

    /**
     * Get the object value associated with an index.
     *
     * @param index
     *            The index must be between 0 and length() - 1.
     * @return An object value.
     * @throws JSONException
     *             If there is no value for the index.
     */
    @Override
    public Object get(int index)
            throws JSONException {
        Object object = myArrayList.get(index);
        if (object == null) {
            throw new JSONException("JSONArray[" + index + "] not found.");
        }
        return object;
    }

    public JsonObject getJsonObject(int index) {
        return getJsonObject(index, null);
    }

    public JsonObject getJsonObject(int index, JsonObject defaultValue) {
        Object value = get(index);
        if (value == null)
            return defaultValue;
        if (value instanceof JsonObject)
            return (JsonObject) value;
        throw new IllegalArgumentException("Child isn'ot a json object: " + index);
    }

    public JsonArray getJsonArray(int index) {
        return getJsonArray(index, null);
    }

    public JsonArray getJsonArray(int index, JsonArray defaultValue) {
        Object value = get(index);
        if (value == null)
            return defaultValue;
        if (value instanceof JsonArray)
            return (JsonArray) value;
        throw new IllegalArgumentException("Child isn'ot a json array: " + index);
    }

    @Override
    public Iterator<Object> iterator() {
        return this.myArrayList.iterator();
    }

    /**
     * Determine if the value is <code>null</code>.
     *
     * @param index
     *            The index must be between 0 and length() - 1.
     * @return true if the value at the index is <code>null</code>, or if there is no value.
     */
    public boolean isNull(int index) {
        return _JSONObject.NULL.equals(this.get(index));
    }

    /**
     * Make a string from the contents of this JSONArray. The <code>separator</code> string is
     * inserted between each element. Warning: This method assumes that the data structure is
     * acyclical.
     *
     * @param separator
     *            A string that will be inserted between the elements.
     * @return a string.
     * @throws JSONException
     *             If the array contains an invalid number.
     */
    public String join(String separator)
            throws JSONException {
        int len = this.length();
        if (len == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder(_JSONObject.valueToString(this.myArrayList.get(0)));

        for (int i = 1; i < len; i++) {
            sb.append(separator).append(_JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return sb.toString();
    }

    /**
     * Get the number of elements in the JSONArray, included nulls.
     *
     * @return The length (or size).
     */
    public int length() {
        return this.myArrayList.size();
    }

    /**
     * Append a boolean value. This increases the array's length by one.
     *
     * @param value
     *            A boolean value.
     * @return this.
     */
    public _JSONArray put(boolean value) {
        return this.put(value ? Boolean.TRUE : Boolean.FALSE);
    }

    /**
     * Put a value in the JSONArray, where the value will be a JSONArray which is produced from a
     * Collection.
     *
     * @param value
     *            A Collection value.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     */
    public _JSONArray put(Collection<?> value) {
        return this.put(JsonArrayBuilder.getInstance().convert(value));
    }

    /**
     * Append a double value. This increases the array's length by one.
     *
     * @param value
     *            A double value.
     * @return this.
     * @throws JSONException
     *             if the value is not finite.
     */
    public _JSONArray put(double value)
            throws JSONException {
        return this.put(Double.valueOf(value));
    }

    /**
     * Append a float value. This increases the array's length by one.
     *
     * @param value
     *            A float value.
     * @return this.
     * @throws JSONException
     *             if the value is not finite.
     */
    public _JSONArray put(float value)
            throws JSONException {
        return this.put(Float.valueOf(value));
    }

    /**
     * Append an int value. This increases the array's length by one.
     *
     * @param value
     *            An int value.
     * @return this.
     */
    public _JSONArray put(int value) {
        return this.put(Integer.valueOf(value));
    }

    /**
     * Append an long value. This increases the array's length by one.
     *
     * @param value
     *            A long value.
     * @return this.
     */
    public _JSONArray put(long value) {
        return this.put(Long.valueOf(value));
    }

    /**
     * Put a value in the JSONArray, where the value will be a JSONObject which is produced from a
     * Map.
     *
     * @param value
     *            A Map value.
     * @return this.
     * @throws JSONException
     *             If a value in the map is non-finite number.
     * @throws NullPointerException
     *             If a key in the map is <code>null</code>
     */
    public _JSONArray put(Map<?, ?> value) {
        return this.put(JsonObjectBuilder.getInstance().fromMap(value));
    }

    /**
     * Append an object value. This increases the array's length by one.
     *
     * @param value
     *            An object value. The value should be a Boolean, Double, Integer, JSONArray,
     *            JSONObject, Long, or String, or the JSONObject.NULL object.
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number.
     */
    public _JSONArray put(Object value) {
        _JSONObject.testValidity(value);
        this.myArrayList.add(value);
        return this;
    }

    /**
     * Put or replace a boolean value in the JSONArray. If the index is greater than the length of
     * the JSONArray, then null elements will be added as necessary to pad it out.
     *
     * @param index
     *            The subscript.
     * @param value
     *            A boolean value.
     * @return this.
     * @throws JSONException
     *             If the index is negative.
     */
    public _JSONArray put(int index, boolean value)
            throws JSONException {
        return this.put(index, value ? Boolean.TRUE : Boolean.FALSE);
    }

    /**
     * Put a value in the JSONArray, where the value will be a JSONArray which is produced from a
     * Collection.
     *
     * @param index
     *            The subscript.
     * @param value
     *            A Collection value.
     * @return this.
     * @throws JSONException
     *             If the index is negative or if the value is non-finite.
     */
    public _JSONArray put(int index, Collection<?> value)
            throws JSONException {
        return this.put(index, JsonArrayBuilder.getInstance().convert(value));
    }

    /**
     * Put or replace a double value. If the index is greater than the length of the JSONArray, then
     * null elements will be added as necessary to pad it out.
     *
     * @param index
     *            The subscript.
     * @param value
     *            A double value.
     * @return this.
     * @throws JSONException
     *             If the index is negative or if the value is non-finite.
     */
    public _JSONArray put(int index, double value)
            throws JSONException {
        return this.put(index, Double.valueOf(value));
    }

    /**
     * Put or replace a float value. If the index is greater than the length of the JSONArray, then
     * null elements will be added as necessary to pad it out.
     *
     * @param index
     *            The subscript.
     * @param value
     *            A float value.
     * @return this.
     * @throws JSONException
     *             If the index is negative or if the value is non-finite.
     */
    public _JSONArray put(int index, float value)
            throws JSONException {
        return this.put(index, Float.valueOf(value));
    }

    /**
     * Put or replace an int value. If the index is greater than the length of the JSONArray, then
     * null elements will be added as necessary to pad it out.
     *
     * @param index
     *            The subscript.
     * @param value
     *            An int value.
     * @return this.
     * @throws JSONException
     *             If the index is negative.
     */
    public _JSONArray put(int index, int value)
            throws JSONException {
        return this.put(index, Integer.valueOf(value));
    }

    /**
     * Put or replace a long value. If the index is greater than the length of the JSONArray, then
     * null elements will be added as necessary to pad it out.
     *
     * @param index
     *            The subscript.
     * @param value
     *            A long value.
     * @return this.
     * @throws JSONException
     *             If the index is negative.
     */
    public _JSONArray put(int index, long value)
            throws JSONException {
        return this.put(index, Long.valueOf(value));
    }

    /**
     * Put a value in the JSONArray, where the value will be a JSONObject that is produced from a
     * Map.
     *
     * @param index
     *            The subscript.
     * @param value
     *            The Map value.
     * @return this.
     * @throws JSONException
     *             If the index is negative or if the the value is an invalid number.
     * @throws NullPointerException
     *             If a key in the map is <code>null</code>
     */
    public _JSONArray put(int index, Map<?, ?> value)
            throws JSONException {
        this.put(index, JsonObjectBuilder.getInstance().fromMap(value));
        return this;
    }

    /**
     * Put or replace an object value in the JSONArray. If the index is greater than the length of
     * the JSONArray, then null elements will be added as necessary to pad it out.
     *
     * @param index
     *            The subscript.
     * @param value
     *            The value to put into the array. The value should be a Boolean, Double, Integer,
     *            JSONArray, JSONObject, Long, or String, or the JSONObject.NULL object.
     * @return this.
     * @throws JSONException
     *             If the index is negative or if the the value is an invalid number.
     */
    public _JSONArray put(int index, Object value)
            throws JSONException {
        if (index < 0) {
            throw new JSONException("JSONArray[" + index + "] not found.");
        }
        if (index < this.length()) {
            _JSONObject.testValidity(value);
            this.myArrayList.set(index, value);
            return this;
        }
        if (index == this.length()) {
            // simple append
            return this.put(value);
        }
        // if we are inserting past the length, we want to grow the array all at once
        // instead of incrementally.

        this.myArrayList.ensureCapacity(index + 1);

        while (index != this.length()) {
            // we don't need to test validity of NULL objects
            this.myArrayList.add(_JSONObject.NULL);
        }
        return this.put(value);
    }

    /**
     * Creates a JSONPointer using an initialization string and tries to match it to an item within
     * this JSONArray. For example, given a JSONArray initialized with this document:
     *
     * <pre>
     * [
     *     {"b":"c"}
     * ]
     * </pre>
     *
     * and this JSONPointer string:
     *
     * <pre>
     * "/0/b"
     * </pre>
     *
     * Then this method will return the String "c" A JSONPointerException may be thrown from code
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
     * Uses a user initialized JSONPointer and tries to match it to an item within this JSONArray.
     * For example, given a JSONArray initialized with this document:
     *
     * <pre>
     * [
     *     {"b":"c"}
     * ]
     * </pre>
     *
     * and this JSONPointer:
     *
     * <pre>
     * "/0/b"
     * </pre>
     *
     * Then this method will return the String "c" A JSONPointerException may be thrown from code
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
     * Remove an index and close the hole.
     *
     * @param index
     *            The index of the element to be removed.
     * @return The value that was associated with the index, or null if there was no value.
     */
    public Object remove(int index) {
        return index >= 0 && index < this.length() ? this.myArrayList.remove(index) : null;
    }

    /**
     * Determine if two JSONArrays are similar. They must contain similar sequences.
     *
     * @param other
     *            The other JSONArray
     * @return true if they are equal
     */
    public boolean similar(Object other) {
        if (!(other instanceof _JSONArray)) {
            return false;
        }
        int len = this.length();
        if (len != ((_JSONArray) other).length()) {
            return false;
        }
        for (int i = 0; i < len; i += 1) {
            Object valueThis = this.myArrayList.get(i);
            Object valueOther = ((_JSONArray) other).myArrayList.get(i);
            if (valueThis == valueOther) {
                continue;
            }
            if (valueThis == null) {
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
    }

    /**
     * Produce a JSONObject by combining a JSONArray of names with the values of this JSONArray.
     *
     * @param names
     *            A JSONArray containing a list of key strings. These will be paired with the
     *            values.
     * @return A JSONObject, or null if there are no names or if this JSONArray has no values.
     * @throws JSONException
     *             If any of the names are null.
     */
    public JsonObject toJsonObject(JsonArray names)
            throws JSONException {
        if (names == null || names.isEmpty() || this.isEmpty()) {
            return null;
        }
        JsonObject jo = JsonObjectBuilder.getInstance().create(); // (names.length());
        for (int i = 0; i < names.length(); i += 1) {
            jo.put(names.getString(i), this.get(i));
        }
        return jo;
    }

    /**
     * Make a JSON text of this JSONArray. For compactness, no unnecessary whitespace is added. If
     * it is not possible to produce a syntactically correct JSON text then null will be returned
     * instead. This could occur if the array contains an invalid number.
     * <p>
     * <b> Warning: This method assumes that the data structure is acyclical. </b>
     *
     * @return a printable, displayable, transmittable representation of the array.
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
     * Make a pretty-printed JSON text of this JSONArray.
     *
     * <p>
     * If <code>indentFactor > 0</code> and the {@link _JSONArray} has only one element, then the
     * array will be output on a single line:
     *
     * <pre>
     * {@code [1]}
     * </pre>
     *
     * <p>
     * If an array has 2 or more elements, then it will be output across multiple lines:
     *
     * <pre>
     * {@code
     * [
     * 1,
     * "value 2",
     * 3
     * ]
     * }
     * </pre>
     * <p>
     * <b> Warning: This method assumes that the data structure is acyclical. </b>
     *
     * @param indentFactor
     *            The number of spaces to add to each level of indentation.
     * @return a printable, displayable, transmittable representation of the object, beginning with
     *         <code>[</code>&nbsp;<small>(left bracket)</small> and ending with <code>]</code>
     *         &nbsp;<small>(right bracket)</small>.
     * @throws JSONException
     */
    public String toString(int indentFactor)
            throws JSONException {
        StringWriter sw = new StringWriter();
        synchronized (sw.getBuffer()) {
            return this.write(sw, indentFactor, 0).toString();
        }
    }

    /**
     * Write the contents of the JSONArray as JSON text to a writer. For compactness, no whitespace
     * is added.
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

    /**
     * Write the contents of the JSONArray as JSON text to a writer.
     *
     * <p>
     * If <code>indentFactor > 0</code> and the {@link _JSONArray} has only one element, then the
     * array will be output on a single line:
     *
     * <pre>
     * {@code [1]}
     * </pre>
     *
     * <p>
     * If an array has 2 or more elements, then it will be output across multiple lines:
     *
     * <pre>
     * {@code
     * [
     * 1,
     * "value 2",
     * 3
     * ]
     * }
     * </pre>
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
            int length = this.length();
            writer.write('[');

            if (length == 1) {
                try {
                    _JSONObject.writeValue(writer, this.myArrayList.get(0), indentFactor, indent);
                } catch (Exception e) {
                    throw new JSONException("Unable to write JSONArray value at index: 0", e);
                }
            } else if (length != 0) {
                final int newIndent = indent + indentFactor;

                for (int i = 0; i < length; i += 1) {
                    if (needsComma) {
                        writer.write(',');
                    }
                    if (indentFactor > 0) {
                        writer.write('\n');
                    }
                    _JSONObject.indent(writer, newIndent);
                    try {
                        _JSONObject.writeValue(writer, this.myArrayList.get(i), indentFactor, newIndent);
                    } catch (Exception e) {
                        throw new JSONException("Unable to write JSONArray value at index: " + i, e);
                    }
                    needsComma = true;
                }
                if (indentFactor > 0) {
                    writer.write('\n');
                }
                _JSONObject.indent(writer, indent);
            }
            writer.write(']');
            return writer;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    /**
     * Returns a java.util.List containing all of the elements in this array. If an element in the
     * array is a JSONArray or JSONObject it will also be converted to a List and a Map
     * respectively.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @return a java.util.List containing the elements of this array
     */
    public List<Object> toList() {
        List<Object> results = new ArrayList<Object>(this.myArrayList.size());
        for (Object element : this.myArrayList) {
            if (element == null || _JSONObject.NULL.equals(element)) {
                results.add(null);
            } else if (element instanceof _JSONArray) {
                results.add(((_JSONArray) element).toList());
            } else if (element instanceof _JSONObject) {
                results.add(((_JSONObject) element).toMap());
            } else {
                results.add(element);
            }
        }
        return results;
    }

    /**
     * Check if JSONArray is empty.
     *
     * @return true if JSONArray is empty, otherwise false.
     */
    public boolean isEmpty() {
        return this.myArrayList.isEmpty();
    }

    /**
     * Create a new JSONException in a common format for incorrect conversions.
     *
     * @param idx
     *            index of the item
     * @param valueType
     *            the type of value being coerced to
     * @param cause
     *            optional cause of the coercion failure
     * @return JSONException that can be thrown.
     */
    private static JSONException wrongValueFormatException(int idx, String valueType, Throwable cause) {
        return new JSONException("JSONArray[" + idx + "] is not a " + valueType + ".", cause);
    }

    /**
     * Create a new JSONException in a common format for incorrect conversions.
     *
     * @param idx
     *            index of the item
     * @param valueType
     *            the type of value being coerced to
     * @param cause
     *            optional cause of the coercion failure
     * @return JSONException that can be thrown.
     */
    private static JSONException wrongValueFormatException(int idx, String valueType, Object value, Throwable cause) {
        return new JSONException("JSONArray[" + idx + "] is not a " + valueType + " (" + value + ").", cause);
    }

}
