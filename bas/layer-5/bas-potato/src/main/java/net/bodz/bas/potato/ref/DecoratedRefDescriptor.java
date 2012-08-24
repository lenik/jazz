package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.traits.DecoratedElement;
import net.bodz.bas.potato.traits.IProperty;
import net.bodz.bas.potato.traits.IType;

public class DecoratedRefDescriptor
        extends DecoratedElement
        implements IRefDescriptor {

    private static final long serialVersionUID = 1L;

    public DecoratedRefDescriptor(IRefDescriptor _orig) {
        super(_orig);
    }

    @Override
    public IRefDescriptor getWrapped() {
        return (IRefDescriptor) _orig;
    }

    @Override
    public IProperty getDeclaringProperty() {
        return getWrapped().getDeclaringProperty();
    }

    @Override
    public Class<?> getValueType() {
        return getWrapped().getValueType();
    }

    @Override
    public IType getPotatoType() {
        return getWrapped().getPotatoType();
    }

    @Override
    public boolean isReadable() {
        return getWrapped().isReadable();
    }

    @Override
    public boolean isWritable() {
        return getWrapped().isWritable();
    }

    @Override
    public boolean isValueChangeSource() {
        return getWrapped().isValueChangeSource();
    }

}
