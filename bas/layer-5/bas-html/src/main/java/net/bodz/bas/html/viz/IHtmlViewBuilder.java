package net.bodz.bas.html.viz;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.IHttpViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;

public interface IHtmlViewBuilder<T>
        extends IHttpViewBuilder<T> {

    IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    void buildHtmlViewEnd(IHtmlViewContext ctx, IHtmlOut out, IHtmlOut body, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

}
