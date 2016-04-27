package net.bodz.bas.http.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.http.HttpServlet;
import net.bodz.bas.http.ResourceTransferer;
import net.bodz.bas.t.iterator.Iterables;

public abstract class AbstractFileAccessServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_MAX_AGE = "max-age";

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
            setParameter(name, param);
        }
    }

    protected void setParameter(String name, String value) {
        switch (name) {
        case ATTRIBUTE_MAX_AGE:
            maxAge = Integer.parseInt(value);
            break;
        }
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

    protected abstract File getLocalRoot(HttpServletRequest req);

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

}
