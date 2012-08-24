package net.bodz.swt.viz.invoke;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.ref.IRefDescriptor;
import net.bodz.bas.potato.traits.IMethod;
import net.bodz.bas.potato.traits.IType;

public class InvocationDescriptor
        extends AbstractInvocationDescriptor {

    private static final long serialVersionUID = 1L;

    IMethod method;

    public InvocationDescriptor(IMethod method, IRefDescriptor[] parameterDescriptors) {
        super(method, parameterDescriptors);
        this.method = method;
    }

    @Override
    public MethodSignature getSignature() {
        return method.getSignature();
    }

    @Override
    public Class<?> getValueType() {
        return method.getReturnType();
    }

    @Override
    public IType getPotatoType() {
        return null;
    }

    @Override
    public boolean isNonVoid() {
        return getValueType() != void.class;
    }

}
