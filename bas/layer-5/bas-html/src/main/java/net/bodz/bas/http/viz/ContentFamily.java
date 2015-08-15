package net.bodz.bas.http.viz;

import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.pdf.viz.IPdfViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderSet;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class ContentFamily {

    public static <T> IHttpViewBuilder<T> findFirstFor(ViewBuilderSet<T> viewBuilderList, ContentType contentType) {
        if (contentType == ContentTypes.text_html //
                || contentType == ContentTypes.text_xhtml)
            return viewBuilderList.findFirst(IHtmlViewBuilder.class);

        if (contentType == ContentTypes.application_pdf)
            return viewBuilderList.findFirst(IPdfViewBuilder.class);

        return viewBuilderList.findFirst(IHttpViewBuilder.class);
    }

}
