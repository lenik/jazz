package net.bodz.bas.potato.model;

public abstract class SimpleMethod
        extends SimplePotatoElement
        implements IMethod {

    private static final long serialVersionUID = 1L;

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
