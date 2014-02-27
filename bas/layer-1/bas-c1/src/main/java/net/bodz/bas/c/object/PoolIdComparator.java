package net.bodz.bas.c.object;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class PoolIdComparator
        extends AbstractNonNullComparator<Object> {

    private IdPool pool;

    public PoolIdComparator(IdPool pool) {
        if (pool == null)
            throw new NullPointerException("pool");
        this.pool = pool;
    }

    @Override
    public int compareNonNull(Object o1, Object o2) {
        int id1 = pool.getId(o1);
        int id2 = pool.getId(o2);
        return id1 - id2;
    }

}
