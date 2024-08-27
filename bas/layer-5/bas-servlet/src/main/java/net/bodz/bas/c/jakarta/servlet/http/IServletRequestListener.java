package net.bodz.bas.c.jakarta.servlet.http;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IServletRequestListener
        extends
            ServletRequestListener,
            IPriority {

    @Override
    default void requestInitialized(ServletRequestEvent sre) {
        ServletRequestListener.super.requestInitialized(sre);
    }

}
