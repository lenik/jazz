package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.IllegalRequestException;
import net.bodz.bas.http.ctx.CurrentHttpService;

public class CurrentVirtualHost {

    public static IVirtualHost getVirtualHostOpt() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;
        return VirtualHostManager.getInstance().resolve(request);
    }

    public static IVirtualHost getVirtualHost() {
        HttpServletRequest request = CurrentHttpService.getRequest();
        IVirtualHost vhost = VirtualHostManager.getInstance().resolve(request);
        if (vhost == null)
            throw new IllegalRequestException("Virtual host is undefined.");
        return vhost;
    }

}
