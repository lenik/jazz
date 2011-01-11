package net.bodz.bas.potato;

public interface IPotatoParameter
        extends IPotatoElement {

    /**
     * @return non-<code>null</code>
     */
    IPotatoMethod getDeclaringMethod();

    /**
     * @return non-<code>null</code> parameter type.
     */
    Class<?> getType();

}
