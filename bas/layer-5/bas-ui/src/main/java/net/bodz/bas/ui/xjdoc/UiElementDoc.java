package net.bodz.bas.ui.xjdoc;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;
import net.bodz.bas.ui.style.UiElementStyleDeclaration;
import net.bodz.mda.xjdoc.model.DecoratedJavaElementDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class UiElementDoc
        extends DecoratedJavaElementDoc {

    private static final long serialVersionUID = 1L;

    private transient IUiElementStyleDeclaration styleClass;

    public UiElementDoc(IElementDoc _orig) {
        super(_orig);
    }

    public UiElementDoc(DecoratedJavaElementDoc _orig) {
        super(_orig.getWrapped());
    }

    public synchronized IUiElementStyleDeclaration getStyleClass() {
        if (styleClass == null) {
            UiElementStyleDeclaration style = new UiElementStyleDeclaration(null);
            String script = (String) getTag("style");
            if (script != null)
                try {
                    style.parseCss(script);
                } catch (ParseException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
        }
        return styleClass;
    }

}
