package net.bodz.bas.pdf.viz;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.http.viz.IHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public interface IPdfViewBuilder<T>
        extends IHttpViewBuilder<T> {

    IHtmlTag buildPdfView(IHttpViewContext ctx, IHtmlTag out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    IHtmlTag buildPdfView(IHttpViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException;

}
