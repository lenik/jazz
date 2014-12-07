package net.bodz.bas.typer.std;

import java.util.Collection;
import java.util.Collections;

public class EmptyAttributes
        implements IMutableAttributes {

    EmptyAttributes() {
    }

    @Override
    public Collection<String> getAttributeNames() {
        return Collections.emptyList();
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
    public ITyperFamily<?> getAttributeTypers(String attributeName) {
        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
    }

    @Override
    public void removeAttribute(String name) {
    }

    @Override
    public void setAttributeTypers(String name, ITyperFamily<?> typers) {
    }

    private static EmptyAttributes instance = new EmptyAttributes();

    public static EmptyAttributes getInstance() {
        return instance;
    }

}
