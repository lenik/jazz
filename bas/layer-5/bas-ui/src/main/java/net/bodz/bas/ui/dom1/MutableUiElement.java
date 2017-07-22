package net.bodz.bas.ui.dom1;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;
import net.bodz.bas.ui.style.UiElementStyleDeclaration;
import net.bodz.mda.xjdoc.model.IElementDoc;

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

    public void populate(IElementDoc doc)
            throws ParseException {
        String css = (String) doc.getTag("style");
        if (css != null) {
            UiElementStyleDeclaration styleDecl = new UiElementStyleDeclaration(null);
            styleDecl.parseCss(css);
            this.setStyle(styleDecl); // TODO merge?
        }
    }

}
