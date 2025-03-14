package net.bodz.bas.potato.element;

import java.lang.reflect.Method;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.meta.decl.NotNull;

public class DecoratedMethod
        extends DecoratedPotatoElement
        implements
            IMethod {

    private static final long serialVersionUID = 1L;

    public DecoratedMethod(IMethod _orig) {
        super(_orig);
    }

    @NotNull
    @Override
    public IMethod getWrapped() {
        return (IMethod) _orig; // super.getWrapped();
    }

    @Override
    public boolean isOverloaded() {
        return getWrapped().isOverloaded();
    }

    @Override
    public Method getMethod() {
        return getWrapped().getMethod();
    }

    @Override
    public Class<?> getReturnType() {
        return getWrapped().getReturnType();
    }

    @Override
    public int getParameterCount() {
        return getWrapped().getParameterCount();
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
