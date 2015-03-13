package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

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
        return VirtualHostManager.getInstance().resolve(request);
    }

}
