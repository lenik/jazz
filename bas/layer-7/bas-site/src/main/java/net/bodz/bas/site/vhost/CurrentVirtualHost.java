package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.http.ctx.CurrentHttpService;

public class CurrentVirtualHost {

    public static IVirtualHost getVirtualHostOpt() {
        VirtualHostManager manager = VirtualHostManager.getInstance();
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;
        return manager.getVirtualHost(request);
    }

    public static IVirtualHost getVirtualHost() {
        VirtualHostManager manager = VirtualHostManager.getInstance();
        HttpServletRequest request = CurrentHttpService.getRequest();
        return manager.getVirtualHost(request);
    }

}
