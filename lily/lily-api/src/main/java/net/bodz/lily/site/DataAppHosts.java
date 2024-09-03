package net.bodz.lily.site;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.vhost.CurrentVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHost;

public class DataAppHosts {

    public static IDataAppHost fromRequest() {
        HttpServletRequest request = CurrentHttpService.getRequest();
        return fromRequest(request);
    }

    public static IDataAppHost fromRequest(HttpServletRequest request) {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost(request);
        if (! (vhost instanceof IDataAppHost))
            throw new IllegalUsageException("Incompatible vhost.");

        IDataAppHost lilyHost = (IDataAppHost) vhost;
        return lilyHost;
    }

}
