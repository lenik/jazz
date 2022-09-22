package net.bodz.bas.servlet.ctx;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractAnchor
        implements
            IAnchor {

    @Override
    public String hrefFrom(String otherPath) {
        return PathUtils.hrefFrom(otherPath, toUriPath());
    }

    @Override
    public String hrefTo(String otherPath) {
        return PathUtils.hrefFrom(toUriPath(), otherPath);
    }

    public String fromCurrentRequest() {
        HttpServletRequest req = CurrentHttpService.getRequest();
        return hrefFrom(req.getRequestURI());
    }

    @Override
    public String toString() {
        return fromCurrentRequest();
    }

}
