package net.bodz.bas.util.ints;

public abstract class _IntSet implements IntSet {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean addAll(IntCollection c) {
        int added = 0;
        IntIterator i = c.iterator();
        while (i.hasNext())
            if (add(i.next()))
                added++;
        return added > 0;
    }

    @Override
    public boolean containsAll(IntCollection c) {
        IntIterator i = c.iterator();
        while (i.hasNext())
            if (!contains(i.next()))
                return false;
        return true;
    }

    @Override
    public boolean removeAll(IntCollection c) {
        int removed = 0;
        IntIterator i = c.iterator();
        while (i.hasNext())
            if (remove(i.next()))
                removed++;
        return removed > 0;
    }

    @Override
    public boolean retainAll(IntCollection c) {
        int removed = 0;
        IntIterator iter = iterator();
        while (iter.hasNext()) {
            int elm = iter.next();
            if (!c.contains(elm)) {
                remove(elm);
                removed++;
            }
        }
        return removed > 0;
    }

}
