package net.bodz.bas.c.javax.servlet.http;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

@IndexedType
public interface IServletContextListener
        extends ServletContextListener, IPriority {

    boolean isIncluded(ServletContextEvent event);

}
