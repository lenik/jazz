package net.bodz.bas.types;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

import net.bodz.bas.nls.TypesNLS;
import net.bodz.bas.types.util.Comparators;

public class IntRange extends AbstractSet<Integer> implements NavigableSet<Integer> {

    private int start;
    private int end;

    public IntRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    class Iter implements Iterator<Integer> {

        private int i = start;
        private int last = start - 1;

        @Override
        public boolean hasNext() {
            return i < end;
        }

        @Override
        public Integer next() {
            return last = i++;
        }

        @Override
        public void remove() {
            if (last < start)
                throw new IllegalStateException();
            if (last == start)
                start++; // now last<start.
            else
                throw new UnsupportedOperationException(TypesNLS.getString("IntRange.holeAfterRemove")); //$NON-NLS-1$
        }

    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iter();
    }

    @Override
    public int size() {
        return end - start;
    }

    @Override
    public boolean contains(Object o) {
        int i = (Integer) o;
        return i >= start && i < end;
    }

    @Override
    public boolean add(Integer e) {
        if (e >= start && e < end)
            return false;
        else if (e == start - 1)
            start--;
        else if (e == end)
            end++;
        else
            throw new UnsupportedOperationException(TypesNLS.getString("IntRange.holeAfterAdd")); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int i = (Integer) o;
        if (i < start || i >= end)
            return false;
        if (i == end - 1)
            end--;
        else if (i == start)
            start++;
        else
            throw new UnsupportedOperationException(TypesNLS.getString("IntRange.holeAfterRemove")); //$NON-NLS-1$
        return true;
    }

    @Override
    public void clear() {
        end = start;
    }

    @Override
    public String toString() {
        return "IntRange[" + start + "," + end + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    // SortedSet

    @Override
    public Comparator<? super Integer> comparator() {
        return Comparators.INT;
    }

    @Override
    public Integer first() {
        if (start >= end)
            throw new NoSuchElementException();
        return start;
    }

    @Override
    public Integer last() {
        if (start >= end)
            throw new NoSuchElementException();
        return end - 1;
    }

    // NavigableSet

    class DIter implements Iterator<Integer> {

        private int i = end;
        private int last = end;

        @Override
        public boolean hasNext() {
            return i > start;
        }

        @Override
        public Integer next() {
            return last = --i;
        }

        @Override
        public void remove() {
            if (last >= end)
                throw new IllegalStateException();
            if (last == end - 1)
                end--; // now last>=end.
            else
                throw new UnsupportedOperationException(TypesNLS.getString("IntRange.holeAfterRemove")); //$NON-NLS-1$
        }
    }

    @Override
    public Iterator<Integer> descendingIterator() {
        return new DIter();
    }

    @Override
    public Integer floor(Integer e) {
        if (start >= end)
            return null;
        if (e < start)
            return null;
        if (e >= end)
            return end - 1;
        return e;
    }

    @Override
    public Integer ceiling(Integer e) {
        if (start >= end)
            return null;
        if (e < start)
            return start;
        if (e >= end)
            return null;
        return e;
    }

    @Override
    public Integer lower(Integer _e) {
        if (start >= end)
            return null;
        int e = _e;
        if (e >= end)
            return end;
        if (e <= start)
            return null;
        return e - 1;
    }

    @Override
    public Integer higher(Integer _e) {
        if (start >= end)
            return null;
        int e = _e;
        if (e < start)
            return start;
        if (e >= end - 1)
            return null;
        return e + 1;
    }

    @Override
    public Integer pollFirst() {
        if (start < end)
            return start++;
        return null;
    }

    @Override
    public Integer pollLast() {
        if (start < end)
            return --end;
        return null;
    }

    @Override
    public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
        if (inclusive)
            toElement++;
        int _end = Math.min(end, toElement);
        return new IntRange(start, _end);
    }

    @Override
    public SortedSet<Integer> headSet(Integer toElement) {
        return headSet(toElement, false);
    }

    @Override
    public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
        if (!inclusive)
            fromElement++;
        int _start = Math.max(start, fromElement);
        return new IntRange(_start, end);
    }

    @Override
    public SortedSet<Integer> tailSet(Integer fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
            boolean toInclusive) {
        if (!fromInclusive)
            fromElement++;
        if (toInclusive)
            toElement++;
        int _start = Math.max(start, fromElement);
        int _end = Math.min(end, toElement);
        return new IntRange(_start, _end);
    }

    @Override
    public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public NavigableSet<Integer> descendingSet() {
        // abstract descending set?
        throw new UnsupportedOperationException();
    }

}
