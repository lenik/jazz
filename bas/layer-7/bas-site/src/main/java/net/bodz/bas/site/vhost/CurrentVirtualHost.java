package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.IllegalRequestException;
import net.bodz.bas.log.diag.CompositeDiagContext;
import net.bodz.bas.log.diag.IContextsCdcConfigurer;
import net.bodz.bas.log.diag.IDiagContextTeller;
import net.bodz.bas.servlet.ctx.CurrentHttpService;

public class CurrentVirtualHost
        implements IContextsCdcConfigurer, IDiagContextTeller {

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
