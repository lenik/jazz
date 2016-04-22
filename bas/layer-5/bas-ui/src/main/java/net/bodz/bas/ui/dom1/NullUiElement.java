package net.bodz.bas.ui.dom1;

import net.bodz.bas.i18n.dom1.NullElement;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public class NullUiElement
        extends NullElement
        implements IUiElement {

    @Override
    public IUiElementStyleDeclaration getStyle() {
        return IUiElementStyleDeclaration.NULL;
    }

}
