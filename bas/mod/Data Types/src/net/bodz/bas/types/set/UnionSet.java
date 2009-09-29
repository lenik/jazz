package net.bodz.bas.types.set;

import java.util.Arrays;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.types.util.DirectIterator;

public class UnionSet<T> extends _Set<T> {

    private final Set<T>[] sets;
    private final boolean  isFinal;
    private final boolean  isFinite;

    private T              first;
    private T              last;

    @SuppressWarnings("unchecked")
    public UnionSet(Set<T> a, Set<T> b) {
        this((Set<T>[]) new Set<?>[] { a, b });
    }

    @SuppressWarnings("unchecked")
    public UnionSet(Set<T>[] sets) {
        if (sets == null)
            throw new NullPointerException("sets");
        int n = sets.length;
        this.sets = (Set<T>[]) new Set<?>[n];
        boolean allFinal = true;
        boolean allFinite = true;
        for (int i = 0; i < n; i++) {
            Set<T> set = sets[i];
            if (set == null)
                throw new NullPointerException("sets[" + i + "]");
            this.sets[i] = set;
            if (!set.isFinal())
                allFinal = false;
            if (!set.isFinite())
                allFinite = false;
        }
        this.isFinal = allFinal;
        this.isFinite = allFinite;
    }

    @Override
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean isFinite() {
        if (isFinal)
            return isFinite;
        // ...
        return false;
    }

    @Override
    public T first() {
        if (isFinal)
            return first;
        T min = null;
        for (Set<T> set : sets) {
            T x = set.first();

        }
        return min;
    }

    @Override
    public T last() {
        if (isFinal)
            return last;
        T max = null;
        for (Set<T> set : sets) {
            T x = set.first();

        }
        return max;
    }

    @Override
    public boolean contains(T x) {
        for (Set<T> set : sets)
            if (set.contains(x))
                return true;
        return false;
    }

    @Override
    public SetRelation test(Set<T> x) {
        SetRelation test = null;
        for (Set<T> set : sets) {
            if (test == null)
                test = set.test(x);
            else
                switch (set.test(x)) {
                case EMPTY:
                case SAME:
                case EMPTY_LESS:
                case SHARE_LESS:
                case EMPTY_MORE:
                case SHARE_MORE:
                case ISOLATED:
                case CUT:
                }
        }
        return test;
    }

    @Override
    public Set<T> toFinal() {
        if (isFinal)
            return this;
        @SuppressWarnings("unchecked")
        Set<T>[] finalSets = (Set<T>[]) new Set<?>[sets.length];
        for (int i = 0; i < sets.length; i++)
            finalSets[i] = sets[i].toFinal();
        return new UnionSet<T>(finalSets);
    }

    @Override
    public DirectIterator<T, RuntimeException> iterator(boolean allowOverlap) {
        // Set<T> firstSet = sets[0];
        // return concat(firstSet, union({Rest})).iterator()
        throw new NotImplementedException();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Set<T> set : sets) {
            hash <<= 1;
            hash ^= set.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnionSet<?>))
            return false;
        Set<?>[] v = ((UnionSet<?>) obj).sets;
        return Arrays.equals(sets, v);
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(sets.length * 100);
        buf.append("(");
        for (int i = 0; i < sets.length; i++) {
            if (i != 0)
                buf.append(" U "); // ∪ \u222A
            buf.append(sets[i]);
        }
        buf.append(")");
        return buf.toString();
    }

}
