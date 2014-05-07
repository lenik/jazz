package net.bodz.bas.ui.dom1;

import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public class AbstractUiElement
        extends AbstractElement
        implements IUiElement {

    @Override
    public IUiElementStyleDeclaration getStyle() {
        return null;
    }

}
