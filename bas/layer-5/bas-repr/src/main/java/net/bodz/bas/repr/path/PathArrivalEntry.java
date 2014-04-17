package net.bodz.bas.repr.path;

import net.bodz.bas.potato.ref.ValueEntry;

public class PathArrivalEntry<T>
        extends ValueEntry<T> {

    private static final long serialVersionUID = 1L;

    private IPathArrival arrival;

    public PathArrivalEntry(IPathArrival arrival) {
        super((T) arrival.getTarget());
        this.arrival = arrival;
    }

    public IPathArrival getArrival() {
        return arrival;
    }

}
