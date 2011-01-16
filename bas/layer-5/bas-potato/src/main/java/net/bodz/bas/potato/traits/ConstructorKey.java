package net.bodz.bas.potato.traits;

import java.beans.MethodDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class ConstructorKey
        extends MethodLikeKey {

    private static final long serialVersionUID = 1L;

    public ConstructorKey(Class<?>[] parameterTypes) {
        super(null, parameterTypes);
    }

    public boolean isMatched(Constructor<?> ctor) {
        return _isMatched(ctor.getParameterTypes());
    }

    public boolean isMatched(MethodDescriptor methodDescriptor) {
        Method method = methodDescriptor.getMethod();
        return _isMatched(method.getParameterTypes());
    }

    @Override
    public String toString() {
        return "ctor=" + getSignature();
    }

}
