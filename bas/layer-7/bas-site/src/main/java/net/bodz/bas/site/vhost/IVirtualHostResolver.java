package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

public interface IVirtualHostResolver {

    IVirtualHost getVirtualHost(HttpServletRequest request);

}
