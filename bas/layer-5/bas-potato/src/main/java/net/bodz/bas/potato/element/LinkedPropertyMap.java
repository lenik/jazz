package net.bodz.bas.potato.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.t.iterator.Iterables;

public class LinkedPropertyMap
        implements IPropertyMap {

    private List<IPropertyMap> maps;

    public LinkedPropertyMap(List<IPropertyMap> propertyMaps) {
        if (propertyMaps == null)
            throw new NullPointerException("propertyMaps");
        this.maps = propertyMaps;
    }

    public LinkedPropertyMap(IPropertyMap... propertyMaps) {
        maps = Arrays.asList(propertyMaps);
    }

    @Override
    public int getPropertyCount() {
        int count = 0;
        for (IPropertyMap map : maps)
            count += map.getPropertyCount();
        return count;
    }

    @Override
    public Iterable<IProperty> getProperties() {
        List<Iterable<IProperty>> iterables = new ArrayList<>(maps.size());

        for (IPropertyMap map : maps)
            iterables.add(map.getProperties());

        return Iterables.concat(iterables);
    }

    @Override
    public Set<String> getPropertyNames() {
        Set<String> set = new LinkedHashSet<>();

        for (IPropertyMap map : maps)
            set.addAll(map.getPropertyNames());

        return set;
    }

    @Override
    public boolean containsProperty(String propertyName) {
        for (IPropertyMap map : maps)
            if (map.containsProperty(propertyName))
                return true;
        return false;
    }

    @Override
    public IProperty getProperty(String propertyName) {
        for (IPropertyMap map : maps) {
            IProperty property = map.getProperty(propertyName);
            if (property != null)
                return property;
        }
        return null;
    }

}
