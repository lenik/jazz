package net.bodz.bas.potato.model;

import net.bodz.bas.c.reflect.MethodSignature;

public abstract class AbstractConstructor
        extends AbstractPotatoElement
        implements IConstructor {

    public AbstractConstructor(Class<?> declaringType) {
        super(declaringType, null);
    }

    @Override
    public MethodSignature getSignature() {
        // String methodName = "<ctor>";
        String methodName = getDeclaringClass().getSimpleName();
        Class<?>[] parameterTypes = getParameterTypes();
        MethodSignature signature = new MethodSignature(methodName, parameterTypes);
        return signature;
    }

}
