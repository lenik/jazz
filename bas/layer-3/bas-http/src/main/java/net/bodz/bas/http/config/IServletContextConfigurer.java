package net.bodz.bas.http.config;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IServletContextConfigurer
        extends IPriority {

    void filters(ServletContextConfig config);

    void servlets(ServletContextConfig config);

    void others(ServletContextConfig config);

}
