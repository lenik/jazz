package net.bodz.bas.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.time.OffsetDateTime;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FileDate;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.std.rfc.http.ContentDisposition;
import net.bodz.bas.std.rfc.http.ContentRange;
import net.bodz.bas.std.rfc.http.MutableCacheControl;
import net.bodz.bas.std.rfc.mime.ContentType;

public class ResourceTransferer {

    static final Logger logger = LoggerFactory.getLogger(net.bodz.bas.servlet.ResourceTransferer.class);

    HttpServletRequest req;
    HttpServletResponse resp;
    boolean asAttachment;

    String filename;
    String description;
    MutableCacheControl cacheControl = new MutableCacheControl();
    Integer maxAge;

    IBlob blob;
    boolean canRead = true;

    public ResourceTransferer() {
        cacheControl.setMaxAge(maxAge);
    }

    public ResourceTransferer request(IHttpViewContext context) {
        this.request(context.getRequest());
        this.response((context.getResponse()));
        return this;
    }

    public ResourceTransferer request(HttpServletRequest req, HttpServletResponse resp) {
        this.request(req);
        this.response(resp);
        return this;
    }

    public ResourceTransferer request(HttpServletRequest req) {
        this.req = req;

        String mode = req.getParameter("mode");
        if (mode != null)
            switch (mode) {
                case "attachment":
                    this.asAttachment = true;
                    break;
            }

        return this;
    }

    public ResourceTransferer response(HttpServletResponse resp) {
        this.resp = resp;
        return this;
    }

    public boolean isAsAttachment() {
        return asAttachment;
    }

    public void setAsAttachment(boolean asAttachment) {
        this.asAttachment = asAttachment;
    }

    public ResourceTransferer asAttachment() {
        this.asAttachment = true;
        return this;
    }

    public ResourceTransferer url(URL url) {
        blob = new URLBlob(url);
        filename = getFilenameFromUrl(url);

        if ("file".equals(url.getProtocol())) {
            File file = FileURL.toFile(url, null);
            OffsetDateTime lastModified = FileDate.getLastModified(file).toOffsetDateTime();
            cacheControl.setLastModified(lastModified);
        }

        return this;
    }

    static String getFilenameFromUrl(URL url) {
        if (url != null) {
            File file = FileURL.toFile(url, null);
            if (file != null)
                return file.getName();
        }
        return null;
    }

    public ResourceTransferer path(Path path)
            throws IOException {
        blob = new PathBlob(path);

        OffsetDateTime lastModified = FileDate.getLastModified(path).toOffsetDateTime();
        cacheControl.setLastModified(lastModified);

        return this;
    }

    public ResourceTransferer file(File file) {
        blob = new FileBlob(file);

        OffsetDateTime lastModified = FileDate.getLastModified(file).toOffsetDateTime();
        cacheControl.setLastModified(lastModified);

        return this;
    }

    public ResourceTransferer inputSource(IStreamInputSource inputSource) {
        blob = new StreamBlob(inputSource);
        canRead = inputSource.canRead();

        OffsetDateTime lastModified = inputSource.getLastModified();
        if (lastModified != null)
            cacheControl.setLastModified(lastModified);

        return this;
    }

    public ResourceTransferer blob(IBlob blob)
            throws IOException {
        this.blob = blob;

        OffsetDateTime lastModified = blob.getLastModified();
        if (lastModified != null)
            cacheControl.setLastModified(lastModified);

        return this;
    }

    public ResourceTransferer filename(String filename) {
        this.filename = filename;
        return this;
    }

    public ResourceTransferer description(String description) {
        this.description = description;
        return this;
    }

    public ResourceTransferer maxAge(int maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public IBlob buildBlob() {
        if (blob == null)
            throw new IllegalUsageException();
        return blob;
    }

    public void transfer()
            throws IOException {
        if (!canRead) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
            return;
        }

        IBlob blob = buildBlob();
        resp.setHeader("Local-Resource", blob.getLocation());
        String filename = Nullables.coalesce(this.filename, blob.getFilename());
        String description = Nullables.coalesce(this.description, blob.getDescription());
        int maxAge = this.maxAge != null ? this.maxAge : cacheControl.getMaxAge();

        ContentType contentType = blob.getContentType();
        if (contentType == null)
            contentType = ContentType.forPath(blob.getPath());
        if (contentType != null)
            resp.setContentType(contentType.getName());

        Charset encoding = blob.getCharset();
        if (encoding != null)
            resp.setCharacterEncoding(encoding.name());

        Long length = blob.getLength();
        if (length != null)
            if (length <= Integer.MAX_VALUE)
                resp.setContentLength(length.intValue());
            else
                resp.setHeader("Content-Length", String.valueOf(length));

        /*
         * @see RFC 2183 2.10
         */
        if (asAttachment) {
            String contentDisposition = ContentDisposition.format(filename, true, false);

            if (contentDisposition != null) {
                resp.setHeader("Content-Disposition", contentDisposition);

                if (description != null)
                    resp.setHeader("Content-Description", description);
            }
        }

        resp.setHeader("Cache-Control", "max-age=" + maxAge);
        // resp.setHeader("Cache-Control", "must-revalidate");
        // resp.setHeader("Cache-Control", "no-cache");
        // resp.setHeader("Cache-Control", "no-store");

        OffsetDateTime lastModified = blob.getLastModified();
        if (lastModified != null) {
            long lastModifiedTime = lastModified.toInstant().toEpochMilli();
            long ifModifiedSince = req.getDateHeader("If-Modified-Since");
            if (ifModifiedSince > 0 && ifModifiedSince >= lastModifiedTime) {
                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }

            /*
             * @see RFC 2616 14.29
             *      <p>
             *      HTTP/1.1 servers SHOULD send Last-Modified whenever feasible
             */
            resp.setDateHeader("Last-Modified", lastModified.toInstant().toEpochMilli());

            /*
             * @see RFC 2616 14.21
             *      <p>
             *      To mark a response as "never expires," an origin server sends an Expires date approximately one year
             *      from the time the response is sent. HTTP/1.1 servers SHOULD NOT send Expires dates more than one
             *      year in the future.
             */
            resp.setDateHeader("Expires", System.currentTimeMillis() + maxAge * 1000L);
        }

        /*
         * @see RFC 2616 13.3.2
         *      <p>
         *      The ETag response-header field value, an entity tag, provides for an "opaque" cache validator. This
         *      might allow more reliable validation in situations where it is inconvenient to store modification dates,
         *      where the one-second resolution of HTTP date values is not sufficient, or where the origin server wishes
         *      to avoid certain paradoxes that might arise from the use of modification dates.
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
        InputStream in = blob.openStream();

        try {
            long skipped = in.skip(start);
            if (skipped != start)
                return;

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
                    logger.errorf("Failed to transfer %s: %s: %s", blob.getLocation(), e.getClass(), e.getMessage());
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
                    // ignore
                }
            if (out != null)
                try {
                    out.flush();
                } catch (IOException e) {
                    logger.errorf("Failed to flush out, when transfer %s: %s: %s", blob.getLocation(), e.getClass(), e.getMessage());
                }
        }
    }

}
