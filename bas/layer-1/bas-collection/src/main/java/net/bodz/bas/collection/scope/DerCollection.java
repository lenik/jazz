package net.bodz.bas.collection.scope;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.collection.iterator.ConcatIterator;

public class CompositeCollection<E>
        extends AbstractCollection<E> {

    private final Collection<E> parent;

    /**
     * when modification(change, remove) is made on <code>orig</code>, then the content of
     * <code>orig</code> is copied (just references, not clone), and the <code>edit</code> contains
     * both modified <code>orig</code> and newly added entries.
     */
    private boolean copy;

    /**
     * if <code>copy</code> is <code>true</code>, <code>edit</code> must be non-null.
     */
    private Collection<E> local;

    /**
     * @throws NullPointerException
     *             if <code>orig</code> is <code>null</code>.
     */
    public CompositeCollection(Collection<E> parent) {
        if (parent == null)
            throw new NullPointerException("parent"); 
        this.parent = parent;
    }

    public Collection<E> getParent() {
        return parent;
    }

    @Override
    public int size() {
        if (copy)
            return local.size();
        if (local == null)
            return parent.size();
        return parent.size() + local.size();
    }

    @Override
    public void clear() {
        copy = false;
        local = null;
    }

    @Override
    public Iterator<E> iterator() {
        if (copy)
            return local.iterator();
        if (local == null)
            return parent.iterator();
        Iterator<E> concat = new ConcatIterator<E>(parent.iterator(), local.iterator());
        return concat;
    }

    @Override
    public int hashCode() {
        if (copy)
            return local.hashCode();
        if (local == null)
            return parent.hashCode();
        return parent.hashCode() + local.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (copy)
            return local.equals(o);
        if (local == null)
            return parent.equals(o);
        // orig+edit => equals?
        return false;
    }

}
