package net.bodz.bas.pdf.viz;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.http.viz.IHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public interface IPdfViewBuilder<T>
        extends IHttpViewBuilder<T> {

    void buildPdfView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    void buildPdfView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException;

}
