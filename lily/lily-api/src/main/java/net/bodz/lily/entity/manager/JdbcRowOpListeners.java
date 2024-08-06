package net.bodz.lily.entity.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class JdbcRowOpListeners {

    static boolean enabled = true;

    static final List<IJdbcRowOpListener> listeners = new ArrayList<>();

    static {
        load();
    }

    static void load() {
        for (IJdbcRowOpListener listener : ServiceLoader.load(IJdbcRowOpListener.class)) {
            listeners.add(listener);
        }
    }

    public static boolean beforeRowOperation(JdbcRowOpEvent event)
            throws Exception {
        if (enabled)
            for (IJdbcRowOpListener listener : ServiceLoader.load(IJdbcRowOpListener.class))
                if (! listener.beforeRowOperation(event))
                    return false;
        return true;
    }

    public static void afterRowOperation(JdbcRowOpEvent event)
            throws Exception {
        if (enabled)
            for (IJdbcRowOpListener listener : ServiceLoader.load(IJdbcRowOpListener.class))
                listener.afterRowOperation(event);
    }

}
