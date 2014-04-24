package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

public interface IHref {

    String getAbsoluteHref();

    String getRelativeHrefTo(String otherAbsolute);

    String getRelativeHrefTo(HttpServletRequest req);

    /**
     * Get the relative href, based on the thread local request.
     *
     * @see CurrentHttpService#getRequest()
     */
    String getRelativeHref();

}
