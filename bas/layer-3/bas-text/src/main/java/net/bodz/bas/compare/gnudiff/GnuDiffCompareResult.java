package net.bodz.bas.compare.gnudiff;

import java.util.List;

import net.bodz.bas.compare.MutableListCompareResult;

public class GnuDiffCompareResult<T>
        extends MutableListCompareResult<T, T, DiffEntry<T>> {

    private static final long serialVersionUID = 1L;

    public GnuDiffCompareResult(List<? extends T> source, List<? extends T> target) {
        super(source, target);
    }

    @Override
    public T internalCopy(T o) {
        return o;
    }

}
