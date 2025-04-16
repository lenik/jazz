package net.bodz.bas.t.pool;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.UnderflowException;
import net.bodz.bas.meta.decl.NotNull;

public class IntSetPool
        implements IPool<Integer> {

    Set<Integer> set = new HashSet<>();
    int start;
    int end;
    int nextSearchFrom;

    public IntSetPool() {
        this(1);
    }

    public IntSetPool(int start) {
        this(start, Integer.MAX_VALUE);
    }

    public IntSetPool(int start, int end) {
        if (end <= start)
            throw new IllegalArgumentException("set empty pool");
        this.start = start;
        this.end = end;
        this.nextSearchFrom = start;
    }

    public static IntSetPool ofSize(int size) {
        return ofSize(size, 1);
    }

    public static IntSetPool ofSize(int size, int start) {
        return new IntSetPool(start, start + size);
    }

    public int capacity() {
        return end - start;
    }

    @Override
    public int available() {
        int capacity = capacity();
        return capacity - set.size();
    }

    @NotNull
    @Override
    public Integer allocate()
            throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();
        int mark = nextSearchFrom;
        while (true) {
            int id = nextSearchFrom++;
            if (set.add(id))
                return id;
            if (nextSearchFrom >= end)
                nextSearchFrom = start;
            if (nextSearchFrom == mark)
                throw new UnderflowException();
        }
    }

    @Override
    public void free(@NotNull Integer resource) {
        set.remove(resource);
    }

    public void rewind() {
        nextSearchFrom = start;
    }

}
