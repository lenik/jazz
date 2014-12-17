package net.bodz.bas.t.range;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

import net.bodz.bas.c.primitive.LongComparator;
import net.bodz.bas.err.ParseException;

public class LongRange
        extends AbstractSet<Long>
        implements NavigableSet<Long> {

    public long start;
    public long end;

    public LongRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static LongRange parse(String s)
            throws ParseException {
        try {
            int colon = s.indexOf(':');
            if (colon == -1) {
                long point = Long.parseLong(s);
                return new LongRange(point, point + 1);
            }
            String startStr = s.substring(0, colon);
            String endStr = s.substring(colon + 1);
            long start = startStr.isEmpty() ? Long.MIN_VALUE : Long.parseLong(startStr);
            long end = endStr.isEmpty() ? Long.MAX_VALUE : Long.parseLong(endStr);
            return new LongRange(start, end);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getMin() {
        return start;
    }

    public long getMax() {
        return end - 1;
    }

    class Iter
            implements Iterator<Long> {

        private long i = start;
        private long last = start - 1;

        @Override
        public boolean hasNext() {
            return i < end;
        }

        @Override
        public Long next() {
            return last = i++;
        }

        @Override
        public void remove() {
            if (last < start)
                throw new IllegalStateException();
            if (last == start)
                start++; // now last<start.
            else
                throw new UnsupportedOperationException("hole after remove");
        }

    }

    @Override
    public Iterator<Long> iterator() {
        return new Iter();
    }

    @Override
    public int size() {
        long sizel = end - start;
        if (sizel >= Integer.MAX_VALUE)
            return -1;
        else
            return (int) sizel;
    }

    @Override
    public boolean contains(Object o) {
        long i = (Long) o;
        return i >= start && i < end;
    }

    @Override
    public boolean add(Long e) {
        if (e >= start && e < end)
            return false;
        else if (e == start - 1)
            start--;
        else if (e == end)
            end++;
        else
            throw new UnsupportedOperationException("hole after add");
        return true;
    }

    @Override
    public boolean remove(Object o) {
        long i = (Long) o;
        if (i < start || i >= end)
            return false;
        if (i == end - 1)
            end--;
        else if (i == start)
            start++;
        else
            throw new UnsupportedOperationException("hole after remove");
        return true;
    }

    @Override
    public void clear() {
        end = start;
    }

    @Override
    public String toString() {
        return "IntRange[" + start + "," + end + ")";
    }

    // SortedSet

    @Override
    public Comparator<? super Long> comparator() {
        return LongComparator.INSTANCE;
    }

    @Override
    public Long first() {
        if (start >= end)
            throw new NoSuchElementException();
        return start;
    }

    @Override
    public Long last() {
        if (start >= end)
            throw new NoSuchElementException();
        return end - 1;
    }

    // NavigableSet

    class DIter
            implements Iterator<Long> {

        private long i = end;
        private long last = end;

        @Override
        public boolean hasNext() {
            return i > start;
        }

        @Override
        public Long next() {
            return last = --i;
        }

        @Override
        public void remove() {
            if (last >= end)
                throw new IllegalStateException();
            if (last == end - 1)
                end--; // now last>=end.
            else
                throw new UnsupportedOperationException("hole after remove");
        }
    }

    @Override
    public Iterator<Long> descendingIterator() {
        return new DIter();
    }

    @Override
    public Long floor(Long e) {
        if (start >= end)
            return null;
        if (e < start)
            return null;
        if (e >= end)
            return end - 1;
        return e;
    }

    @Override
    public Long ceiling(Long e) {
        if (start >= end)
            return null;
        if (e < start)
            return start;
        if (e >= end)
            return null;
        return e;
    }

    @Override
    public Long lower(Long _e) {
        if (start >= end)
            return null;
        long e = _e;
        if (e >= end)
            return end;
        if (e <= start)
            return null;
        return e - 1;
    }

    @Override
    public Long higher(Long _e) {
        if (start >= end)
            return null;
        long e = _e;
        if (e < start)
            return start;
        if (e >= end - 1)
            return null;
        return e + 1;
    }

    @Override
    public Long pollFirst() {
        if (start < end)
            return start++;
        return null;
    }

    @Override
    public Long pollLast() {
        if (start < end)
            return --end;
        return null;
    }

    @Override
    public NavigableSet<Long> headSet(Long toElement, boolean inclusive) {
        if (inclusive)
            toElement++;
        long _end = Math.min(end, toElement);
        return new LongRange(start, _end);
    }

    @Override
    public SortedSet<Long> headSet(Long toElement) {
        return headSet(toElement, false);
    }

    @Override
    public NavigableSet<Long> tailSet(Long fromElement, boolean inclusive) {
        if (!inclusive)
            fromElement++;
        long _start = Math.max(start, fromElement);
        return new LongRange(_start, end);
    }

    @Override
    public SortedSet<Long> tailSet(Long fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public NavigableSet<Long> subSet(Long fromElement, boolean fromInclusive, Long toElement, boolean toInclusive) {
        if (!fromInclusive)
            fromElement++;
        if (toInclusive)
            toElement++;
        long _start = Math.max(start, fromElement);
        long _end = Math.min(end, toElement);
        return new LongRange(_start, _end);
    }

    @Override
    public SortedSet<Long> subSet(Long fromElement, Long toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public NavigableSet<Long> descendingSet() {
        // abstract descending set?
        throw new UnsupportedOperationException();
    }

}
