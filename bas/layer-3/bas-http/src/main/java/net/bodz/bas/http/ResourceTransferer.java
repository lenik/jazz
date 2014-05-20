package net.bodz.bas.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.std.rfc.http.ContentRange;
import net.bodz.bas.std.rfc.mime.ContentType;

public class ResourceTransferer {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private int maxAge = 86400;

    public ResourceTransferer(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public void transfer(URLResource resource)
            throws IOException {
        URL url = resource.getURL();
        String path = url.getFile();
        // if (!resource.canRead()) {
        // resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
        // return;
        // }

        resp.setHeader("Cache-Control", "max-age=" + maxAge);

        long lastModified = resource.lastModified();
        if (lastModified > 0) {
            long ifModifiedSince = req.getDateHeader("If-Modified-Since");
            if (ifModifiedSince > 0 && ifModifiedSince >= lastModified) {
                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }
            resp.setDateHeader("Last-Modified", lastModified);
            resp.setDateHeader("Expires", lastModified + maxAge * 1000L);
        }
        // resp.setHeader("E-Tag", eTag);

        ContentType contentType = ContentType.forPath(path);
        if (contentType != null)
            resp.setContentType(contentType.getName());

        Long length = FileURL.length(resource.getURL(), null);
        if (length != null)
            if (length <= Integer.MAX_VALUE)
                resp.setContentLength(length.intValue());
            else
                resp.setHeader("Content-Length", String.valueOf(length));

        ContentRange contentRange = null;
        String contentRangeHeader = req.getHeader("Content-Range");
        if (contentRangeHeader != null) {
            if (length == null) {
                resp.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, //
                        "Length is unknown.");
                return;
            }
            contentRange = ContentRange.parseAndSet(length, contentRangeHeader, resp);
            if (contentRange == null)
                return;
        }

        long start = 0;
        long remaining = -1;
        if (length != null) {
            long end = length;
            if (contentRange != null)
                end = contentRange.end;
            remaining = end - start;
        }

        ServletOutputStream out = resp.getOutputStream();
        InputStream in = resource.newInputStream();
        try {
            byte[] block = new byte[4096];
            while (true) {
                int cb = block.length;
                if (remaining != -1 && remaining < cb)
                    cb = (int) remaining;

                if ((cb = in.read(block, 0, cb)) == -1)
                    break;
                out.write(block, 0, cb);

                if (remaining != -1)
                    remaining -= cb;
            }
        } finally {
            in.close();
            out.close();
        }
    }

}
