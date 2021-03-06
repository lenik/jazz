package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;

public interface IConstructor
        extends IPotatoElement {

    /**
     * The name of the constructor is meaningless, and it's undetermined in potato context.
     * 
     * @return Arbitrary value.
     */
    @Override
    String getName();

    Class<?>[] getParameterTypes();

    MethodSignature getSignature();

    /**
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code>.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     * @throws IllegalArgumentException
     *             If any parameter isn't of the corresponding parameter type.
     */
    Object newInstance(Object... parameters)
            throws ReflectiveOperationException;

}
