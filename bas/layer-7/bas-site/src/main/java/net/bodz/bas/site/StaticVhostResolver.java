package net.bodz.bas.site;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHostResolver;

@ExcludedFromIndex
public abstract class StaticVhostResolver
        implements
            IVirtualHostResolver,
            ISiteRootResolver {

    static IVirtualHost vhost;

    public StaticVhostResolver() {
    }

    synchronized IVirtualHost lazyCreateVirtualHost() {
        if (vhost == null) {
            vhost = buildVirtualHost();
        }
        return vhost;
    }

    protected abstract IVirtualHost buildVirtualHost();

    @Override
    public ISiteRoot getSiteRoot(HttpServletRequest request) {
        return lazyCreateVirtualHost().getRoot();
    }

    @Override
    public IVirtualHost getVirtualHost(String id) {
        return lazyCreateVirtualHost();
    }

    @Override
    public IVirtualHost resolveVirtualHost(HttpServletRequest request) {
        return lazyCreateVirtualHost();
    }

}
