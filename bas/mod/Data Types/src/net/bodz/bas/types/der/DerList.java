package net.bodz.bas.types.der;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * thread-unsafe: the orig may be copy/cached.
 */
public abstract class DerList<E> extends AbstractList<E> implements
        Derivation<List<E>> {

    private final List<E> orig;

    /**
     * when modification(change, remove) is made on <code>orig</code>, then the
     * content of <code>orig</code> is copied (just references, not clone), and
     * the <code>edit</code> contains both modified <code>orig</code> and newly
     * added entries.
     */
    private boolean       copy;
ArrayList
    /**
     * if <code>copy</code> is <code>true</code>, <code>edit</code> must be
     * non-null.
     */
    private List<E>       edit;

    /**
     * @throws NullPointerException
     *             if <code>orig</code> is <code>null</code>.
     */
    public DerList(List<E> orig) {
        if (orig == null)
            throw new NullPointerException("orig");
        this.orig = orig;
    }

    @Override
    public List<E> getOrig() {
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

    private int getEditIndex(int index) {
        int editIndex = index - orig.size();
        if (editIndex < 0)
            throw new IndexOutOfBoundsException("index=" + index
                    + " edit-index=" + editIndex);
        return editIndex;
    }

    @Override
    public E get(int index) {
        if (copy)
            return edit.get(index);
        if (edit == null)
            return orig.get(index);
        int editIndex = getEditIndex(index);
        return edit.get(editIndex);
    }

    @Override
    public void add(int index, E e) {
        if (copy) {
            edit.add(e);
            return;
        }
        if (edit == null) {
            if (index < orig.size()) {
                edit = copy(orig);
                copy = true;
            } else {
                edit = create();
                int editIndex = getEditIndex(index);
            }
        } else {
            edit = concat(orig, edit);
            copy = true;
        }
    }

    protected abstract List<E> copy(List<E> list);

    @Override
    public E remove(int index) {
        if (copy)
            return edit.remove(index);
        if (edit == null)
            return orig.remove(index);
        int editIndex = getEditIndex(index);
        return edit.get(editIndex);
    }

    @Override
    public void clear() {
        copy = false;
        edit = null;
    }

    @Override
    public int indexOf(Object o) {
        if (copy)
            return edit.indexOf(o);
        int index = orig.indexOf(o);
        if (index != -1)
            return index;
        if (edit != null) {
            int editIndex = edit.indexOf(o);
            if (editIndex != -1)
                return orig.size() + editIndex;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (copy)
            return edit.lastIndexOf(o);
        if (edit != null) {
            int editIndex = edit.lastIndexOf(o);
            if (editIndex != -1)
                return orig.size() + editIndex;
        }
        return orig.lastIndexOf(o);
    }

    @Override
    public Iterator<E> iterator() {
        if (copy)
            return edit.iterator();
        return super.iterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (copy)
            return edit.listIterator(index);
        if (edit == null)
            return orig.listIterator(index);
        // back...
        return null;
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
        if (o == this)
            return true;
        if (copy)
            return edit.equals(o);
        if (edit == null)
            return orig.equals(o);
        // compare(orig+edit, o)
        if (!(o instanceof List<?>))
            return false;
        List<?> l = (List<?>) o;
        int norig = orig.size();
        int nedit = edit.size();
        int n = l.size();
        if (norig + nedit != n)
            return false;
        int offset = 0;
        for (int c = 0; c < 2; c++) {
            List<?> part = c == 0 ? orig : edit;
            int npart = part.size();
            for (int i = 0; i < npart; i++) {
                Object my = part.get(i);
                Object your = l.get(offset++);
                if (my != your)
                    if (my == null || !my.equals(your))
                        return false;
            }
        }
        return true;
    }

}
