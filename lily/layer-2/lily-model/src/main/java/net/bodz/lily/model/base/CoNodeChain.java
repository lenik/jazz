package net.bodz.lily.model.base;

import java.util.ArrayList;
import java.util.Collection;

public class CoNodeChain<T extends CoNode<T, ?>>
        extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    public CoNodeChain() {
        super();
    }

    public CoNodeChain(Collection<? extends T> c) {
        super(c);
    }

    public CoNodeChain(int initialCapacity) {
        super(initialCapacity);
    }

}
