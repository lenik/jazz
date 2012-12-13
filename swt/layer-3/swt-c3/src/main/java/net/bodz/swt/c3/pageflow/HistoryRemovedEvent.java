package net.bodz.swt.c3.pageflow;

import java.util.EventObject;

import net.bodz.bas.t.tree.TreePath;

public class HistoryRemovedEvent
        extends EventObject {

    private static final long serialVersionUID = 7197245074194714144L;

    public static final int REMOVED = 1;

    private final TreePath path;
    private final int refCount;

    public HistoryRemovedEvent(History history, TreePath path, int refCount) {
        super(history);
        this.path = path;
        this.refCount = refCount;
    }

    public TreePath getPath() {
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
