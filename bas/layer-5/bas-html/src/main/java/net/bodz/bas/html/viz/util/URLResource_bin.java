package net.bodz.bas.html.viz.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;

import net.bodz.bas.c.java.io.FileDate;
import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.repr.content.MutableContent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<URLResource> ref)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = ctx.getRequest();
        URLResource resource = ref.get();
        URL url = resource.getURL();

        ResourceTransferer transferer = new ResourceTransferer(req, resp);
        ICacheControl cacheControl = getCacheControl(req, url);
        transferer.transfer(url, cacheControl);
        return null;
    }

    public ICacheControl getCacheControl(HttpServletRequest req, URL url) {
        MutableContent content = new MutableContent();
        content.setMaxAge(maxAge);
        if ("file".equals(url.getProtocol())) {
            File file = FileURL.toFile(url, null);
            ZonedDateTime lastModified = FileDate.getLastModified(file);
            content.setLastModified(lastModified);

            ZonedDateTime creationDate;
            try {
                creationDate = FileDate.getCreationDate(file.toPath());
            } catch (IOException e) {
                creationDate = lastModified;
            }
            content.setCreationDate(creationDate);
        }
        return content;
    }

}
