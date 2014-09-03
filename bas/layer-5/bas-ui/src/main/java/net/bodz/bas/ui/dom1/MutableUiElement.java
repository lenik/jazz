package net.bodz.bas.ui.dom1;

import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public class MutableUiElement
        extends MutableElement
        implements IUiElement {

    private static final long serialVersionUID = 1L;

    private IUiElementStyleDeclaration styleDecl;

    @Override
    public IUiElementStyleDeclaration getStyle() {
        return styleDecl;
    }

    public void setStyle(IUiElementStyleDeclaration styleDecl) {
        this.styleDecl = styleDecl;
    }

}
