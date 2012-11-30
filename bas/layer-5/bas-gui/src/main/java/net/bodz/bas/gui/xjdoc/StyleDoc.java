package net.bodz.bas.gui.xjdoc;

import net.bodz.bas.gui.css3.RootCss3StyleClass;
import net.bodz.bas.gui.css3.ICss3Properties;
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

    public ICss3Properties getStyle() {
        ICss3Properties style = new RootCss3StyleClass();

    }

}
