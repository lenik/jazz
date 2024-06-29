package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IServletContextListener
        extends
            ServletContextListener,
            IPriority {

    boolean isIncluded(ServletContextEvent event);

}
