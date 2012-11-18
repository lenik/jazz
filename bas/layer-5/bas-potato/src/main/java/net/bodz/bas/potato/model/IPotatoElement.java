package net.bodz.bas.potato.model;

import net.bodz.bas.i18n.dom1.IElement;

public interface IPotatoElement
        extends IElement, IAnnotatedElement {

    /**
     * The type where this element is declared.
     * 
     * @return The declaring type.
     */
    Class<?> getDeclaringClass();

}
