package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IMethod
        extends IPotatoElement {

    boolean isOverloaded();

    Class<?> getReturnType();

    int getParameterCount();

    Class<?>[] getParameterTypes();

    IParameter[] getParameters();

    MethodSignature getSignature();

    /**
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code>.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     * @throws IllegalArgumentException
     *             If any parameter isn't of the corresponding parameter type.
     */
    Object invoke(Object instance, Object... parameters)
            throws ReflectiveOperationException;

    Object invokeStatic(Object... parameters)
            throws ReflectiveOperationException;

}
