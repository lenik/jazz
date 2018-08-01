package net.bodz.bas.t.range;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

import net.bodz.bas.c.primitive.ShortComparator;
import net.bodz.bas.err.ParseException;

public class ShortRangeSet
        extends AbstractSet<Short>
        implements NavigableSet<Short> {

    public short start;
    public short end;

    public ShortRangeSet(short start, short end) {
        this.start = start;
        this.end = end;
    }

    public static ShortRangeSet parse(String s)
            throws ParseException {
        try {
            int colon = s.indexOf(':');
            if (colon == -1) {
                short point = Short.parseShort(s);
                return new ShortRangeSet(point, (short) (point + 1));
            }
            String startStr = s.substring(0, colon);
            String endStr = s.substring(colon + 1);
            short start = startStr.isEmpty() ? Short.MIN_VALUE : Short.parseShort(startStr);
            short end = endStr.isEmpty() ? Short.MAX_VALUE : Short.parseShort(endStr);
            return new ShortRangeSet(start, end);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    public short getStart() {
        return start;
    }

    public void setStart(short start) {
        this.start = start;
    }

    public short getEnd() {
        return end;
    }

    public void setEnd(short end) {
        this.end = end;
    }

    public short getMin() {
        return start;
    }

    public short getMax() {
        return (short) (end - 1);
    }

    class Iter
            implements Iterator<Short> {

        private short i = start;
        private short last = (short) (start - 1);

        @Override
        public boolean hasNext() {
            return i < end;
        }

        @Override
        public Short next() {
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
    public Iterator<Short> iterator() {
        return new Iter();
    }

    @Override
    public int size() {
        return end - start;
    }

    @Override
    public boolean contains(Object o) {
        short i = (Short) o;
        return i >= start && i < end;
    }

    @Override
    public boolean add(Short e) {
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
        short i = (Short) o;
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
    public Comparator<? super Short> comparator() {
        return ShortComparator.INSTANCE;
    }

    @Override
    public Short first() {
        if (start >= end)
            throw new NoSuchElementException();
        return start;
    }

    @Override
    public Short last() {
        if (start >= end)
            throw new NoSuchElementException();
        return (short) (end - 1);
    }

    // NavigableSet

    class DIter
            implements Iterator<Short> {

        private short i = end;
        private short last = end;

        @Override
        public boolean hasNext() {
            return i > start;
        }

        @Override
        public Short next() {
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
    public Iterator<Short> descendingIterator() {
        return new DIter();
    }

    @Override
    public Short floor(Short e) {
        if (start >= end)
            return null;
        if (e < start)
            return null;
        if (e >= end)
            return (short) (end - 1);
        return e;
    }

    @Override
    public Short ceiling(Short e) {
        if (start >= end)
            return null;
        if (e < start)
            return start;
        if (e >= end)
            return null;
        return e;
    }

    @Override
    public Short lower(Short _e) {
        if (start >= end)
            return null;
        short e = _e;
        if (e >= end)
            return end;
        if (e <= start)
            return null;
        return (short) (e - 1);
    }

    @Override
    public Short higher(Short _e) {
        if (start >= end)
            return null;
        short e = _e;
        if (e < start)
            return start;
        if (e >= end - 1)
            return null;
        return (short) (e + 1);
    }

    @Override
    public Short pollFirst() {
        if (start < end)
            return start++;
        return null;
    }

    @Override
    public Short pollLast() {
        if (start < end)
            return --end;
        return null;
    }

    @Override
    public NavigableSet<Short> headSet(Short toElement, boolean inclusive) {
        if (inclusive)
            toElement++;
        short _end = (short) Math.min(end, toElement);
        return new ShortRangeSet(start, _end);
    }

    @Override
    public SortedSet<Short> headSet(Short toElement) {
        return headSet(toElement, false);
    }

    @Override
    public NavigableSet<Short> tailSet(Short fromElement, boolean inclusive) {
        if (!inclusive)
            fromElement++;
        short _start = (short) Math.max(start, fromElement);
        return new ShortRangeSet(_start, end);
    }

    @Override
    public SortedSet<Short> tailSet(Short fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public NavigableSet<Short> subSet(Short fromElement, boolean fromInclusive, Short toElement, boolean toInclusive) {
        if (!fromInclusive)
            fromElement++;
        if (toInclusive)
            toElement++;
        short _start = (short) Math.max(start, fromElement);
        short _end = (short) Math.min(end, toElement);
        return new ShortRangeSet(_start, _end);
    }

    @Override
    public SortedSet<Short> subSet(Short fromElement, Short toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public NavigableSet<Short> descendingSet() {
        // abstract descending set?
        throw new UnsupportedOperationException();
    }

}
