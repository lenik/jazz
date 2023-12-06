package net.bodz.bas.site.vhost;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

import jakarta.servlet.http.HttpServletRequest;

@IndexedType
public interface IVirtualHostResolver
        extends
            IPriority {

    IVirtualHost getVirtualHost(String id);

    String getVirtualHostId(HttpServletRequest request);

    IVirtualHost resolveVirtualHost(HttpServletRequest request);

}
