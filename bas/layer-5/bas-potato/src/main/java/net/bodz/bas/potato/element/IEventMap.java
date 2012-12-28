package net.bodz.bas.potato.element;

import java.util.Set;

public interface IEventMap {

    int getEventCount();

    Iterable<IEvent> getEvents();

    Set<String> getEventNames();

    boolean containsEvent(String eventName);

    /**
     * Find the matching event.
     * <p>
     * There should be only one matching event at most.
     * 
     * @param eventName
     *            Non-<code>null</code> event name to get.
     * @return The matched event if any, or <code>null</code> if none matched.
     */
    IEvent getEvent(String eventName);

}
