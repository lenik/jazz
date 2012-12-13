package net.bodz.swt.c3.pageflow;

import java.util.EventObject;

import net.bodz.bas.t.pojo.PathEntries;

public class HistoryRemovedEvent
        extends EventObject {

    private static final long serialVersionUID = 7197245074194714144L;

    public static final int REMOVED = 1;

    private final PathEntries path;
    private final int refCount;

    public HistoryRemovedEvent(History history, PathEntries path, int refCount) {
        super(history);
        this.path = path;
        this.refCount = refCount;
    }

    public PathEntries getPath() {
        return path;
    }

    public int getRefCount() {
        return refCount;
    }

    @Override
    public String toString() {
        return refCount + " * " + path;
    }

}
