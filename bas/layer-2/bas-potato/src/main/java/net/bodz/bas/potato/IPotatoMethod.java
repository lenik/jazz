package net.bodz.bas.potato;

public interface IPotatoMethod
        extends IPotatoElement {

    Class<?> getReturnType();

    Class<?>[] getParameterTypes();

    // IPotatoParameter[] getParameters();

    /**
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code>.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     * @throws IllegalArgumentException
     *             If any parameter isn't of the corresponding parameter type.
     */
    Object invoke(Object instance, Object... parameters)
            throws PotatoException, PotatoTargetException;

}
