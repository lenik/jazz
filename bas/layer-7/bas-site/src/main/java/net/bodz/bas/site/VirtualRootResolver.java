package net.bodz.bas.site;

import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHostResolver;
import net.bodz.bas.site.vhost.VirtualHostManager;

import jakarta.servlet.http.HttpServletRequest;

public class VirtualRootResolver
        implements
            ISiteRootResolver {

    IVirtualHostResolver vhResolver;

    public VirtualRootResolver() {
        this(VirtualHostManager.getInstance());
    }

    public VirtualRootResolver(IVirtualHostResolver vhResolver) {
        this.vhResolver = vhResolver;
    }

    @Override
    public ISiteRoot getSiteRoot(HttpServletRequest request) {
        IVirtualHost vhost = vhResolver.resolveVirtualHost(request);
        if (vhost == null)
            return null;
        else
            return vhost.getRoot();
    }

}
