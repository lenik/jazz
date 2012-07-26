package net.bodz.bas.potato.spi.builtin;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.potato.traits.IEvent;
import net.bodz.bas.potato.traits.IEventMap;

public class NullEventMap
        implements IEventMap {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<IEvent> getEvents() {
        return Collections.emptyList();
    }

    @Override
    public IEvent getEvent(String eventName) {
        return null;
    }

    static final NullEventMap instance = new NullEventMap();

    public static NullEventMap getInstance() {
        return instance;
    }

}
