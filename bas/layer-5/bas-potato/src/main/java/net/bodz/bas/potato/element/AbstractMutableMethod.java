package net.bodz.bas.potato.element;

public abstract class AbstractMutableMethod
        extends MutablePotatoElement
        implements IMethod {

    private Class<?> returnType;
    private Class<?>[] parameterTypes;

    /** ⇱ Implementation Of {@link IMethod}. */
    /* _____________________________ */static section.iface __METHOD__;

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    /** ⇱ Implementation Of {@link IMutableMethod}. */
    /* _____________________________ */static section.iface __MUTABLE__;

    public void setReturnType(Class<?> returnType) {
        if (returnType == null)
            throw new NullPointerException("returnType");
        this.returnType = returnType;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        this.parameterTypes = parameterTypes;
    }

}
