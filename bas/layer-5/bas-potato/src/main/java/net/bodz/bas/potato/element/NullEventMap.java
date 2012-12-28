package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class NullEventMap
        implements IEventMap {

    @Override
    public int getEventCount() {
        return 0;
    }

    @Override
    public Collection<IEvent> getEvents() {
        return Collections.emptyList();
    }

    @Override
    public Set<String> getEventNames() {
        return Collections.emptySet();
    }

    @Override
    public boolean containsEvent(String eventName) {
        return false;
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
