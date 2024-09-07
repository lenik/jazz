package net.bodz.bas.site.vhost;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.servlet.ctx.CurrentHttpService;

import jakarta.servlet.http.HttpServletRequest;

public class VirtualHostScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHostOpt();
        if (vhost == null)
            return null;
        return new VirtualHostScopeInstance(vhost);
    }

    @Override
    public String tellId() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;

        VirtualHostManager manager = VirtualHostManager.getInstance();
        IVirtualHost vhost = manager.resolveVirtualHost(request);
        if (vhost != null)
            return vhost.getName();

        return request.getServerName() + ":" + request.getServerPort();
    }

}
