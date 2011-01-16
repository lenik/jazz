package net.bodz.bas.potato.traits;

import java.util.Map;

public interface IEventMap
        extends Map<String, IEvent> {

    /**
     * Find the matching event.
     * <p>
     * There should be only one matching event at most.
     * 
     * @param eventKey
     *            Non-<code>null</code> event key to match.
     * @return The matched event if any, or <code>null</code> if none matched.
     */
    IEvent getEvent(EventKey eventKey);

}
