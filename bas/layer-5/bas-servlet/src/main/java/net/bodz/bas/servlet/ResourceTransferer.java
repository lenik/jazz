package net.bodz.bas.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.std.rfc.http.ContentDisposition;
import net.bodz.bas.std.rfc.http.ContentRange;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.std.rfc.mime.ContentType;

public class ResourceTransferer {

    static final Logger logger = LoggerFactory.getLogger(ResourceTransferer.class);

    private HttpServletRequest req;
    private HttpServletResponse resp;

    public ResourceTransferer(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public void transfer(URL url, ICacheControl cacheControl)
            throws IOException {
        String filename = null;
        String description = null;
        String mode = req.getParameter("mode");
        if (mode != null)
            if ("attachment".equals(mode)) {
                File file = FileURL.toFile(url, null);
                if (file != null)
                    filename = file.getName();
            }

        transfer(url, filename, description, cacheControl);
    }

    public void transfer(URL url, String filename, String description, ICacheControl cacheControl)
            throws IOException {
        // if (!resource.canRead()) {
        // resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
        // return;
        // }
        resp.setHeader("Local-Resource", url.toString());

        ContentType contentType = ContentType.forPath(url.getPath());
        if (contentType != null)
            resp.setContentType(contentType.getName());

        Long length = FileURL.length(url, null);
        if (length != null)
            if (length <= Integer.MAX_VALUE)
                resp.setContentLength(length.intValue());
            else
                resp.setHeader("Content-Length", String.valueOf(length));

        /**
         * @see RFC 2183 2.10
         */
        if (filename != null) {
            // String filename = FilePath.getBaseName(path);
            String contentDisposition = ContentDisposition.format(filename, true, false);

            if (contentDisposition != null) {
                resp.setHeader("Content-Disposition", contentDisposition);

                if (description != null)
                    resp.setHeader("Content-Description", description);
            }
        }

        int maxAge = cacheControl.getMaxAge();
        resp.setHeader("Cache-Control", "max-age=" + maxAge);
        // resp.setHeader("Cache-Control", "must-revalidate");
        // resp.setHeader("Cache-Control", "no-cache");
        // resp.setHeader("Cache-Control", "no-store");

        Long lastModified = FileURL.lastModified(url, null);
        if (lastModified != null) {
            long ifModifiedSince = req.getDateHeader("If-Modified-Since");
            if (ifModifiedSince > 0 && ifModifiedSince >= lastModified) {
                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }

            /**
             * @see RFC 2616 14.29
             *      <p>
             *      HTTP/1.1 servers SHOULD send Last-Modified whenever feasible
             */
            resp.setDateHeader("Last-Modified", lastModified);

            /**
             * @see RFC 2616 14.21
             *      <p>
             *      To mark a response as "never expires," an origin server sends an Expires date
             *      approximately one year from the time the response is sent. HTTP/1.1 servers
             *      SHOULD NOT send Expires dates more than one year in the future.
             */
            resp.setDateHeader("Expires", System.currentTimeMillis() + maxAge * 1000L);
        }

        /**
         * @see RFC 2616 13.3.2
         *      <p>
         *      The ETag response-header field value, an entity tag, provides for an "opaque" cache
         *      validator. This might allow more reliable validation in situations where it is
         *      inconvenient to store modification dates, where the one-second resolution of HTTP
         *      date values is not sufficient, or where the origin server wishes to avoid certain
         *      paradoxes that might arise from the use of modification dates.
         */
        String eTag = null;
        // if (eTag != null) resp.setHeader("E-Tag", eTag);

        ContentRange range = null;
        String rangeHeader = req.getHeader("Range");
        if (rangeHeader != null) {
            if (length == null) {
                resp.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, //
                        "Length is unknown.");
                return;
            }
            range = ContentRange.parseAndSet(length, rangeHeader, resp);
            if (range == null)
                return;
        }

        long start = 0;
        Long end = length;
        if (range != null) {
            start = range.start;
            end = range.end;
        }
        long remaining = end == null ? -1 : (end - start);
        long transferred = 0;

        ServletOutputStream out = resp.getOutputStream();
        InputStream in = url.openStream();

        try {
            in.skip(start);

            byte[] block = new byte[4096];
            while (remaining != 0) {
                int cb = block.length;
                if (remaining != -1 && remaining < cb)
                    cb = (int) remaining;

                if ((cb = in.read(block, 0, cb)) == -1)
                    break;

                // PATCH out closed when 304 Not Modified.
                // BufferedInputStream may return 0 for EOF.
                if (cb == 0)
                    break;

                try {
                    out.write(block, 0, cb);
                } catch (IOException e) {
                    // connection reset is highly possible.
                    logger.errorf("Failed to transfer %s: %s: %s", url, e.getClass(), e.getMessage());
                    break;
                }

                if (remaining != -1)
                    remaining -= cb;
                transferred += cb;
            }
        } finally {
            logger.debug("Transferred: " + transferred + " for chunk " + range);
            if (range != null)
                logger.debug("    expected size " + (range.end - range.start));
            if (in != null)
                try {
                    in.close();
                } catch (Exception e) {
                }
            if (out != null)
                try {
                    out.flush();
                } catch (IOException e) {
                    logger.errorf("Failed to flush out, when transfer %s: %s: %s", url, e.getClass(), e.getMessage());
                }
        }
    }

}
