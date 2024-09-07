package net.bodz.bas.servlet.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.std.rfc.http.ICacheControl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClassResourceAccessorServlet
        extends AbstractFileAccessServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_PATH = "start-path";

    /**
     * The prefix, should start with '/', but without trailing slash.
     */
    private String startPath;

    @Override
    public void _init()
            throws ServletException {
        if (startPath == null)
            throw new IllegalConfigException(ATTRIBUTE_PATH + " isn't set.");
    }

    @Override
    protected void setInitParameter(String name, String value)
            throws ServletException {
        super.setInitParameter(name, value);
        if (ATTRIBUTE_PATH.equals(name))
            startPath = FilePath.removeTrailingSlashes(value);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = this.startPath;
        if (pathInfo != null)
            path += pathInfo;

        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(path);
        if (url == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ResourceTransferer transferer = new ResourceTransferer(req, resp);
        ICacheControl cacheControl = getCacheControl(req, url);
        transferer.transfer(url, cacheControl);
    }

    protected ClassLoader getClassLoader() {
        ClassLoader classLoader = ClassLoaders.getRuntimeClassLoader();
        return classLoader;
    }

    @Override
    protected File getLocalRoot(HttpServletRequest req) {
        return null;
    }

    @Override
    public ICacheControl getCacheControl(HttpServletRequest req, URL url) {
        return super.getCacheControl(req, url);
    }

}
