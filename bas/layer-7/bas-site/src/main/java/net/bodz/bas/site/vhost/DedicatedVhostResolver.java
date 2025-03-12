package net.bodz.bas.site.vhost;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;

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
    public String getVirtualHostId(HttpServletRequest request) {
        return "dedicated-any";
    }

    @Override
    public IVirtualHost resolveVirtualHost(HttpServletRequest request) {
        return vhost;
    }

}
