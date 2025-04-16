package net.bodz.bas.fmt.json;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.fork.org.json.JSONException;

public interface IJsonOut
        extends IJsonFormOptionsAwareStacked {

    // Iterable<Object> path();

    IJsonOut verbatim(String code)
            throws JSONException;

    /**
     * Begin appending a new array. All values until the balancing <code>endArray</code> will be appended to this array.
     * The <code>endArray</code> method must be called to mark the array's end.
     *
     * @return this
     * @throws JSONException
     *             If the nesting is too deep, or if the object is started in the wrong place (for example as a key or
     *             after the end of the outermost array or object).
     */
    IJsonOut array()
            throws JSONException;

    /**
     * End an array. This method most be called to balance calls to <code>array</code>.
     *
     * @return this
     * @throws JSONException
     *             If incorrectly nested.
     */
    IJsonOut endArray()
            throws JSONException;

    /**
     * End an object. This method most be called to balance calls to <code>object</code>.
     *
     * @return this
     * @throws JSONException
     *             If incorrectly nested.
     */
    IJsonOut endObject()
            throws JSONException;

    /**
     * Append a key. The key will be associated with the next value. In an object, every value must be preceded by a
     * key.
     *
     * @param string
     *            A key string.
     * @return this
     * @throws JSONException
     *             If the key is out of place. For example, keys do not belong in arrays or if the key is null.
     */
    IJsonOut key(String string)
            throws JSONException;

    /**
     * Begin appending a new object. All keys and values until the balancing <code>endObject</code> will be appended to
     * this object. The <code>endObject</code> method must be called to mark the object's end.
     *
     * @return this
     * @throws JSONException
     *             If the nesting is too deep, or if the object is started in the wrong place (for example as a key or
     *             after the end of the outermost array or object).
     */
    IJsonOut object()
            throws JSONException;

    /**
     * Append either the value <code>true</code> or the value <code>false</code>.
     *
     * @param b
     *            A boolean.
     * @return this
     * @throws JSONException
     */
    IJsonOut value(boolean b)
            throws JSONException;

    /**
     * Append a double value.
     *
     * @param d
     *            A double.
     * @return this
     * @throws JSONException
     *             If the number is not finite.
     */
    IJsonOut value(double d)
            throws JSONException;

    /**
     * Append a long value.
     *
     * @param l
     *            A long.
     * @return this
     * @throws JSONException
     */
    IJsonOut value(long l)
            throws JSONException;

    /**
     * Append an object value.
     *
     * @param object
     *            The object to append. It can be null, or a Boolean, Number, String, JSONObject, or JSONArray, or an
     *            object that implements JSONString.
     * @return this
     * @throws JSONException
     *             If the value is out of sequence.
     */
    IJsonOut value(Object object)
            throws JSONException;

    IJsonOut entry(String key, boolean value)
            throws JSONException;

    IJsonOut entry(String key, double value)
            throws JSONException;

    IJsonOut entry(String key, long value)
            throws JSONException;

    IJsonOut entry(String key, Object value)
            throws JSONException;

    default IJsonOut entryTrue(String key, boolean value)
            throws JSONException {
        if (value)
            entry(key, true);
        return this;
    }

    default IJsonOut entryNotNull(String key, Object value)
            throws JSONException {
        if (value != null)
            entry(key, value);
        return this;
    }

    default IJsonOut entryNot0(String key, int value)
            throws JSONException {
        if (value != 0)
            entry(key, value);
        return this;
    }

    default IJsonOut entryNot0(String key, long value)
            throws JSONException {
        if (value != 0L)
            entry(key, value);
        return this;
    }

    default IJsonOut entryNot0(String key, float value)
            throws JSONException {
        if (value != 0.0f)
            entry(key, value);
        return this;
    }

    default IJsonOut entryNot0(String key, double value)
            throws JSONException {
        if (value != 0.0)
            entry(key, value);
        return this;
    }

    default IJsonOut object(Object object)
            throws JSONException {
        try {
            JsonFn.writeObject(this, object, getJsonFormOptions());
        } catch (Exception e) {
            throw new JSONException(e);
        }
        return this;
    }

    default IJsonOut array(String key, Object array) {
        key(key);
        return array(array);
    }

    default IJsonOut array(Object array)
            throws JSONException {
        this.array();
        int n = Array.getLength(array);
        for (int i = 0; i < n; i++) {
            Object item = Array.get(array, i);
            try {
                JsonFn.writeObject(this, item, getJsonFormOptions());
            } catch (Exception e) {
                throw new JSONException(String.format(//
                        "error write array[%d]: %s", i, item), e);
            }
        }
        this.endArray();
        return this;
    }

    default IJsonOut array(String key, Collection<?> collection) {
        this.key(key);
        return array(collection);
    }

    default IJsonOut array(Collection<?> collection)
            throws JSONException {
        this.array();
        int i = 0;
        for (Object item : collection) {
            try {
                JsonFn.writeObject(this, item, getJsonFormOptions());
            } catch (Exception e) {
                throw new JSONException(String.format(//
                        "error write collection[%d]: %s", i, item), e);
            }
            i++;
        }
        this.endArray();
        return this;
    }

    default IJsonOut array(JsonArray array) {
        this.array();
        for (Object item : array) {
            JsonVariant jv = JsonVariant.of(item);
            variant(jv);
        }
        this.endArray();
        return this;
    }

    default IJsonOut map(String key, Map<String, ?> map) {
        this.key(key);
        return map(map);
    }

    default IJsonOut map(Map<String, ?> map)
            throws JSONException {
        this.object();
        for (String key : map.keySet()) {
            this.key(key);
            Object item = map.get(key);
            try {
                JsonFn.writeObject(this, item, getJsonFormOptions());
            } catch (Exception e) {
                throw new JSONException(String.format(//
                        "error write map[%s]: %s", key, item), e);
            }
        }
        this.endObject();
        return this;
    }

    default IJsonOut object(JsonObject jo) {
        this.object();
        for (String key : jo.keySet()) {
            key(key);
            Object value = jo.get(key);
            JsonVariant jvValue = JsonVariant.of(value);
            variant(jvValue);
        }
        this.endObject();
        return this;
    }

    default IJsonOut variant(JsonVariant jv) {
        switch (jv.type) {
            case NULL:
                value(null);
                break;
            case ARRAY:
                assert jv.getArray() != null;
                array(jv.getArray());
                break;
            case OBJECT:
                assert jv.getObject() != null;
                object(jv.getObject());
                break;
            case SCALAR:
                value(jv.getScalar());
                break;
        }
        return this;
    }

}
