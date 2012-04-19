package net.bodz.bas.potato.traits;

public interface IMethod
        extends IElement {

    Class<?> getReturnType();

    Class<?>[] getParameterTypes();

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
