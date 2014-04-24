package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHttpReprContext;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class URLResourceVbo
        extends AbstractHtmlViewBuilder<URLResource> {

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
    public IHttpReprContext buildHtmlView(IHttpReprContext ctx, IRefEntry<URLResource> entry, IOptions options)
            throws ViewBuilderException, IOException {
        HttpServletResponse response = ctx.getResponse();

        URLResource resource = entry.get();

        URL url = resource.getURL();
        Long length = FileURL.length(url, null);
        if (length == null)
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        if (length <= Integer.MAX_VALUE)
            response.setContentLength(length.intValue());

        IByteOut byteOut = ctx.getByteOut();
        for (byte[] block : resource.to(StreamReading.class).blocks())
            byteOut.write(block);
        byteOut.flush();

        return ctx;
    }

}
