package net.bodz.bas.repr.path;

import net.bodz.bas.ui.dom1.UiVar;

public class PathArrivalEntry<T>
        extends UiVar<T> {

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
