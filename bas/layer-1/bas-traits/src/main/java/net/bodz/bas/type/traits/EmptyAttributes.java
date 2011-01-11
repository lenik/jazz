package net.bodz.bas.type.traits;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.type.ITypeTraits;

public class EmptyAttributes
        implements IAttributes {

    EmptyAttributes() {
    }

    @Override
    public Collection<String> getAttributeNames() {
        return Collections.emptyList();
    }

    @Override
    public Object getAttribute(String attributeName) {
        return null;
    }

    @Override
    public ITypeTraits<?> getAttributeTraits(String attributeName) {
        return null;
    }

    private static EmptyAttributes instance = new EmptyAttributes();

    public static EmptyAttributes getInstance() {
        return instance;
    }

}
