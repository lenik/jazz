package net.bodz.bas.potato.element;

import net.bodz.bas.i18n.dom1.IElement;

public interface IPotatoElement
        extends IAnnotated, IElement {

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
