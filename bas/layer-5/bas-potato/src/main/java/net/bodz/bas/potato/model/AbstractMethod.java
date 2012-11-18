package net.bodz.bas.potato.model;

import net.bodz.bas.c.reflect.MethodSignature;

public abstract class AbstractMethod
        extends AbstractPotatoElement
        implements IMethod {

    transient IParameter[] parameters;

    public AbstractMethod(Class<?> declaringType, String methodName) {
        super(declaringType, methodName);
    }

    @Override
    public MethodSignature getSignature() {
        String methodName = getName();
        Class<?>[] parameterTypes = getParameterTypes();
        MethodSignature signature = new MethodSignature(methodName, parameterTypes);
        return signature;
    }

    @Override
    public synchronized IParameter[] getParameters() {
        if (parameters == null) {
            Class<?>[] parameterTypes = getParameterTypes();

            int n = parameterTypes.length;
            parameters = new IParameter[n];

            for (int i = 0; i < n; i++)
                parameters[i] = createParameter(i);
        }
        return parameters;
    }

    protected abstract IParameter createParameter(int index);

    @Override
    public Object invokeStatic(Object... parameters)
            throws ReflectiveOperationException {
        return invoke(null, parameters);
    }

}
