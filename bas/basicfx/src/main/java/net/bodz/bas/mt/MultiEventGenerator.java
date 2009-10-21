package net.bodz.bas.mt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public abstract class MultiEventGenerator implements Runnable {

    private final Executor              executor;
    private final Iterator<?>           eventSource;
    private Map<Object, List<Runnable>> eventHandlers;

    public MultiEventGenerator(Executor executor, Iterator<?> eventSource) {
        if (executor == null)
            throw new NullPointerException("executor"); //$NON-NLS-1$
        if (eventSource == null)
            throw new NullPointerException("eventSource"); //$NON-NLS-1$
        this.executor = executor;
        this.eventSource = eventSource;
        this.eventHandlers = new HashMap<Object, List<Runnable>>();
    }

    public void addHandler(Object event, Runnable eventHandler) {
        List<Runnable> list = eventHandlers.get(event);
        if (list == null) {
            list = new ArrayList<Runnable>(1);
            eventHandlers.put(event, list);
        }
        list.add(eventHandler);
    }

    public boolean removeHandler(Object event, Runnable eventHandler) {
        List<Runnable> list = eventHandlers.get(event);
        if (list == null)
            return false;
        boolean removed = list.remove(eventHandler);
        if (!removed)
            return false;
        if (list.isEmpty())
            eventHandlers.remove(event);
        return true;
    }

    @Override
    public void run() {
        while (eventSource.hasNext()) {
            Object event = eventSource.next();
            List<Runnable> list = eventHandlers.get(event);
            if (list != null)
                for (Runnable handler : list)
                    executor.execute(handler);
        }
    }

}
