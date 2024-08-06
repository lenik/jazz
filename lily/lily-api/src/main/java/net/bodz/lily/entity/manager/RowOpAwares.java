package net.bodz.lily.entity.manager;

import net.bodz.lily.entity.IId;

public class RowOpAwares {

    public static boolean beforeRowOperation(Object o, JdbcRowOpEvent event)
            throws Exception {
        RowOpAware aRowOps = o.getClass().getAnnotation(RowOpAware.class);
        if (aRowOps != null) {
            for (Class<? extends IJdbcRowOpListener> handlerClass : aRowOps.value()) {
                IJdbcRowOpListener handler = handlerClass.getConstructor(IId.class).newInstance(o);
                if (! handler.beforeRowOperation(event))
                    return false;
            }
        }
        return true;
    }

    public static void afterRowOperation(Object o, JdbcRowOpEvent event)
            throws Exception {
        RowOpAware aRowOps = o.getClass().getAnnotation(RowOpAware.class);
        if (aRowOps != null) {
            for (Class<? extends IJdbcRowOpListener> handlerClass : aRowOps.value()) {
                IJdbcRowOpListener handler = handlerClass.getConstructor(IId.class).newInstance(o);
                handler.afterRowOperation(event);
            }
        }
    }

}
