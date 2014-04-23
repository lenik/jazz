package net.bodz.bas.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.std.rfc.mime.ContentType;

public class FileAccessorServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_PATH = "path";

    /**
     * The target path.
     */
    private String path;

    /**
     * 1 day by default.
     */
    private int maxAge = 86400;

    @Override
    public void init()
            throws ServletException {
        ServletConfig config = getServletConfig();
        path = config.getInitParameter(ATTRIBUTE_PATH);
        if (path == null)
            throw new NullPointerException(ATTRIBUTE_PATH);
        path = FilePath.removeTrailingSlashes(path);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = this.path;
        if (pathInfo != null)
            path += pathInfo;

        File file = new File(path);
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (!file.canRead()) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
            return;
        }

        ContentType contentType = ContentType.forPath(file.getName());
        if (contentType != null)
            resp.setContentType(contentType.getName());

        long length = file.length();
        if (length <= Integer.MAX_VALUE)
            resp.setContentLength((int) length);
        else
            resp.setHeader("Content-Length", String.valueOf(length));

        resp.setHeader("Cache-Control", "max-age=" + maxAge);
        resp.setDateHeader("Last-Modified", file.lastModified());
        resp.setDateHeader("Expires", file.lastModified() + maxAge * 1000L);
        // resp.setHeader("E-Tag", eTag);

        ContentRange contentRange = null;
        String contentRangeHeader = req.getHeader("Content-Range");
        if (contentRangeHeader != null) {
            contentRange = new ContentRange();
            String error = contentRange.parse(contentRangeHeader);
            if (error != null) {
                resp.setHeader("Content-Range", "*/" + length);
                resp.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, //
                        error + ": " + contentRangeHeader);
                return;
            } else if (contentRange.end >= length) {
                resp.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, //
                        "out of length");
                return;
            } else {
                resp.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            }
        }

        long start = 0;
        long end = file.length();
        if (contentRange != null)
            end = contentRange.end;
        long remaining = end - start;

        ServletOutputStream out = resp.getOutputStream();
        RandomAccessFile raf = new RandomAccessFile(path, "r");
        raf.seek(start);

        byte[] block = new byte[4096];
        while (true) {
            int cb = block.length;
            if (remaining < cb)
                cb = (int) remaining;
            if ((cb = raf.read(block, 0, cb)) == -1)
                break;
            out.write(block, 0, cb);
            remaining -= cb;
        }
        raf.close();
        out.close();
    }

}
