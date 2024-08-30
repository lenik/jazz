package net.bodz.bas.ui.xjdoc;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;
import net.bodz.bas.ui.style.UiElementStyleDeclaration;
import net.bodz.mda.xjdoc.model.DecoratedElementDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class UiElementDoc
        extends DecoratedElementDoc {

    private static final long serialVersionUID = 1L;

    private transient IUiElementStyleDeclaration styleClass;

    public UiElementDoc(IElementDoc _orig) {
        super(_orig);
    }

    public UiElementDoc(DecoratedElementDoc _orig) {
        super(_orig.getWrapped());
    }

    public synchronized IUiElementStyleDeclaration getStyleClass() {
        if (styleClass == null) {
            UiElementStyleDeclaration style = new UiElementStyleDeclaration(null);
            String script = getTag("style").getString();
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
