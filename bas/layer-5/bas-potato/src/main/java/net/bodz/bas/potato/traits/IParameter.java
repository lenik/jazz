package net.bodz.bas.potato.traits;

public interface IParameter
        extends IElement {

    /**
     * @return non-<code>null</code>
     */
    IMethod getDeclaringMethod();

    /**
     * @return non-<code>null</code> parameter type.
     */
    Class<?> getType();

}
