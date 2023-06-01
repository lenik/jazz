package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IVirtualHostResolver
        extends
            IPriority {

    IVirtualHost getVirtualHost(String id);

    default IVirtualHost getVirtualHostFromRequest(HttpServletRequest request) {
        return resolveVirtualHost(request);
    }

    IVirtualHost resolveVirtualHost(HttpServletRequest request);

}
