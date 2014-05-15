package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractAnchor
        implements IAnchor {

    @Override
    public String hrefFrom(String otherPath) {
        return fn.hrefFrom(otherPath, absoluteHref());
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
