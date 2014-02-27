package net.bodz.bas.c.object;

import java.util.Comparator;

public class NonNullPoolIdComparator
        implements Comparator<Object> {

    private IdPool pool;

    public NonNullPoolIdComparator(IdPool pool) {
        if (pool == null)
            throw new NullPointerException("pool");
        this.pool = pool;
    }

    @Override
    public int compare(Object o1, Object o2) {
        int id1 = pool.getId(o1);
        int id2 = pool.getId(o2);
        return id1 - id2;
    }

}
