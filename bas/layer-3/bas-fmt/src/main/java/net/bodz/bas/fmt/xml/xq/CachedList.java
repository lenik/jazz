package net.bodz.bas.fmt.xml.xq;

import java.util.ArrayList;
import java.util.Collection;

public class CachedList<E>
        extends ArrayList<E>
        implements
            ICachedIterable<E> {

    private static final long serialVersionUID = 1L;

    public CachedList() {
        super();
    }

    public CachedList(Collection<? extends E> c) {
        super(c);
    }

    public CachedList(int initialCapacity) {
        super(initialCapacity);
    }

}
