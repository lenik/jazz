package net.bodz.bas.gui.dom1;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.i18n.dom1.DecoratedElement;
import net.bodz.bas.i18n.dom1.IElement;

public abstract class DecoratedGUIElement
        extends DecoratedElement
        implements IGUIElement {

    private static final long serialVersionUID = 1L;

    protected DecoratedGUIElement(IElement _orig) {
        super(_orig);
    }

    @Override
    public IGUIElement getWrapped() {
        return (IGUIElement) _orig;
    }

    @Override
    public IGUIElementStyleDeclaration getStyle() {
        return getWrapped().getStyle();
    }

}
