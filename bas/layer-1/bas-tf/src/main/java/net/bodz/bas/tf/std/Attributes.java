package net.bodz.bas.tf.std;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.object.Nullables;

public class Attributes
        implements IAttributes {

    private Map<String, Object> map;
    private Map<String, ICommonTypeFeatures<?>> typeMap;;

    public Attributes() {
        this(null);
    }

    public Attributes(Map<String, ICommonTypeFeatures<?>> typeMap) {
        this.map = new TreeMap<String, Object>();
        this.typeMap = typeMap;
    }

    @Override
    public Object getAttribute(String name) {
        return map.get(name);
    }

    @Override
    public Collection<String> getAttributeNames() {
        return map.keySet();
    }

    @Override
    public ICommonTypeFeatures<?> getAttributeTypeFeatures(String attributeName) {
        if (typeMap != null)
            return typeMap.get(attributeName);
        return null;
    }

    public void setAttribute(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        map.put(name, value);
    }

    public void setAttributeTypeInfo(String name, ICommonTypeFeatures<?> typeInfo) {
        if (name == null)
            throw new NullPointerException("name");
        if (typeInfo == null)
            throw new NullPointerException("typeInfo");
        if (typeMap == null)
            typeMap = new HashMap<String, ICommonTypeFeatures<?>>();
        typeMap.put(name, typeInfo);
    }

    @Override
    public int hashCode() {
        int hash = 0xf5a440eb;
        hash += map.hashCode();
        if (typeMap != null)
            hash += typeMap.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Attributes))
            return false;
        Attributes o = (Attributes) obj;
        if (!map.equals(o.map))
            return false;
        if (!Nullables.equals(typeMap, o.typeMap))
            return false;
        return true;
    }

}
