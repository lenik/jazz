package net.bodz.bas.site.vhost;

import jakarta.servlet.http.HttpServletRequest;

public class NullVhostResolver
        implements
            IVirtualHostResolver {

    @Override
    public IVirtualHost getVirtualHost(String id) {
        return null;
    }

    @Override
    public String getVirtualHostId(HttpServletRequest request) {
        return "null-any";
    }

    @Override
    public IVirtualHost resolveVirtualHost(HttpServletRequest request) {
        return null;
    }

}
