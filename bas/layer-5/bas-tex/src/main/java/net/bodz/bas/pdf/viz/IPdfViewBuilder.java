package net.bodz.bas.pdf.viz;

import java.io.IOException;

import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.IHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.ui.dom1.IUiRef;

import jakarta.servlet.http.HttpServletResponse;

public interface IPdfViewBuilder<T>
        extends IHttpViewBuilder<T> {

    void buildPdfView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

}
