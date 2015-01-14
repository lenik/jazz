package net.bodz.bas.http.config;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IServletContextConfigurer {

    void filters(ServletContextConfig config);

    void servlets(ServletContextConfig config);

    void others(ServletContextConfig config);

}
