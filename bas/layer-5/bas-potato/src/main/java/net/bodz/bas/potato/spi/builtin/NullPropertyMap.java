package net.bodz.bas.potato.spi.builtin;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.model.IPropertyMap;

public class NullPropertyMap
        implements IPropertyMap {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<IProperty> getProperties() {
        return Collections.emptyList();
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
