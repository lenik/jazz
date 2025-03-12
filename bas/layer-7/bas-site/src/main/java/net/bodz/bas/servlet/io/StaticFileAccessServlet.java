package net.bodz.bas.servlet.io;

import java.io.File;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.err.IllegalConfigException;

public class StaticFileAccessServlet
        extends AbstractFileAccessServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_PATH = "start-path";

    /**
     * The target path, without traling slash.
     */
    private File start;

    @Override
    public void _init()
            throws ServletException {
        if (start == null)
            throw new IllegalConfigException(ATTRIBUTE_PATH + " isn't set.");
    }

    @Override
    protected void setInitParameter(String name, String value)
            throws ServletException {
        super.setInitParameter(name, value);
        switch (name) {
        case ATTRIBUTE_PATH:
            value = FilePath.removeTrailingSlashes(value);
            start = new File(value);
            break;
        }
    }

    @Override
    protected File getLocalRoot(HttpServletRequest req) {
        return start;
    }

}
