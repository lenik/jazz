package net.bodz.bas.site.vhost;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;

import jakarta.servlet.http.HttpServletRequest;

@ExcludedFromIndex
public class DedicatedVhostResolver
        implements
            IVirtualHostResolver {

    IVirtualHost vhost;

    public DedicatedVhostResolver(IVirtualHost vhost) {
        this.vhost = vhost;
    }

    @Override
    public IVirtualHost getVirtualHost(String id) {
        return vhost;
    }

    @Override
    public IVirtualHost resolveVirtualHost(HttpServletRequest request) {
        return vhost;
    }

}
