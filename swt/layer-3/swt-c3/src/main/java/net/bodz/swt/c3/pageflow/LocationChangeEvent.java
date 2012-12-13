package net.bodz.swt.c3.pageflow;

import java.util.EventObject;

import net.bodz.bas.t.tree.TreePath;

public class LocationChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 4990351296447595259L;

    public final TreePath prev;
    public final TreePath next;

    public LocationChangeEvent(Object source, TreePath prev, TreePath next) {
        super(source);
        this.prev = prev;
        this.next = next;
    }

}
