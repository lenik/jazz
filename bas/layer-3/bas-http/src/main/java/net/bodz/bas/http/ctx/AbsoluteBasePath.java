package net.bodz.bas.http.ctx;

import net.bodz.bas.c.java.io.FilePath;

public class AbsoluteBasePath
        extends AbstractBasePath {

    /**
     * With the trailing slash.
     */
    private String path_;

    public AbsoluteBasePath(String href) {
        if (href == null)
            throw new NullPointerException("href");
        if (!href.startsWith("/"))
            throw new IllegalArgumentException("Not absolute path: " + href);
        href = FilePath.removeTrailingSlashes(href);
        this.path_ = href + "/";
    }

    @Override
    public String absolute() {
        return path_;
    }

}