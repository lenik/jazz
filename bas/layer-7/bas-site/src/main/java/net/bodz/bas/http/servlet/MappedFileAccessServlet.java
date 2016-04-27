package net.bodz.bas.http.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.http.HttpServlet;
import net.bodz.bas.http.ResourceTransferer;
import net.bodz.bas.site.file.IFilePathMapping;
import net.bodz.bas.t.iterator.Iterables;

public class MappedFileAccessServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_MAPPING_CLASS = "mapping-class";
    public static final String ATTRIBUTE_START_PATH = "start-path";
    public static final String ATTRIBUTE_MAX_AGE = "max-age";

    private IFilePathMapping mapping;

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
            case ATTRIBUTE_MAPPING_CLASS:
                try {
                    Class<?> mappingClass = Class.forName(param);
                    mapping = (IFilePathMapping) mappingClass.newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new ServletException("Can't create mapping: " + e.getMessage(), e);
                }
                break;

            case ATTRIBUTE_MAX_AGE:
                maxAge = Integer.parseInt(param);
                break;
            }
        }

        if (mapping == null)
            throw new IllegalConfigException(ATTRIBUTE_MAPPING_CLASS + " isn't set.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = mapping.getLocalRoot(req).getPath();
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
