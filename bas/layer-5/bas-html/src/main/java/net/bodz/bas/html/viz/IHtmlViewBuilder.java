package net.bodz.bas.html.viz;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.http.viz.IHttpViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;

public interface IHtmlViewBuilder<T>
        extends IHttpViewBuilder<T> {

    void buildHtmlViewStart(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    void buildHtmlViewEnd(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

}
