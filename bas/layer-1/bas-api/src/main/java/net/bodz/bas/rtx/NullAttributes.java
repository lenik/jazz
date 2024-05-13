package net.bodz.bas.rtx;

import java.util.Collections;
import java.util.Set;

public class NullAttributes
        implements
            IMutableAttributes {

    protected NullAttributes() {
    }

    @Override
    public boolean isAttributePresent(String name) {
        return false;
    }

    @Override
    public Set<String> getAttributeNames() {
        return Collections.emptySet();
    }

    @Override
    public <T> T getAttribute(String attributeName) {
        return null;
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        return defaultValue;
    }

    @Override
    public void setAttribute(String name, Object value) {
    }

    @Override
    public void removeAttribute(String name) {
    }

    public static final NullAttributes INSTANCE = new NullAttributes();

}
