package net.bodz.bas.potato.element;

import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;

public interface IPotatoElement
        extends
            IXjdocElement,
            IAnnotated {

    IType getDeclaringType();

    /**
     * The class where this element is declared.
     *
     * @return The declaring class.
     */
    default Class<?> getDeclaringClass() {
        IType type = getDeclaringType();
        if (type == null)
            return null;
        else
            return type.getJavaClass();
    }

    /**
     * Get typer.
     *
     * @return <code>null</code> if the specific typer is undefined.
     */
    <T> T getTyper(Class<T> typerClass);

}
