package net.bodz.bas.http.ctx;

import javax.servlet.ServletContext;

public class WebAppAnchor
        extends StructAnchor {

    private String localPath;
    private boolean directory;
    private transient String absolutePath;

    /**
     * @param path
     *            the local-path inside the servlet-context, must start with '/'.
     */
    public WebAppAnchor(String path) {
        if (path == null)
            throw new NullPointerException("path");
        if (!path.startsWith("/"))
            throw new IllegalArgumentException("Not absolute: " + path);

        this.localPath = path;
        this.directory = path.endsWith("/");
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    @Override
    public String absoluteHref() {
        if (absolutePath == null) {
            synchronized (this) {
                if (absolutePath == null) {
                    ServletContext servletContext = CurrentHttpService.getServletContext();

                    // empty or "/foo".
                    String contextPath = servletContext.getContextPath();

                    absolutePath = contextPath + localPath;
                }
            }
        }
        return absolutePath;
    }

    @Override
    protected String getLocalPath() {
        return localPath;
    }

    @Override
    protected IAnchor create(String localPath) {
        return new WebAppAnchor(localPath);
    }

}
