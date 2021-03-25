package net.bodz.bas.typer.std;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.object.Nullables;

public class MutableAttributes
        implements IMutableAttributes {

    private Map<String, Object> valueMap;
    private Map<String, ITyperFamily<?>> typersMap;

    public MutableAttributes() {
        this(null);
    }

    public MutableAttributes(Map<String, ITyperFamily<?>> typerMap) {
        this.valueMap = new TreeMap<String, Object>();
        this.typersMap = typerMap;
    }

    @Override
    public Collection<String> getAttributeNames() {
        return valueMap.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String name) {
        return (T) valueMap.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        @SuppressWarnings("unchecked")
        T value = (T) valueMap.get(name);
        if (value == null && !valueMap.containsKey(name))
            return defaultValue;
        else
            return value;
    }

    @Override
    public ITyperFamily<?> getAttributeTypers(String attributeName) {
        if (typersMap != null)
            return typersMap.get(attributeName);
        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        valueMap.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        valueMap.remove(name);
    }

    @Override
    public void setAttributeTypers(String name, ITyperFamily<?> typers) {
        if (name == null)
            throw new NullPointerException("name");
        if (typers == null)
            throw new NullPointerException("typeInfo");
        if (typersMap == null)
            typersMap = new HashMap<String, ITyperFamily<?>>();
        typersMap.put(name, typers);
    }

    @Override
    public int hashCode() {
        int hash = 0xf5a440eb;
        hash += valueMap.hashCode();
        if (typersMap != null)
            hash += typersMap.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MutableAttributes))
            return false;
        MutableAttributes o = (MutableAttributes) obj;
        if (!valueMap.equals(o.valueMap))
            return false;
        if (!Nullables.equals(typersMap, o.typersMap))
            return false;
        return true;
    }

}
