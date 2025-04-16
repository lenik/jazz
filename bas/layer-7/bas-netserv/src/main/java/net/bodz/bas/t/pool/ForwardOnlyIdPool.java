package net.bodz.bas.t.pool;

import net.bodz.bas.err.UnderflowException;
import net.bodz.bas.meta.decl.NotNull;

public class ForwardOnlyIdPool
        implements IPool<Long> {

    long nextId;

    public ForwardOnlyIdPool() {
        this(1);
    }

    public ForwardOnlyIdPool(long initialId) {
        this.nextId = initialId;
    }

    @Override
    public int available() {
        if (nextId < 0)
            return 0;
        long n = Long.MAX_VALUE - nextId;
        if (n < Integer.MAX_VALUE)
            return (int) n;
        else
            return Integer.MAX_VALUE;
    }

    @NotNull
    @Override
    public Long allocate()
            throws UnderflowException {
        return nextId++;
    }

    @Override
    public void free(@NotNull Long resource) {
        // ignore, leaving id leaks.
    }

}
