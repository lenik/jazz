package net.bodz.bas.html.servlet;

import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.pdf.viz.IPdfViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderSet;
import net.bodz.bas.repr.viz.web.IHttpViewBuilder;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class ContentFamily {

    public static <T> IHttpViewBuilder<T> findFirstFor(ViewBuilderSet<T> viewBuilderList, ContentType contentType) {
        if (contentType == ContentTypes.text_html //
                || contentType == ContentTypes.text_xhtml) {
            IHtmlViewBuilder<T> builder = viewBuilderList.findFirst(IHtmlViewBuilder.class);
            if (builder != null)
                return builder;
        }

        if (contentType == ContentTypes.application_pdf) {
            IPdfViewBuilder<T> builder = viewBuilderList.findFirst(IPdfViewBuilder.class);
            if (builder != null)
                return builder;
        }

        // matching content-type?
        IHttpViewBuilder<T> builder = viewBuilderList.findFirst(IHttpViewBuilder.class);
        return builder;
    }

}
