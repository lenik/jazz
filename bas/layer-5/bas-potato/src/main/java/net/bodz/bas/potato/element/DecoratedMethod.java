package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;

public class DecoratedMethod
        extends DecoratedPotatoElement
        implements IMethod {

    private static final long serialVersionUID = 1L;

    public DecoratedMethod(IMethod _orig) {
        super(_orig);
    }

    @Override
    public IMethod getWrapped() {
        return (IMethod) _orig; // super.getWrapped();
    }

    @Override
    public Class<?> getReturnType() {
        return getWrapped().getReturnType();
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return getWrapped().getParameterTypes();
    }

    @Override
    public IParameter[] getParameters() {
        return getWrapped().getParameters();
    }

    @Override
    public MethodSignature getSignature() {
        return getWrapped().getSignature();
    }

    @Override
    public Object invoke(Object instance, Object... parameters)
            throws ReflectiveOperationException {
        return getWrapped().invoke(instance, parameters);
    }

    @Override
    public Object invokeStatic(Object... parameters)
            throws ReflectiveOperationException {
        return getWrapped().invokeStatic(parameters);
    }

}
