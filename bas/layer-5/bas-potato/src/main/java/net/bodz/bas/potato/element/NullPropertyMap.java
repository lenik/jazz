package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class NullPropertyMap
        implements IPropertyMap {

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public Collection<IProperty> getProperties() {
        return Collections.emptyList();
    }

    @Override
    public Set<String> getPropertyNames() {
        return Collections.emptySet();
    }

    @Override
    public boolean containsProperty(String propertyName) {
        return false;
    }

    @Override
    public IProperty getProperty(String propertyName) {
        return null;
    }

    static final NullPropertyMap instance = new NullPropertyMap();

    public static NullPropertyMap getInstance() {
        return instance;
    }

}
