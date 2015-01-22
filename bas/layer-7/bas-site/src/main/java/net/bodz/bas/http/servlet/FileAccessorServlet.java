package net.bodz.bas.http.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.http.HttpServlet;
import net.bodz.bas.http.ResourceTransferer;
import net.bodz.bas.t.iterator.Iterables;

public class FileAccessorServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_PATH = "start-path";
    public static final String ATTRIBUTE_MAX_AGE = "max-age";

    /**
     * The target path, without traling slash.
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

        for (String name : Iterables.otp(config.getInitParameterNames())) {
            String param = config.getInitParameter(name);
            switch (name) {
            case ATTRIBUTE_PATH:
                startPath = param;
                break;

            case ATTRIBUTE_MAX_AGE:
                maxAge = Integer.parseInt(param);
                break;
            }
        }

        if (startPath == null)
            throw new IllegalConfigException(ATTRIBUTE_PATH + " isn't set.");
        startPath = FilePath.removeTrailingSlashes(startPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = this.startPath;
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

        URL url = file.toURI().toURL();

        ResourceTransferer transferer = new ResourceTransferer(req, resp);
        transferer.setMaxAge(maxAge);
        transferer.transfer(url);
    }

}
