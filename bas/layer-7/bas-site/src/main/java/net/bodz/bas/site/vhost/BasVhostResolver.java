package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

public class BasVhostResolver
        implements
            IVirtualHostResolver {

    @Override
    public IVirtualHost getVirtualHost(String id) {
        return null;
    }

    @Override
    public IVirtualHost resolveVirtualHost(HttpServletRequest request) {
        return null;
    }

}
