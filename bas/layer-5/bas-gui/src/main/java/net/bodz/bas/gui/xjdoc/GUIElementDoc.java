package net.bodz.bas.gui.xjdoc;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.style.GUIElementStyleClass;
import net.bodz.bas.gui.style.IGUIElementStyleClass;
import net.bodz.mda.xjdoc.model.DecoratedJavaElementDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class GUIElementDoc
        extends DecoratedJavaElementDoc {

    private static final long serialVersionUID = 1L;

    private transient IGUIElementStyleClass styleClass;

    public GUIElementDoc(IJavaElementDoc _orig) {
        super(_orig);
    }

    public GUIElementDoc(DecoratedJavaElementDoc _orig) {
        super(_orig.getWrapped());
    }

    public synchronized IGUIElementStyleClass getStyleClass() {
        if (styleClass == null) {
            GUIElementStyleClass style = new GUIElementStyleClass(null);
            String script = (String) getTag("style");
            if (script != null)
                try {
                    style.parse(script);
                } catch (ParseException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
        }
        return styleClass;
    }

}
