package net.bodz.bas.potato.invoke;

import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.t.ref.Ref;
import net.bodz.bas.t.ref.Value;

public class Invocation
        extends AbstractInvocation {

    private static final long serialVersionUID = 1L;

    private Ref<?> instanceRef;
    private IMethod method;
    private Class<?>[] parameterTypes;

    public Invocation(Object instance, IMethod method) {
        this(Value.of(instance), method, method.getParameterTypes());
    }

    public Invocation(Ref<?> instanceRef, IMethod method) {
        this(instanceRef, method, method.getParameterTypes());
    }

    private Invocation(Ref<?> instanceRef, IMethod method, Class<?>[] parameterTypes) {
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
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
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
