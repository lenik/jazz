package net.bodz.bas.typer.std;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.object.Nullables;

public class Attributes
        implements IAttributes {

    private Map<String, Object> valueMap;
    private Map<String, ITyperFamily<?>> typersMap;

    public Attributes() {
        this(null);
    }

    public Attributes(Map<String, ITyperFamily<?>> typerMap) {
        this.valueMap = new TreeMap<String, Object>();
        this.typersMap = typerMap;
    }

    @Override
    public Object getAttribute(String name) {
        return valueMap.get(name);
    }

    @Override
    public Collection<String> getAttributeNames() {
        return valueMap.keySet();
    }

    @Override
    public ITyperFamily<?> getAttributeTypers(String attributeName) {
        if (typersMap != null)
            return typersMap.get(attributeName);
        return null;
    }

    public void setAttribute(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        valueMap.put(name, value);
    }

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
        if (!(obj instanceof Attributes))
            return false;
        Attributes o = (Attributes) obj;
        if (!valueMap.equals(o.valueMap))
            return false;
        if (!Nullables.equals(typersMap, o.typersMap))
            return false;
        return true;
    }

}
