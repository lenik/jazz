package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.meta.decl.NotNull;

public class DecoratedConstructor
        extends DecoratedPotatoElement
        implements IConstructor {

    private static final long serialVersionUID = 1L;

    public DecoratedConstructor(IConstructor _orig) {
        super(_orig);
    }

    @NotNull
    @Override
    public IConstructor getWrapped() {
        return (IConstructor) _orig; // super.getWrapped();
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return getWrapped().getParameterTypes();
    }

    @Override
    public MethodSignature getSignature() {
        return getWrapped().getSignature();
    }

    @Override
    public Object newInstance(Object... parameters)
            throws ReflectiveOperationException {
        return getWrapped().newInstance(parameters);
    }

}
