package net.bodz.bas.disp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.err.IllegalUsageException;

public class MethodInjector {

    private final Class<?>[] actualTypes;
    private final Object[] parameters;

    public MethodInjector(Class<?>[] actualTypes, Object... parameters) {
        if (actualTypes == null)
            throw new NullPointerException("actualTypes");
        if (parameters == null)
            throw new NullPointerException("parameters");

        this.actualTypes = actualTypes;
        this.parameters = parameters;
    }

    public MethodInjector(Object... valueArray) {
        if (valueArray == null)
            throw new NullPointerException("valueArray");

        this.actualTypes = new Class<?>[valueArray.length];
        for (int i = 0; i < valueArray.length; i++) {
            this.actualTypes[i] = valueArray[i].getClass();
        }
        this.parameters = valueArray;
    }

    /**
     * Test if all parameters required by given method is fulfilled from this method injector.
     * 
     * @return Parameter-mapping, to reorder the actual parameters to fulfill the declared
     *         prototype.
     */
    public int[] matches(Method method) {
        Class<?>[] declTypes = method.getParameterTypes();

        int[] mapping = new int[declTypes.length];

        for (int declIndex = 0; declIndex < declTypes.length; declIndex++) {
            Class<?> declType = declTypes[declIndex];
            int actualIndex = search(declType);
            if (actualIndex == -1)
                return null;

            mapping[declIndex] = actualIndex;
        }
        return mapping;
    }

    /**
     * @return -1 if none available.
     */
    public int search(Class<?> declaredType) {
        for (int actualIndex = 0; actualIndex < actualTypes.length; actualIndex++) {
            Class<?> actualType = actualTypes[actualIndex];
            if (declaredType.isAssignableFrom(actualType))
                return actualIndex;
        }
        return -1;
    }

    /**
     * Invoke method with the parameters re-mapped using the inferred mapping.
     * 
     * @see Method#invoke(Object, Object...)
     */
    public Object invoke(Object obj, Method method)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        int[] mapping = matches(method);

        if (mapping == null)
            throw new IllegalUsageException("Mandatory parameter isn't fulfilled: " + method);

        return invoke(obj, method, mapping);
    }

    /**
     * Invoke method with the parameters re-mapped using explicit mapping.
     * 
     * @see Method#invoke(Object, Object...)
     */
    public Object invoke(Object obj, Method method, int[] mapping)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Object[] reorder = new Object[parameters.length];

        for (int declIndex = 0; declIndex < parameters.length; declIndex++) {
            int actualIndex = mapping[declIndex];
            reorder[declIndex] = parameters[actualIndex];
        }

        return method.invoke(obj, reorder);
    }

}
