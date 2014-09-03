package net.bodz.bas.dbi;

public abstract class AbstractRepository<T, K>
        // extends AbstractMap<K, T>
        implements IRepository<T, K> {

    @Override
    public ISelection<T, K> all() {
        return null;
    }

}
