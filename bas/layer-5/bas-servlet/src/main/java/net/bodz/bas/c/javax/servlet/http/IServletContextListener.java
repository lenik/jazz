package net.bodz.bas.c.javax.servlet.http;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IServletContextListener
        extends ServletContextListener, IPriority {

    boolean isIncluded(ServletContextEvent event);

}
