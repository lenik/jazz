package net.bodz.bas.potato.traits;

public abstract class SimpleMethod
        extends SimpleElement
        implements IMethod {

    Class<?> returnType;
    Class<?>[] parameterTypes;

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

}
