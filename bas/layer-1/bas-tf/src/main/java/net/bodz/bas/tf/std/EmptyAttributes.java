package net.bodz.bas.tf.std;

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
    public ICommonTypeFeatures<?> getAttributeTypeFeatures(String attributeName) {
        return null;
    }

    private static EmptyAttributes instance = new EmptyAttributes();

    public static EmptyAttributes getInstance() {
        return instance;
    }

}
