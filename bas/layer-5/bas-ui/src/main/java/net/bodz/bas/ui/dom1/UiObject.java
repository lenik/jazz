package net.bodz.bas.ui.dom1;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;
import net.bodz.bas.ui.style.MutableUiStyleDeclaration;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

public class UiObject
        extends XjdocObject
        implements IUiElement {

    @Override
    public IUiElementStyleDeclaration getStyle() {
        IElementDoc xjdoc = getXjdoc();
        String styleCss = (String) xjdoc.getTag("style");

        MutableUiStyleDeclaration decl = new MutableUiStyleDeclaration();
        try {
            decl.parseCss(styleCss);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return decl;
    }

}
