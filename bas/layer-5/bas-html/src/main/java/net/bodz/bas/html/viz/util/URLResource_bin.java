package net.bodz.bas.html.viz.util;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class URLResource_bin
        extends AbstractHttpViewBuilder<IStreamResource> {

    /**
     * 1 hour by default.
     */
    int maxAge = 3600;

    public URLResource_bin() {
        super(IStreamResource.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, IStreamResource value) {
        String extension = FilePath.extensionOfPath(value.getName());
        ContentType contentType = ContentType.forExtension(extension, //
                ContentTypes.application_octet_stream);
        return contentType;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<IStreamResource> ref)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = ctx.getRequest();
        IStreamResource resource = ref.get();

        new ResourceTransferer()//
                .request(req, resp)//
                .inputSource(resource)//
                .maxAge(maxAge)//
                .transfer();

        return null;
    }

}
