package net.bodz.bas.potato.traits;

import java.util.TreeMap;

import net.bodz.bas.collection.comparator.ComparableComparator;

public class AbstractPropertyMap
        extends TreeMap<String, IProperty>
        implements IPropertyMap {

    private static final long serialVersionUID = 1L;

    public AbstractPropertyMap() {
        super(ComparableComparator.getInstance());
    }

    @Override
    public IProperty getProperty(PropertyKey propertyKey) {
        return get(propertyKey.getName());
    }

}
