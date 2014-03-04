package net.bodz.bas.potato.element;

import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;

public interface IPotatoElement
        extends IXjdocElement, IAnnotated {

    /**
     * The type where this element is declared.
     *
     * @return The declaring type.
     */
    Class<?> getDeclaringClass();

    /**
     * Get typer.
     *
     * @return <code>null</code> if the specific typer is undefined.
     */
    <T> T getTyper(Class<T> typerClass);

}
