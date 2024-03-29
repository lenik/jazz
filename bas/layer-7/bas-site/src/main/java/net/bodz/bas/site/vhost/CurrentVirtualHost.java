package net.bodz.bas.site.vhost;

import net.bodz.bas.err.IllegalRequestException;
import net.bodz.bas.log.diag.CompositeDiagContext;
import net.bodz.bas.log.diag.IContextsCdcConfigurer;
import net.bodz.bas.log.diag.IDiagContextTeller;
import net.bodz.bas.servlet.ctx.CurrentHttpService;

import jakarta.servlet.http.HttpServletRequest;

public class CurrentVirtualHost
        implements
            IContextsCdcConfigurer,
            IDiagContextTeller {

    @Override
    public void configure(CompositeDiagContext contexts) {
        contexts.add(this);
    }

    @Override
    public String getDiagContextName() {
        IVirtualHost vhost = getVirtualHostOpt();
        if (vhost == null)
            return null;
        else
            return "vh=" + vhost.getName();
    }

    public static IVirtualHost getVirtualHostOpt() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;
        return VirtualHostManager.getInstance().resolveVirtualHost(request);
    }

    public static IVirtualHost getVirtualHost() {
        VirtualHostManager manager = VirtualHostManager.getInstance();
        HttpServletRequest request = CurrentHttpService.getRequest();
        IVirtualHost vhost = manager.resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalRequestException("Virtual host is undefined.");
        return vhost;
    }

}
