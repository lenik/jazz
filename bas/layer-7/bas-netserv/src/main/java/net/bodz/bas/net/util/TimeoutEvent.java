package net.bodz.bas.net.util;

import java.time.Instant;
import java.util.EventObject;

public class TimeoutEvent
        extends EventObject {

    final long expire;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public TimeoutEvent(Object source, long expire) {
        super(source);
        this.expire = expire;
    }

    public Instant getExpire() {
        return Instant.ofEpochMilli(expire);
    }

}
