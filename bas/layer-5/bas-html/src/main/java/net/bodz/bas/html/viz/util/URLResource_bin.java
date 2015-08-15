package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.http.ResourceTransferer;
import net.bodz.bas.http.viz.AbstractHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class URLResource_bin
        extends AbstractHttpViewBuilder<URLResource> {

    /**
     * 1 hour by default.
     */
    private int maxAge = 3600 * 1;

    public URLResource_bin() {
        super(IStreamResource.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, URLResource value) {
        URL url = value.getURL();
        String fileName = url.getFile();
        String extension = FilePath.getExtension(fileName);
        ContentType contentType = ContentType.forExtension(extension);
        if (contentType == null)
            contentType = ContentTypes.application_octet_stream;
        return contentType;
    }

    @Override
    public void buildHttpView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<URLResource> ref, IOptions options)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = ctx.getRequest();
        URLResource resource = ref.get();
        URL url = resource.getURL();

        ResourceTransferer transferer = new ResourceTransferer(req, resp);
        transferer.setMaxAge(maxAge);
        transferer.transfer(url);
    }

}
