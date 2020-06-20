package net.bodz.bas.fmt.json;

import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.bodz.bas.t.model.IWrapper;

@SuppressWarnings("unchecked")
public abstract class JSONObjectWrapper<self_t>
        implements IWrapper<JSONObject> {

    JSONObject wrapped;

    public JSONObjectWrapper(JSONObject wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public JSONObject getWrapped() {
        return wrapped;
    }

    public self_t accumulate(String key, Object value)
            throws JSONException {
        wrapped.accumulate(key, value);
        return (self_t) this;
    }

    public self_t append(String key, Object value)
            throws JSONException {
        wrapped.append(key, value);
        return (self_t) this;
    }

    public Object get(String key)
            throws JSONException {
        return wrapped.get(key);
    }

    public boolean getBoolean(String key)
            throws JSONException {
        return wrapped.getBoolean(key);
    }

    public double getDouble(String key)
            throws JSONException {
        return wrapped.getDouble(key);
    }

    public int getInt(String key)
            throws JSONException {
        return wrapped.getInt(key);
    }

    public JSONArray getJSONArray(String key)
            throws JSONException {
        return wrapped.getJSONArray(key);
    }

    public JSONObject getJSONObject(String key)
            throws JSONException {
        return wrapped.getJSONObject(key);
    }

    public long getLong(String key)
            throws JSONException {
        return wrapped.getLong(key);
    }

    public String getString(String key)
            throws JSONException {
        return wrapped.getString(key);
    }

    public boolean has(String key) {
        return wrapped.has(key);
    }

    public self_t increment(String key)
            throws JSONException {
        wrapped.increment(key);
        return (self_t) this;
    }

    public boolean isNull(String key) {
        return wrapped.isNull(key);
    }

    public Iterator<String> keys() {
        return wrapped.keys();
    }

    public Set<String> keySet() {
        return wrapped.keySet();
    }

    public int length() {
        return wrapped.length();
    }

    public JSONArray names() {
        return wrapped.names();
    }

    public Object opt(String key) {
        return wrapped.opt(key);
    }

    public boolean optBoolean(String key) {
        return wrapped.optBoolean(key);
    }

    public boolean optBoolean(String key, boolean defaultValue) {
        return wrapped.optBoolean(key, defaultValue);
    }

    public double optDouble(String key) {
        return wrapped.optDouble(key);
    }

    public double optDouble(String key, double defaultValue) {
        return wrapped.optDouble(key, defaultValue);
    }

    public int optInt(String key) {
        return wrapped.optInt(key);
    }

    public int optInt(String key, int defaultValue) {
        return wrapped.optInt(key, defaultValue);
    }

    public JSONArray optJSONArray(String key) {
        return wrapped.optJSONArray(key);
    }

    public JSONObject optJSONObject(String key) {
        return wrapped.optJSONObject(key);
    }

    public long optLong(String key) {
        return wrapped.optLong(key);
    }

    public long optLong(String key, long defaultValue) {
        return wrapped.optLong(key, defaultValue);
    }

    public String optString(String key) {
        return wrapped.optString(key);
    }

    public String optString(String key, String defaultValue) {
        return wrapped.optString(key, defaultValue);
    }

    public self_t put(String key, boolean value)
            throws JSONException {
        wrapped.put(key, value);
        return (self_t) this;
    }

    public self_t put(String key, Collection value)
            throws JSONException {
        wrapped.put(key, value);
        return (self_t) this;
    }

    public self_t put(String key, double value)
            throws JSONException {
        wrapped.put(key, value);
        return (self_t) this;
    }

    public self_t put(String key, int value)
            throws JSONException {
        wrapped.put(key, value);
        return (self_t) this;
    }

    public self_t put(String key, long value)
            throws JSONException {
        wrapped.put(key, value);
        return (self_t) this;
    }

    public self_t put(String key, Map value)
            throws JSONException {
        wrapped.put(key, value);
        return (self_t) this;
    }

    public self_t put(String key, Object value)
            throws JSONException {
        wrapped.put(key, value);
        return (self_t) this;
    }

    public self_t putOnce(String key, Object value)
            throws JSONException {
        wrapped.putOnce(key, value);
        return (self_t) this;
    }

    public self_t putOpt(String key, Object value)
            throws JSONException {
        wrapped.putOpt(key, value);
        return (self_t) this;
    }

    public Object remove(String key) {
        return wrapped.remove(key);
    }

    public JSONArray toJSONArray(JSONArray names)
            throws JSONException {
        return wrapped.toJSONArray(names);
    }

    @Override
    public String toString() {
        return wrapped.toString();
    }

    public String toString(int indentFactor)
            throws JSONException {
        return wrapped.toString(indentFactor);
    }

    public Writer write(Writer writer)
            throws JSONException {
        return wrapped.write(writer);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        JSONObjectWrapper<?> o = (JSONObjectWrapper<?>) obj;
        return wrapped.equals(o.wrapped);
    }

    @Override
    public int hashCode() {
        int hash = 0xc59e4544;
        hash += wrapped.hashCode();
        return hash;
    }

}
