package net.bodz.bas.site.vhost;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IVirtualHostResolver
        extends
            IPriority {

    @Override
    default int getPriority() {
        return 0;
    }

    IVirtualHost getVirtualHost(String id);

    String getVirtualHostId(HttpServletRequest request);

    IVirtualHost resolveVirtualHost(HttpServletRequest request);

}
