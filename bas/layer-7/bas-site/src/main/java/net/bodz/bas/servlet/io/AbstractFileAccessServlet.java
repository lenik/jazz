package net.bodz.bas.servlet.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.bas.html.viz.util.StreamContentBlob;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.content.MutableContent;
import net.bodz.bas.servlet.HttpServlet;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.iterator.Iterables;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractFileAccessServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(AbstractFileAccessServlet.class);

    public static final String ATTRIBUTE_MAX_AGE = "max-age";

    /**
     * 1 day by default.
     */
    int maxAge = 86400;

    @Override
    public final void init()
            throws ServletException {
        ServletConfig config = getServletConfig();

        for (String name : Iterables.otp(config.getInitParameterNames())) {
            String param = config.getInitParameter(name);
            setInitParameter(name, param);
        }

        _init();
    }

    protected void _init()
            throws ServletException {
    }

    protected void setInitParameter(String name, String value)
            throws ServletException {
        if (ATTRIBUTE_MAX_AGE.equals(name))
            maxAge = Integer.parseInt(value);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        File localRoot = getLocalRoot(req);

        File file = localRoot;
        if (pathInfo != null) {
            if (pathInfo.startsWith("/"))
                pathInfo = pathInfo.substring(1);
            file = new File(file, pathInfo);
        }

        if (file == null)
            throw new NullPointerException("file");

        if (!file.exists()) {
            logger.warn("Not-Found: " + file);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (!file.canRead()) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
            return;
        }

        new ResourceTransferer()//
                .request(req, resp)//
                .file(file)//
                .maxAge(maxAge)//
                .transfer();
    }

    protected abstract File getLocalRoot(HttpServletRequest req);

    public final int getMaxAge() {
        return maxAge;
    }

    public final void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

}
