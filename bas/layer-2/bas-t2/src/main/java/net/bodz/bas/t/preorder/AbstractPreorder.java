package net.bodz.bas.t.preorder;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.err.IllegalUsageException;

public abstract class AbstractPreorder<T>
        implements IPreorder<T> {

    @Override
    public final int compare(T o1, T o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return -1;
        if (o2 == null)
            return 1;

        int cmp = precompare(o1, o2);
        if (cmp != UNKNOWN)
            return cmp;

        return compare2(o1, o2);
    }

    protected int compare2(T o1, T o2) {
        return compareIdentity(o1, o2);
    }

    @Override
    public boolean isGreaterOrEquals(T o1, T o2) {
        switch (precompare(o1, o2)) {
        case GREATER_THAN:
        case EQUALS:
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean isGreaterThan(T o1, T o2) {
        return precompare(o1, o2) == GREATER_THAN;
    }

    @Override
    public boolean isLessOrEquals(T o1, T o2) {
        switch (precompare(o1, o2)) {
        case LESS_THAN:
        case EQUALS:
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean isLessThan(T o1, T o2) {
        return precompare(o1, o2) == LESS_THAN;
    }

    @SafeVarargs
    @Override
    public final T meet(T... array) {
        if (array == null)
            throw new NullPointerException("array");
        if (array.length == 0)
            throw new IllegalArgumentException("array is empty");
        T meet = array[0];
        for (int i = 1; i < array.length; i++)
            meet = meet(meet, array[i]);
        return meet;
    }

    @Override
    public T meet(T o1, T o2) {
        switch (precompare(o1, o2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            return o1;
        case IPreorder.GREATER_THAN:
            return o2;
        }

        T p1 = getPreceding(o1);
        switch (precompare(p1, o2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            return p1;
        }

        T p2 = getPreceding(o2);
        switch (precompare(o1, p2)) {
        case IPreorder.GREATER_THAN:
        case IPreorder.EQUALS:
            return p2;
        case IPreorder.UNKNOWN:
            return meet(p1, p2);
        }

        throw new IllegalStateException(String.format("Can't infer meet from %s and %s", o1, o2));
    }

    @Override
    public MeetX<T> meetX(T o1, T o2) {
        switch (precompare(o1, o2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            return new MeetX<T>(o1, o1, o1);
        case IPreorder.GREATER_THAN:
            return new MeetX<T>(o2, o2, o2);
        }

        T p1 = getPreceding(o1);
        switch (precompare(p1, o2)) {
        case IPreorder.LESS_THAN:
        case IPreorder.EQUALS:
            T x2 = getSecondMin(p1, o2);
            return new MeetX<T>(p1, o1, x2);
        }

        T p2 = getPreceding(o2);
        switch (precompare(o1, p2)) {
        case IPreorder.GREATER_THAN:
        case IPreorder.EQUALS:
            T x1 = getSecondMin(p2, o1);
            return new MeetX<T>(p2, x1, o2);
        case IPreorder.UNKNOWN:
            return meetX(p1, p2);
        }

        throw new IllegalStateException(String.format("Can't infer meet from %s and %s", o1, o2));
    }

    final int searchPrecedesMax = 0x10000;

    protected T getSecondMin(T min, T x) {
        assert precompare(min, x) == LESS_THAN;
        int precedes = searchPrecedesMax;
        while (--precedes > 0) {
            T secondMin = x;
            x = getPreceding(x);
            if (min.equals(x))
                return secondMin;
        }
        throw new IllegalUsageException("Search too many precedings, max=" + searchPrecedesMax);
    }

    protected static final int compareIdentity(Object o1, Object o2) {
        assert o1 != o2;
        int id1 = System.identityHashCode(o1);
        int id2 = System.identityHashCode(o2);
        return IntMath.signum(id1 - id2);
    }

}
