package net.bodz.bas.repr.position;

import net.bodz.bas.potato.model.IProperty;

public class InProperty
        extends AbstractObjectOccurrence {

    private Object context;
    private IProperty property;

    public InProperty(Object context, IProperty property) {
        if (context == null)
            throw new NullPointerException("context");
        if (property == null)
            throw new NullPointerException("property");
        this.context = context;
        this.property = property;
    }

    @Override
    public Object getContext() {
        return context;
    }

    @Override
    public String getPath() {
        return property.getName();
    }

    public IProperty getProperty() {
        return property;
    }

}
