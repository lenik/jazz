package net.bodz.bas.ui.dom1;

import net.bodz.bas.i18n.dom1.DecoratedElement;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public abstract class DecoratedUiElement
        extends DecoratedElement
        implements IUiElement {

    private static final long serialVersionUID = 1L;

    protected DecoratedUiElement(IElement _orig) {
        super(_orig);
    }

    @Override
    public IUiElement getWrapped() {
        return (IUiElement) _orig;
    }

    @Override
    public IUiElementStyleDeclaration getStyle() {
        return getWrapped().getStyle();
    }

}
