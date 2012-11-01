package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.model.DecoratedProperty;
import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.model.IType;

public class PropertyRefDescriptor
        extends DecoratedProperty
        implements IRefDescriptor {

    private static final long serialVersionUID = 1L;

    public PropertyRefDescriptor(IProperty _orig) {
        super(_orig);
    }

    @Override
    public IProperty getDeclaringProperty() {
        return this;
    }

    @Override
    public Class<?> getValueType() {
        return getPropertyType();
    }

    @Override
    public IType getPotatoType() {
        // return getDeclaringType();
        return null;
    }

    @Override
    public boolean isValueChangeSource() {
        return isPropertyChangeSource();
    }

}
