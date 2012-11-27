package net.bodz.bas.gui.xjdoc;

import net.bodz.mda.xjdoc.model.DecoratedJavaElementDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class StyleDoc
        extends DecoratedJavaElementDoc {

    private static final long serialVersionUID = 1L;

    public StyleDoc(IJavaElementDoc _orig) {
        super(_orig);
    }

    public StyleDoc(DecoratedJavaElementDoc _orig) {
        super(_orig.getWrapped());
    }

    public StyleData getStyle() {
        StyleData style = new StyleData();

    }

}
