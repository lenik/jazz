package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.repr.form.SortOrder;

public class MutableEventMap
        implements
            IEventMap {

    Map<String, IEvent> map;

    public MutableEventMap(SortOrder order) {
        map = order.newMap();
    }

    @Override
    public int getEventCount() {
        return map.size();
    }

    @Override
    public Collection<IEvent> getEvents() {
        return map.values();
    }

    @Override
    public Set<String> getEventNames() {
        return map.keySet();
    }

    @Override
    public boolean containsEvent(String eventName) {
        return map.containsKey(eventName);
    }

    @Override
    public IEvent getEvent(String eventName) {
        if (eventName == null)
            throw new NullPointerException("eventName");
        return map.get(eventName);
    }

    public void addEvent(IEvent event) {
        if (event == null)
            throw new NullPointerException("event");

        String eventName = event.getName();

        IEvent existing = map.get(eventName);
        if (existing != null)
            throw new DuplicatedKeyException(eventName);

        map.put(eventName, event);
    }

    public IEvent removeEvent(String eventName) {
        if (eventName == null)
            throw new NullPointerException("eventName");
        return map.remove(eventName);
    }

}
