package net.bodz.bas.gui.dom1;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.i18n.dom1.MutableElement;

public class MutableGUIElement
        extends MutableElement
        implements IGUIElement {

    private IGUIElementStyleDeclaration styleDecl;

    @Override
    public IGUIElementStyleDeclaration getStyle() {
        return styleDecl;
    }

    public void setStyle(IGUIElementStyleDeclaration styleDecl) {
        this.styleDecl = styleDecl;
    }

}
