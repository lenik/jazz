package net.bodz.bas.t.specmap;

import java.util.Objects;

public class StartEndRange<T extends Comparable<T>>
        implements
            IMutableRange<T> {

    public T start;
    public T end;

    public StartEndRange(T start, T end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public T getStart() {
        return start;
    }

    @Override
    public void setStart(T start) {
        this.start = start;
    }

    @Override
    public T getStop() {
        return null;
    }

    @Override
    public void setStop(T stop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getEnd() {
        return end;
    }

    @Override
    public void setEnd(T end) {
        this.end = end;
    }

    @Override
    public boolean isStartEnd() {
        return true;
    }

    @Override
    public boolean contains(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) > 0) // start > val
            return false;
        if (end != null && end.compareTo(val) <= 0) // end <= val
            return false;
        return true;
    }

    @Override
    public boolean contains(IRange<T> range) {
        if (range == null)
            return start == null && end == null;
        T start2 = range.getStart();
        T stop2 = range.getStop();
        T end2 = range.getEnd();
        if (stop2 != null)
            return containsStartStop(start2, stop2);
        if (end2 != null)
            return containsStartEnd(start2, end2);
        if (end != null)
            return false;

        if (start == start2)
            return true;
        if (start == null)
            return true;
        if (start2 == null)
            return false;
        return start.compareTo(start2) <= 0; // start <= start2
    }

    @Override
    public boolean containsStartEnd(T start2, T end2) {
        if (start2 == null) {
            if (start != null)
                return false;
        } else {
            if (end != null && end.compareTo(start2) <= 0) // end <= start2
                return false;
        }
        if (end2 == null) {
            if (end != null)
                return false;
        } else {
            if (start != null && start.compareTo(end2) >= 0) // start >= end2
                return false;
        }
        return true;
    }

    @Override
    public boolean containsStartStop(T start2, T stop2) {
        if (start2 == null) {
            if (start != null)
                return false;
        } else {
            if (end != null && end.compareTo(start2) <= 0) // end <= start2
                return false;
        }
        if (stop2 == null) {
            if (end != null)
                return false;
        } else {
            if (start != null && start.compareTo(stop2) > 0) // start > stop2
                return false;
        }
        return true;
    }

    @Override
    public boolean isLessThan(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) >= 0) // start >= val
            return false;
        if (end == null || end.compareTo(val) > 0) // end > val
            return false;
        return true;
    }

    @Override
    public boolean isLessOrContains(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) > 0) // start > val
            return false;
        if (end == null || end.compareTo(val) > 0) // end > val
            return false;
        return true;
    }

    @Override
    public boolean isGreaterThan(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) <= 0) // start <= val
            return false;
        if (end == null || end.compareTo(val) <= 0) // end <= val
            return false;
        return true;
    }

    @Override
    public boolean isGreaterOrContains(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) < 0) // start < val
            return false;
        if (end == null || end.compareTo(val) <= 0) // end <= val
            return false;
        return true;
    }

    @Override
    public int compareTo(IRange<T> o) {
        if (o == null) {
            if (start == null && end == null)
                return 0;
            return -1;
        }
        T start2 = o.getStart();
        if (start != start2) {
            if (start == null)
                return -1;
            if (start2 == null)
                return 1;
            int cmp = start.compareTo(start2);
            if (cmp != 0)
                return cmp;
        }
        T stop2 = o.getStop();
        T end2 = o.getEnd();
        if (stop2 != null) { // o is start-stop
            if (end == stop2)
                return -1;
            if (end == null)
                return 1;
            int cmp = end.compareTo(stop2);
            if (cmp > 0) // end > stop2
                // "...9.000001") vs "...9"] ?
                return /* 0 or */ 1;
            return -1;
        } else {
            if (end == end2)
                return 0;
            if (end == null)
                return 1;
            if (end2 == null)
                return -1;
            int cmp = end.compareTo(end2);
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(end, start);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        StartEndRange<T> other = (StartEndRange<T>) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }

    @Override
    public String toString() {
        return "[ " + start + ", " + end + " )";
    }

}
