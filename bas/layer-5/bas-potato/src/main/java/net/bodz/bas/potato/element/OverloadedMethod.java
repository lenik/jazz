package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.c.type.TypeMath;
import net.bodz.bas.c.type.TypeSpace;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class OverloadedMethod
        extends AbstractMethod {

    static boolean checkNames = true;
    static TypeSpace typeSpace = new TypeSpace(10, 10000, 1);

    private Class<?> returnType;
    private Class<?>[] parameterTypes;
    private int minParameterCount;

    private List<IMethod> methods;
    private Map<TypeArray, Object> tvConvMap = new HashMap<TypeArray, Object>();
    private static Object NONE = new Object();

    public OverloadedMethod(Class<?> declaringType, List<IMethod> methods, IElementDoc doc) {
        super(declaringType, methods.get(0).getName(), doc);
        this.methods = methods;

        IMethod method0 = methods.get(0);
        String name = method0.getName();
        Class<?> returnType = method0.getReturnType();
        Class<?>[] tv0 = method0.getParameterTypes();
        int min = tv0.length;
        int max = min;

        int n = methods.size();
        if (n > 1) {
            if (checkNames)
                for (int i = 1; i < n; i++)
                    if (!methods.get(i).getName().equals(name))
                        throw new IllegalUsageException("Methods with different names.");

            for (int i = 1; i < n; i++) {
                IMethod m = methods.get(i);
                returnType = TypeMath.getCommonSuperclass(returnType, m.getReturnType());
                int pc = m.getParameterCount();
                if (pc < min)
                    min = pc;
                if (pc > max)
                    max = pc;
            }

            parameterTypes = new Class<?>[max];
            for (int i = 0; i < tv0.length; i++)
                parameterTypes[i] = tv0[i];
        } else {
            parameterTypes = tv0;
        }

        this.returnType = returnType;
        this.minParameterCount = min;

        for (int i = 1; i < n; i++) {
            IMethod m = methods.get(i);
            Class<?>[] tv = m.getParameterTypes();
            for (int p = 0; p < tv.length; p++) {
                Class<?> type = parameterTypes[p];
                Class<?> t = tv[p];
                if (type == null)
                    type = t;
                else {
                    type = TypeMath.getCommonSuperclass(type, t);
                    if (type == null)
                        type = Object.class;
                }
                parameterTypes[p] = type;
            }
        }
    }

    @Override
    public boolean isOverloaded() {
        return true;
    }

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }

    @Override
    public int getParameterCount() {
        return parameterTypes.length;
    }

    public int getMinParameterCount() {
        return minParameterCount;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        // return parameterTypes;
        return parameterTypes.clone();
    }

    @Override
    protected IParameter createParameter(int index) {
        return null;
    }

    public IMethod getActualMethod(TypeArray parameterTypes) {
        Object _method = tvConvMap.get(parameterTypes);
        if (_method == null) {
            _method = findActualMethod(parameterTypes.getArray());
            if (_method == null)
                _method = NONE;
            tvConvMap.put(parameterTypes, _method);
        }
        if (_method == NONE)
            return null;
        else
            return (IMethod) _method;
    }

    /**
     * @param tv
     *            The actual parameter type vector.
     * @return <code>null</code> if not found.
     */
    protected IMethod findActualMethod(Class<?>[] tv) {
        int minDist = -1;
        IMethod minMethod = null;

        for (IMethod m : methods) {
            Class<?>[] mtv = m.getParameterTypes();
            int dist = typeSpace.dist(mtv, tv);
            if (dist != -1)
                if (minDist == -1 || dist < minDist) {
                    minDist = dist;
                    minMethod = m;
                }
        }
        return minMethod;
    }

    @Override
    public Object invoke(Object instance, Object... parameters)
            throws ReflectiveOperationException {
        TypeArray parameterTypes = TypeArray.fromObjects(null, parameters);
        IMethod method = getActualMethod(parameterTypes);
        if (method == null)
            throw new IllegalArgumentException("Illegal parameter types: " + parameterTypes);
        else
            return method.invoke(instance, parameters);
    }

    /** ⇱ Implementation Of {@link AnnotatedElement}. */
    /* _____________________________ */static section.iface __ANNOTATED__;

    @Override
    public Annotation[] getAnnotations() {
        IMethod method0 = methods.get(0);
        return method0.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        IMethod method0 = methods.get(0);
        return method0.getDeclaredAnnotations();
    }

}
