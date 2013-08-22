package net.bodz.bas.gui.dom1;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.i18n.dom1.IElement;

public interface IGUIElement
        extends IElement {

    /**
     * Get the GUI element style declaration.
     */
    IGUIElementStyleDeclaration getStyle();

}
