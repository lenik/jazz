package net.bodz.bas.gui.xjdoc;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.spec0.GUIStyleClass;
import net.bodz.bas.gui.spec0.IGUIStyleClass;
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

    public IGUIStyleClass getStyle() {
        GUIStyleClass style = new GUIStyleClass(null);
        String script = (String) getTag("style");
        if (script != null)
            try {
                style.parse(script);
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        return style;
    }

}
