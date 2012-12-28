package net.bodz.bas.potato.invoke;

import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.t.ref.Ref;

public class Invocation
        extends AbstractInvocation {

    private static final long serialVersionUID = 1L;

    Ref<?> instanceRef;
    IMethod method;
    Class<?>[] parameterTypes;

    public Invocation(Ref<?> instanceRef, IMethod method) {
        this(instanceRef, method, method.getParameterTypes());
    }

    Invocation(Ref<?> instanceRef, IMethod method, Class<?>[] parameterTypes) {
        super(parameterTypes.length);

        if (instanceRef == null)
            throw new NullPointerException("instanceRef");

        this.instanceRef = instanceRef;
        this.method = method;
        this.parameterTypes = parameterTypes;
    }

    @Override
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    @Override
    public Class<?> getParameterType(int index) {
        return parameterTypes[index];
    }

    public Object getInstance() {
        return instanceRef.get();
    }

    @Override
    public Object invoke()
            throws ReflectiveOperationException {
        return method.invoke(getInstance(), getParameters());
    }

}
