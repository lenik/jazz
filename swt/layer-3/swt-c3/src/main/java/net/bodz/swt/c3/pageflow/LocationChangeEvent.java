package net.bodz.swt.c3.pageflow;

import java.util.EventObject;

import net.bodz.bas.t.pojo.PathEntries;

public class LocationChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 4990351296447595259L;

    public final PathEntries prev;
    public final PathEntries next;

    public LocationChangeEvent(Object source, PathEntries prev, PathEntries next) {
        super(source);
        this.prev = prev;
        this.next = next;
    }

}
