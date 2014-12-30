package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.ResourceTransferer;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class URLResourceVbo
        extends AbstractHtmlViewBuilder<URLResource> {

    /**
     * 1 day by default.
     */
    private int maxAge = 86400;

    public URLResourceVbo() {
        super(IStreamResource.class);
    }

    @Override
    public ContentType getContentType(URLResource value) {
        URL url = value.getURL();
        String fileName = url.getFile();
        String extension = FilePath.getExtension(fileName);
        ContentType contentType = ContentType.forExtension(extension);
        if (contentType == null)
            contentType = ContentTypes.application_octet_stream;
        return contentType;
    }

    @Override
    public boolean isOrigin(URLResource value) {
        return true;
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<URLResource> ref, IOptions options)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = ctx.getRequest();
        HttpServletResponse resp = ctx.getResponse();
        URLResource resource = ref.get();

        ResourceTransferer transferer = new ResourceTransferer(req, resp);
        transferer.setMaxAge(maxAge);
        transferer.transfer(resource);
        return null;
    }
}
