package net.bodz.bas.potato.traits;

import java.beans.MethodDescriptor;
import java.lang.reflect.Method;


public class MethodKey
        extends MethodLikeKey {

    private static final long serialVersionUID = 1L;

    private boolean defaultMethod;

    public MethodKey(String name, Class<?>[] parameterTypes) {
        super(name, parameterTypes);
        if (name == null)
            throw new NullPointerException("name");
    }

    public boolean isDefaultMethod() {
        return defaultMethod;
    }

    public boolean isMatched(Method method) {
        if (!method.getName().equals(name))
            return false;
        return _isMatched(method.getParameterTypes());
    }

    public boolean isMatched(MethodDescriptor methodDescriptor) {
        if (!methodDescriptor.getName().equals(name))
            return false;
        Method method = methodDescriptor.getMethod();
        return _isMatched(method.getParameterTypes());
    }

    @Override
    public String toString() {
        return "method=" + getSignature();
    }

}
