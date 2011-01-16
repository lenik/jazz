package net.bodz.bas.potato.traits;

import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

public interface IConstructor
        extends IMember {

    /**
     * The name of the constructor is meaningless, and it's undetermined in potato context.
     * 
     * @return Arbitrary value.
     */
    @Override
    String getName();

    Class<?>[] getParameterTypes();

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
