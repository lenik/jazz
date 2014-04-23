package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractHref
        implements IHref {

    @Override
    public String getRelativeHrefTo(HttpServletRequest req) {
        if (req == null)
            throw new NullPointerException("req");
        String uri = req.getRequestURI();
        return getRelativeHrefTo(uri);
    }

    @Override
    public String getRelativeHref() {
        HttpServletRequest req = ThreadServletContext.getRequest();
        return getRelativeHrefTo(req);
    }

    @Override
    public String toString() {
        return getRelativeHref();
    }

}
