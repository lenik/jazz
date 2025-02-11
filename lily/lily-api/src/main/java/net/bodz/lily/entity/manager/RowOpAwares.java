package net.bodz.lily.entity.manager;

import net.bodz.lily.entity.IId;

public class RowOpAwares {

    public static boolean beforeRowOperation(JdbcRowOpEvent event, Object o)
            throws Exception {
        RowOpAware aRowOps = o.getClass().getAnnotation(RowOpAware.class);
        if (aRowOps != null) {
            for (Class<? extends IJdbcRowOpListener> handlerClass : aRowOps.value()) {
                IJdbcRowOpListener handler = handlerClass.getConstructor().newInstance();
                if (!handler.beforeRowOperation(event, o))
                    return false;
            }
        }
        return true;
    }

    public static void afterRowOperation(JdbcRowOpEvent event, Object o)
            throws Exception {
        RowOpAware aRowOps = o.getClass().getAnnotation(RowOpAware.class);
        if (aRowOps != null) {
            for (Class<? extends IJdbcRowOpListener> handlerClass : aRowOps.value()) {
                IJdbcRowOpListener handler = handlerClass.getConstructor().newInstance();
                handler.afterRowOperation(event, o);
            }
        }
    }

}
