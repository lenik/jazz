package net.bodz.bas.t.set;

import java.util.Map;

import net.bodz.bas.c.object.IdentityHashSet;

public class MarkSet<T>
        extends IdentityHashSet<T> {

    private static final long serialVersionUID = 1L;

    public MarkSet() {
        super();
    }

    public MarkSet(int expectedMaxSize) {
        super(expectedMaxSize);
    }

    public MarkSet(Map<? extends T, ?> m) {
        super(m);
    }

}
