package net.bodz.bas.gui.xjdoc;

import net.bodz.bas.gui.css3.Css3StyleAttributes;
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

    public Css3StyleAttributes getStyle() {
        Css3StyleAttributes style = new Css3StyleAttributes();

    }

}
