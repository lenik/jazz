package net.bodz.bas.potato.model.invoke;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.model.IConstructor;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.potato.ref.IRefDescriptor;

public class InstantiateDescriptor
        extends AbstractInvocationDescriptor {

    private static final long serialVersionUID = 1L;

    IConstructor constructor;

    public InstantiateDescriptor(IConstructor constructor, IRefDescriptor[] parameterDescriptors) {
        super(constructor, parameterDescriptors);
        this.constructor = constructor;
    }

    @Override
    public MethodSignature getSignature() {
        return constructor.getSignature();
    }

    @Override
    public Class<?> getValueType() {
        return constructor.getDeclaringClass();
    }

    @Override
    public IType getPotatoType() {
        return null;
    }

    @Override
    public boolean isNonVoid() {
        return true;
    }

}
