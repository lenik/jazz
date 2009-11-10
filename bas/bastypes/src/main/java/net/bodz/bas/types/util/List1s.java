package net.bodz.bas.types.util;

import static net.bodz.bas.lang.err.OutOfDomainException.getMesg;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * One or many list.
 * 
 * no <code>null</code> element.
 */
public class List1s<E> extends AbstractList<E> {

    private E first;
    private List<E> more;

    protected List<E> createMoreList() {
        return new ArrayList<E>();
    }

    private void checkIndex(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException(getMesg("index", index, 0)); //$NON-NLS-1$
        if (first == null)
            throw new IndexOutOfBoundsException(getMesg("index", index, 0)); //$NON-NLS-1$
        if (index == 0)
            return;
        if (more == null)
            throw new IndexOutOfBoundsException(getMesg("index", index, 1)); //$NON-NLS-1$
        else if (index >= more.size() + 1)
            throw new IndexOutOfBoundsException(getMesg("index", index, more //$NON-NLS-1$
                    .size() + 1));
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        if (index == 0)
            return first;
        return more.get(index - 1);
    }

    @Override
    public int size() {
        if (first == null)
            return 0;
        if (more == null)
            return 1;
        return 1 + more.size();
    }

    @Override
    public void clear() {
        first = null;
        more = null;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            E orig = first;
            if (more != null && !more.isEmpty())
                first = more.remove(0);
            else
                first = null;
            return orig;
        } else
            return more.remove(index - 1);
    }

    @Override
    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException();
        if (first == null)
            first = e;
        else {
            if (more == null)
                more = createMoreList();
            more.add(e);
        }
        return true;
    }

    @Override
    public void add(int index, E e) {
        if (e == null)
            throw new NullPointerException();
        if (index == size()) {
            add(e);
            return;
        }
        checkIndex(index);
        if (index == 0) {
            if (more == null)
                more = createMoreList();
            more.add(0, first);
            first = e;
        } else {
            // assert index != size(); =>
            assert more != null;
            more.add(index - 1, e);
        }
    }

}
