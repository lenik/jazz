package net.bodz.bas.potato.model;

import java.util.Collection;

public interface IEventMap {

    int size();

    Collection<IEvent> getEvents();

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
