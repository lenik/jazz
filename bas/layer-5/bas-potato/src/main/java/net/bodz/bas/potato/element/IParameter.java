package net.bodz.bas.potato.element;

public interface IParameter
        extends IPotatoElement {

    /**
     * @return non-<code>null</code>
     */
    IMethod getDeclaringMethod();

    /**
     * @return non-<code>null</code> parameter type.
     */
    Class<?> getType();

    boolean isOptional();

}
