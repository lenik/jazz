package net.bodz.bas.potato.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.t.iterator.Iterables;

public class LinkedEventMap
        implements IEventMap {

    private List<IEventMap> maps;

    public LinkedEventMap(List<IEventMap> eventMaps) {
        if (eventMaps == null)
            throw new NullPointerException("eventMaps");
        this.maps = eventMaps;
    }

    public LinkedEventMap(IEventMap... eventMaps) {
        maps = Arrays.asList(eventMaps);
    }

    @Override
    public int getEventCount() {
        int count = 0;
        for (IEventMap map : maps)
            count += map.getEventCount();
        return count;
    }

    @Override
    public Iterable<IEvent> getEvents() {
        List<Iterable<IEvent>> iterables = new ArrayList<>(maps.size());

        for (IEventMap map : maps)
            iterables.add(map.getEvents());

        return Iterables.concat(iterables);
    }

    @Override
    public Set<String> getEventNames() {
        Set<String> set = new LinkedHashSet<>();

        for (IEventMap map : maps)
            set.addAll(map.getEventNames());

        return set;
    }

    @Override
    public boolean containsEvent(String eventName) {
        for (IEventMap map : maps)
            if (map.containsEvent(eventName))
                return true;
        return false;
    }

    @Override
    public IEvent getEvent(String eventName) {
        for (IEventMap map : maps) {
            IEvent event = map.getEvent(eventName);
            if (event != null)
                return event;
        }
        return null;
    }

}
