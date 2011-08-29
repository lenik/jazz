package net.bodz.bas.collection.scope;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CSet<E>
        extends CCollection<E>
        implements Set<E> {

    public CSet()
            throws InstantiationException {
        super();
    }

    public CSet(Set<E> start) {
        super(start);
    }

    /** create a HashSet by default */
    @Override
    protected Set<E> create() {
        return new HashSet<E>();
    }

    @Override
    protected Collection<E> link(Collection<E> _this, Collection<E> next) {
        assert _this instanceof Set<?>;
        assert next instanceof Set<?>;
        return new Link<E>((Set<E>) _this, (Set<E>) next);
    }

    static class Link<E>
            extends CCollection.Link<E>
            implements Set<E> {

        public Link(Set<E> _this, Set<E> next) {
            super(_this, next);
        }

    }

}
