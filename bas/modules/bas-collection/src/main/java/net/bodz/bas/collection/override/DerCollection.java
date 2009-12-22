package net.bodz.bas.collection.override;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.collection.iterator.ConcatIterator;

public class DerCollection<E>
        extends AbstractCollection<E>
        implements Derivation<Collection<E>> {

    private final Collection<E> orig;

    /**
     * when modification(change, remove) is made on <code>orig</code>, then the content of
     * <code>orig</code> is copied (just references, not clone), and the <code>edit</code> contains
     * both modified <code>orig</code> and newly added entries.
     */
    private boolean copy;

    /**
     * if <code>copy</code> is <code>true</code>, <code>edit</code> must be non-null.
     */
    private Collection<E> edit;

    /**
     * @throws NullPointerException
     *             if <code>orig</code> is <code>null</code>.
     */
    public DerCollection(Collection<E> orig) {
        if (orig == null)
            throw new NullPointerException("orig"); //$NON-NLS-1$
        this.orig = orig;
    }

    @Override
    public Collection<E> getOrig() {
        return orig;
    }

    @Override
    public int size() {
        if (copy)
            return edit.size();
        if (edit == null)
            return orig.size();
        return orig.size() + edit.size();
    }

    @Override
    public void clear() {
        copy = false;
        edit = null;
    }

    @Override
    public Iterator<E> iterator() {
        if (copy)
            return edit.iterator();
        if (edit == null)
            return orig.iterator();
        Iterator<E> concat = new ConcatIterator<E>(orig.iterator(), edit.iterator());
        return concat;
    }

    @Override
    public int hashCode() {
        if (copy)
            return edit.hashCode();
        if (edit == null)
            return orig.hashCode();
        return orig.hashCode() + edit.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (copy)
            return edit.equals(o);
        if (edit == null)
            return orig.equals(o);
        // orig+edit => equals?
        return false;
    }

}
