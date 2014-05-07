package net.bodz.bas.ui.dom1;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public interface IUiElement
        extends IElement {

    /**
     * Get the GUI element style declaration.
     */
    IUiElementStyleDeclaration getStyle();

}
