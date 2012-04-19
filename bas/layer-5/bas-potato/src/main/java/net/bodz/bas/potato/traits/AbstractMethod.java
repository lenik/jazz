package net.bodz.bas.potato.traits;

public abstract class AbstractMethod
        extends AbstractElement
        implements IMethod {

    public AbstractMethod(Class<?> declaringType, String methodName) {
        super(declaringType, methodName);
    }

    @Override
    public Object invokeStatic(Object... parameters)
            throws ReflectiveOperationException {
        return invoke(null, parameters);
    }

}
