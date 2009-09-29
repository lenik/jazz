package net.bodz.bas.types.set;

public abstract class _Set<T> implements Set<T> {

    @Override
    public Set<T> reduce() {
        return this;
    }

    @Override
    public Set<T> union(Set<T> set) {
        if (set == null)
            throw new NullPointerException("set");
        return new UnionSet<T>(this, set);
    }

    @Override
    public CutResult cut(Set<T> set) {
        SetRelation test = test(set);

        return null;
    }

}
