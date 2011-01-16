package net.bodz.bas.traits;

import java.util.Collection;
import java.util.Collections;

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
    public ICommonTraits<?> getAttributeTraits(String attributeName) {
        return null;
    }

    private static EmptyAttributes instance = new EmptyAttributes();

    public static EmptyAttributes getInstance() {
        return instance;
    }

}
