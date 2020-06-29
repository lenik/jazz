package net.bodz.bas.servlet.io;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;
import net.bodz.bas.std.rfc.http.ICacheControl;

/**
 * Notice:
 *<p>
 * If you can't (http-)get a static existed file, it could be false-cached. Restart the server or
 * invalidate the cache then try again.
 */
public class StaticVhostFileAccessServlet
        extends AbstractFileAccessServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_PATH = "start-path";
    public static final String ATTRIBUTE_SUB = "sub-path";

    /**
     * The target path, without traling slash.
     */
    private File start;
    private String sub;

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
        IVirtualHost vhost = VirtualHostManager.getInstance().resolve(req);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String vhostName = vhost.getName();
        File vstart = new File(start, vhostName);
        if (sub != null)
            vstart = new File(vstart, sub);
        return vstart;
    }

    @Override
    public ICacheControl getCacheControl(HttpServletRequest req, URL url) {
        return super.getCacheControl(req, url);
    }

}
