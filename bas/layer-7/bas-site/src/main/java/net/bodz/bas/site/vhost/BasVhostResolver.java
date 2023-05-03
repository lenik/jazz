package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

public class BasVhostResolver
        implements
            IVirtualHostResolver {

    @Override
    public IVirtualHost get(String id) {
        return null;
    }

    @Override
    public IVirtualHost resolve(HttpServletRequest request) {
        return null;
    }

}
