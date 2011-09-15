package net.bodz.bas.collection.preorder;

import net.bodz.bas.err.IllegalUsageException;

public abstract class AbstractLattice<T>
        extends AbstractPreorder<T>
        implements ILattice<T> {

    @SafeVarargs
    @Override
    public final T join(T... array) {
        if (array == null)
            throw new NullPointerException("array");
        if (array.length == 0)
            throw new IllegalArgumentException("array is empty");
        T join = array[0];
        for (int i = 1; i < array.length; i++)
            join = join(join, array[i]);
        return join;
    }

    @Override
    public T join(T o1, T o2) {
        switch (precompare(o1, o2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            return o2;
        case IPreorder.GREATER_THAN:
            return o1;
        }

        T s1 = getSucceeding(o1);
        switch (precompare(s1, o2)) {
        case IPreorder.GREATER_THAN:
        case IPreorder.EQUALS:
            return s1;
        }

        T s2 = getSucceeding(o2);
        switch (precompare(o1, s2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            return s2;
        case IPreorder.UNKNOWN:
            return join(s1, s2);
        }

        throw new IllegalStateException(String.format("Can't infer join from %s and %s", o1, o2));
    }

    @Override
    public JoinX<T> joinX(T o1, T o2) {
        switch (precompare(o1, o2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            return new JoinX<T>(o2, o2, o2);
        case IPreorder.GREATER_THAN:
            return new JoinX<T>(o1, o1, o1);
        }

        T s1 = getSucceeding(o1);
        switch (precompare(s1, o2)) {
        case IPreorder.GREATER_THAN:
        case IPreorder.EQUALS:
            T x2 = getSecondMax(s1, o2);
            return new JoinX<T>(s1, o1, x2);
        }

        T s2 = getSucceeding(o2);
        switch (precompare(o1, s2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            T x1 = getSecondMax(s2, o1);
            return new JoinX<T>(s2, x1, o2);
        case IPreorder.UNKNOWN:
            return joinX(s1, s2);
        }

        throw new IllegalStateException(String.format("Can't infer join from %s and %s", o1, o2));
    }

    final int searchSucceedsMax = 0x10000;

    protected T getSecondMax(T max, T x) {
        assert precompare(max, x) == GREATER_THAN;
        int succeeds = searchSucceedsMax;
        while (--succeeds > 0) {
            T secondMax = x;
            x = getSucceeding(x);
            if (max.equals(x))
                return secondMax;
        }
        throw new IllegalUsageException("Search too many succeeds, max=" + searchSucceedsMax);
    }

}
