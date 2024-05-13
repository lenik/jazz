package net.bodz.bas.rtx;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.bean.JsonRedundant;
import net.bodz.bas.repr.form.SortOrder;

public class MutableAttributes
        implements
            IMutableAttributes {

    protected Map<String, Object> attributeMap;

    public MutableAttributes() {
        this(SortOrder.KEEP);
    }

    public MutableAttributes(SortOrder order) {
        this.attributeMap = order.newMap();
    }

    public MutableAttributes(Map<String, Object> attributeMap) {
        if (attributeMap == null)
            throw new NullPointerException("attributeMap");
        this.attributeMap = attributeMap;
    }

    @JsonRedundant
    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    @JsonRedundant
    @Override
    public Set<String> getAttributeNames() {
        return attributeMap.keySet();
    }

    @Override
    public boolean isAttributePresent(String name) {
        return attributeMap.containsKey(name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String name) {
        return (T) attributeMap.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        @SuppressWarnings("unchecked")
        T value = (T) attributeMap.get(name);
        if (value == null && ! attributeMap.containsKey(name))
            return defaultValue;
        else
            return value;
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        attributeMap.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributeMap.remove(name);
    }

    public boolean contentEquals(MutableAttributes o) {
        if (o == null)
            return false;
        if (! attributeMap.equals(o.attributeMap))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return attributeMap.toString();
    }

}
