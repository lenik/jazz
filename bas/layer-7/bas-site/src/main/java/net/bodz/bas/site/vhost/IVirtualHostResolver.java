package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IVirtualHostResolver
        extends
            IPriority {

    IVirtualHost get(String id);

    IVirtualHost get(HttpServletRequest request);

    IVirtualHost resolve(HttpServletRequest request);

}
