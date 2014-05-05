package net.bodz.bas.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.std.rfc.mime.ContentType;

public class ClassResourceAccessorServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_PATH = "path";

    /**
     * The prefix, should start with '/', but without trailing slash.
     */
    private String startPath;

    /**
     * 1 day by default.
     */
    private int maxAge = 86400;

    @Override
    public void init()
            throws ServletException {
        ServletConfig config = getServletConfig();
        startPath = config.getInitParameter(ATTRIBUTE_PATH);
        if (startPath == null)
            throw new NullPointerException(ATTRIBUTE_PATH);
        startPath = FilePath.removeTrailingSlashes(startPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = this.startPath;
        if (pathInfo != null)
            path += pathInfo;

        ClassLoader classLoader = getClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // if (!resource.canRead()) {
        // resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
        // return;
        // }

        ContentType contentType = ContentType.forPath(path);
        if (contentType != null)
            resp.setContentType(contentType.getName());

        Long length = FileURL.length(resource, null);
        if (length != null)
            if (length <= Integer.MAX_VALUE)
                resp.setContentLength(length.intValue());
            else
                resp.setHeader("Content-Length", String.valueOf(length));

        resp.setHeader("Cache-Control", "max-age=" + maxAge);
        // resp.setDateHeader("Last-Modified", file.lastModified());
        // resp.setDateHeader("Expires", file.lastModified() + maxAge * 1000L);
        // resp.setHeader("E-Tag", eTag);

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
        InputStream in = resource.openStream();
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

    protected ClassLoader getClassLoader() {
        ClassLoader classLoader = ClassLoaders.getRuntimeClassLoader();
        return classLoader;
    }

}
