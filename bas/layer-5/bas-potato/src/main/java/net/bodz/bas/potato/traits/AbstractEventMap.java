package net.bodz.bas.potato.traits;

import java.util.TreeMap;

import net.bodz.bas.util.order.ComparableComparator;

public class AbstractEventMap
        extends TreeMap<String, IEvent>
        implements IEventMap {

    private static final long serialVersionUID = 1L;

    public AbstractEventMap() {
        super(ComparableComparator.getInstance());
    }

    @Override
    public IEvent getEvent(EventKey eventKey) {
        return get(eventKey.getName());
    }

}
