package net.bodz.bas.http.ctx;

import javax.servlet.ServletContext;

import net.bodz.bas.c.java.io.FilePath;

public class WebAppPath
        extends AbstractBasePath {

    private String localPath;
    private transient String target_;

    public WebAppPath(String path) {
        if (path == null)
            throw new NullPointerException("path");
        if (!path.startsWith("/"))
            throw new IllegalArgumentException("Not absolute: " + path);
        this.localPath = FilePath.removeTrailingSlashes(path);
    }

    @Override
    public String absolute() {
        if (target_ == null) {
            synchronized (this) {
                if (target_ == null) {
                    ServletContext servletContext = CurrentServletContext.getServletContext();

                    // empty or "/foo".
                    String contextPath = servletContext.getContextPath();

                    target_ = contextPath + localPath + "/";
                }
            }
        }
        return target_;
    }

}
