package net.bodz.bas.collection.scope;

import java.util.AbstractList;
import java.util.List;

/**
 * thread-unsafe: the orig may be copy/cached.
 * 
 * @test DerListTest
 */
public abstract class DerList<E> extends AbstractList<E> implements Derivation<List<E>> {

    private final List<E> pList;
    private final List<E> qList;
    private DerListDelta delta;
    private int dels;

    /**
     * @throws NullPointerException
     *             if <code>orig</code> is <code>null</code>.
     */
    public DerList(List<E> orig) {
        if (orig == null)
            throw new NullPointerException("orig"); 
        this.pList = orig;
        this.delta = new DerListDelta();
        this.qList = createAllocList();
    }

    protected abstract List<E> createAllocList();

    @Override
    public List<E> getParent() {
        return pList;
    }

    @Override
    public int size() {
        return pList.size() + qList.size() - dels;
    }

    @Override
    public E get(int index) {
        int pq = delta.map(index);
        int q = pq - pList.size();
        if (q < 0)
            return pList.get(pq);
        return qList.get(q);
    }

    @Override
    public void add(int index, E e) {
        int qNext = pList.size() + qList.size(); // size(); **MEMORY LEAKS.
        delta.add(qNext, index);
        int pq = delta.map(index);
        int q = pq - pList.size();
        assert q >= 0 : "should added to a new allocated unit"; 
        qList.add(q, e);
    }

    @Override
    public E remove(int index) {
        int pq = delta.map(index);
        int q = pq - pList.size();
        E old;
        if (q < 0) {
            old = pList.get(pq);
        } else {
            // TODO: memory leaks..., how to reuse?
            old = qList.set(q, null);
        }
        delta.remove(index);
        dels++;
        return old;
    }

    /**
     * remove all entries.
     */
    @Override
    public void clear() {
        reset();
        int n = pList.size();
        delta.remove(0, n);
        dels += n;
    }

    /**
     * remove all changes from orig.
     */
    public void reset() {
        delta.reset();
        qList.clear();
        dels = 0;
    }

}
