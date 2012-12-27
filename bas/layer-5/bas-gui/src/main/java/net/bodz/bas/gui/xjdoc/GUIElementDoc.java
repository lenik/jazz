package net.bodz.bas.gui.xjdoc;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.style.GUIStyleClass;
import net.bodz.bas.gui.style.IGUIStyleClass;
import net.bodz.mda.xjdoc.model.DecoratedJavaElementDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class GUIElementDoc
        extends DecoratedJavaElementDoc {

    private static final long serialVersionUID = 1L;

    private transient IGUIStyleClass styleClass;

    public GUIElementDoc(IJavaElementDoc _orig) {
        super(_orig);
    }

    public GUIElementDoc(DecoratedJavaElementDoc _orig) {
        super(_orig.getWrapped());
    }

    public synchronized IGUIStyleClass getStyleClass() {
        if (styleClass == null) {
            GUIStyleClass style = new GUIStyleClass(null);
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
