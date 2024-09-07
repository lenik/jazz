package net.bodz.bas.html.viz;

import net.bodz.bas.pdf.viz.IPdfViewBuilder;
import net.bodz.bas.repr.req.IViewOfRequest;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.repr.viz.web.HttpViewBuilderFamily;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

import jakarta.servlet.http.HttpServletRequest;

public abstract class AbstractPdfViewBuilder<T>
        extends AbstractHttpViewBuilder<T>
        implements IPdfViewBuilder<T> {

    public AbstractPdfViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public HttpViewBuilderFamily getFamily() {
        return HttpViewBuilderFamily.PDF;
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, T value) {
        IViewOfRequest view = (IViewOfRequest) request.getAttribute(IViewOfRequest.class.getName());
        if (view != null)
            return view.getContentType();
        else
            return ContentTypes.application_pdf;
    }

}
