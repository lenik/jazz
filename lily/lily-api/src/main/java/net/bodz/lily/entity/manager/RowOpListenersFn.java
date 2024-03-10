package net.bodz.lily.entity.manager;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.lily.entity.IId;

public class RowOpListenersFn {

    public static List<IJdbcRowOpListener> getRowOpListeners(Object obj) {
        Class<?> clazz = obj.getClass();
        List<IJdbcRowOpListener> listeners = new ArrayList<>();
        RowOpListeners aRowOps = clazz.getAnnotation(RowOpListeners.class);
        if (aRowOps != null) {
            for (Class<? extends IJdbcRowOpListener> handlerClass : aRowOps.value()) {
                IJdbcRowOpListener listener;
                try {
                    listener = handlerClass.getConstructor(IId.class).newInstance(obj);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalUsageException(e.getMessage(), e);
                }
                listeners.add(listener);
            }
        }
        return listeners;
    }

}
