package net.bodz.bas.pdf.viz;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractPdfViewBuilder<T>
        extends AbstractHttpViewBuilder<T>
        implements IPdfViewBuilder<T> {

    public AbstractPdfViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public int getPriority() {
        return Priority.LOW;
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, T value) {
        return ContentTypes.application_pdf;
    }

    @Override
    public final Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        buildPdfView(ctx, resp, ref);
        return null;
    }

}
