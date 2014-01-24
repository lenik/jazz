package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.t.order.ComparableComparator;

public class MutablePropertyMap
        implements IPropertyMap {

    Map<String, IProperty> map;

    public MutablePropertyMap(boolean sorted) {
        map = createMap(sorted);
    }

    protected Map<String, IProperty> createMap(boolean sorted) {
        if (sorted)
            return new TreeMap<>(ComparableComparator.getInstance());
        else
            return new LinkedHashMap<>();
    }

    @Override
    public int getPropertyCount() {
        return map.size();
    }

    @Override
    public Collection<IProperty> getProperties() {
        return map.values();
    }

    @Override
    public Set<String> getPropertyNames() {
        return map.keySet();
    }

    @Override
    public boolean containsProperty(String propertyName) {
        return map.containsKey(propertyName);
    }

    @Override
    public IProperty getProperty(String propertyName) {
        if (propertyName == null)
            throw new NullPointerException("propertyName");
        return map.get(propertyName);
    }

    public void addProperty(IProperty property) {
        if (property == null)
            throw new NullPointerException("property");

        String propertyName = property.getName();

        IProperty existing = map.get(propertyName);
        if (existing != null)
            throw new DuplicatedKeyException(propertyName);

        map.put(propertyName, property);
    }

    public IProperty removeProperty(String propertyName) {
        if (propertyName == null)
            throw new NullPointerException("propertyName");
        return map.remove(propertyName);
    }

}
