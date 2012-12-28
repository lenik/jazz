package net.bodz.bas.potato.invoke;

import net.bodz.bas.potato.element.IConstructor;

public class Instantiation
        extends AbstractInvocation {

    private static final long serialVersionUID = 1L;

    private final IConstructor constructor;
    private final Class<?>[] parameterTypes;

    // private final Object outer;

    public Instantiation(IConstructor constructor, Class<?>[] parameterTypes) {
        super(parameterTypes.length);
        this.constructor = constructor;
        this.parameterTypes = parameterTypes;
    }

    @Override
    public Class<?> getReturnType() {
        return constructor.getDeclaringClass();
    }

    @Override
    public Class<?> getParameterType(int index) {
        return parameterTypes[index];
    }

    @Override
    public final Object invoke()
            throws ReflectiveOperationException {
        return newInstance();
    }

    public Object newInstance()
            throws ReflectiveOperationException {
        return constructor.newInstance(getParameters());
    }

}
