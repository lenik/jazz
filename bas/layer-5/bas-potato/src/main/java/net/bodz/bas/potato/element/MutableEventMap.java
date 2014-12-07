package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.t.order.DefaultComparator;

public class MutableEventMap
        implements IEventMap {

    Map<String, IEvent> map;

    public MutableEventMap(boolean sorted) {
        map = createMap(sorted);
    }

    protected Map<String, IEvent> createMap(boolean sorted) {
        if (sorted)
            return new TreeMap<>(DefaultComparator.getInstance());
        else
            return new LinkedHashMap<>();
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
