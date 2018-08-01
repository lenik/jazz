package net.bodz.bas.t.range;

import java.util.*;

import net.bodz.bas.c.primitive.IntegerComparator;
import net.bodz.bas.err.ParseException;

public class IntRangeSet
        extends AbstractSet<Integer>
        implements NavigableSet<Integer> {

    public int start;
    public int end;

    public IntRangeSet(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static IntRangeSet minMax(int min, int max) {
        return new IntRangeSet(min, max + 1);
    }

    public static IntRangeSet parse(String s)
            throws ParseException {
        try {
            int colon = s.indexOf(':');
            if (colon == -1) {
                int point = Integer.parseInt(s);
                return new IntRangeSet(point, point + 1);
            }
            String startStr = s.substring(0, colon);
            String endStr = s.substring(colon + 1);
            int start = startStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(startStr);
            int end = endStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(endStr);
            return new IntRangeSet(start, end);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getMin() {
        return start;
    }

    public int getMax() {
        return end - 1;
    }

    class Iter
            implements Iterator<Integer> {

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
                throw new UnsupportedOperationException("hole after remove");
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
            throw new UnsupportedOperationException("hole after add");
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
            throw new UnsupportedOperationException("hole after remove");
        return true;
    }

    @Override
    public void clear() {
        end = start;
    }

    @Override
    public String toString() {
        return "IntRangeSet[" + start + "," + end + ")";
    }

    public List<Integer> toReversedList() {
        List<Integer> list = new ArrayList<Integer>(this);
        Collections.reverse(list);
        return list;
    }

    // SortedSet

    @Override
    public Comparator<? super Integer> comparator() {
        return IntegerComparator.INSTANCE;
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

    class DIter
            implements Iterator<Integer> {

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
                throw new UnsupportedOperationException("hole after remove");
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
        return new IntRangeSet(start, _end);
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
        return new IntRangeSet(_start, end);
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
        return new IntRangeSet(_start, _end);
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
