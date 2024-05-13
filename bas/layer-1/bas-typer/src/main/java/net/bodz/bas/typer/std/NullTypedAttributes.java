package net.bodz.bas.typer.std;

import net.bodz.bas.rtx.NullAttributes;

public class NullTypedAttributes
        extends NullAttributes
        implements
            ITypedAttributes {

    protected NullTypedAttributes() {
    }

    @Override
    public IAttributeDescriptor getAttributeDescriptor(String name) {
        return null;
    }

    @Override
    public String getAttributeLabel(String name) {
        return null;
    }

    @Override
    public String getAttributeDescription(String name) {
        return null;
    }

    @Override
    public String getAttributeIcon(String name) {
        return null;
    }

    @Override
    public Class<?> getAttributeType(String name) {
        return null;
    }

    @Override
    public <T> T getAttributeTyper(String name, Class<T> typerClass) {
        return null;
    }

    public static final NullTypedAttributes INSTANCE = new NullTypedAttributes();

}
