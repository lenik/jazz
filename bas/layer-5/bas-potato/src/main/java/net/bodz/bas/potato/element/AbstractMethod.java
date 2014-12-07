package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class AbstractMethod
        extends AbstractPotatoElement
        implements IMethod {

    private transient IParameter[] parameters;

    public AbstractMethod(Class<?> declaringType, String methodName, IElementDoc doc) {
        super(declaringType, methodName, doc);
    }

    @Override
    public boolean isOverloaded() {
        return false;
    }

    @Override
    public MethodSignature getSignature() {
        String methodName = getName();
        Class<?>[] parameterTypes = getParameterTypes();
        MethodSignature signature = new MethodSignature(methodName, parameterTypes);
        return signature;
    }

    @Override
    public IParameter[] getParameters() {
        if (parameters == null)
            synchronized (this) {
                if (parameters == null)
                    parameters = createParameters();
            }
        return parameters;
    }

    private IParameter[] createParameters() {
        Class<?>[] parameterTypes = getParameterTypes();

        int n = parameterTypes.length;
        IParameter[] parameters = new IParameter[n];

        for (int i = 0; i < n; i++)
            parameters[i] = createParameter(i);

        return parameters;
    }

    protected abstract IParameter createParameter(int index);

    @Override
    public final Object invokeStatic(Object... parameters)
            throws ReflectiveOperationException {
        return invoke(null, parameters);
    }

}
