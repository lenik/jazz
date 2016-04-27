package net.bodz.bas.http.servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.err.IllegalConfigException;

public class StaticFileAccessServlet
        extends AbstractFileAccessServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_PATH = "start-path";
    public static final String ATTRIBUTE_MAX_AGE = "max-age";

    /**
     * The target path, without traling slash.
     */
    private File start;

    /**
     * 1 day by default.
     */
    private int maxAge = 86400;

    @Override
    public void init()
            throws ServletException {
        super.init();

        if (start == null)
            throw new IllegalConfigException(ATTRIBUTE_PATH + " isn't set.");
    }

    @Override
    protected void setParameter(String name, String value) {
        super.setParameter(name, value);
        switch (name) {
        case ATTRIBUTE_PATH:
            value = FilePath.removeTrailingSlashes(value);
            start = new File(value);
            break;
        }
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    protected File getLocalRoot(HttpServletRequest req) {
        return start;
    }

}
