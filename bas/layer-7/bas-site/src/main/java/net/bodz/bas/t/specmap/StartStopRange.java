package net.bodz.bas.t.specmap;

import java.util.Objects;

public class StartStopRange<T extends Comparable<T>>
        implements
            IMutableRange<T> {

    public T start;
    public T stop;

    public StartStopRange(T start, T stop) {
        this.start = start;
        this.stop = stop;
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
        return stop;
    }

    @Override
    public void setStop(T stop) {
        this.stop = stop;
    }

    @Override
    public T getEnd() {
        return null;
    }

    @Override
    public void setEnd(T end) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isStartEnd() {
        return false;
    }

    @Override
    public boolean contains(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) > 0) // start > val
            return false;
        if (stop != null && stop.compareTo(val) < 0) // stop < val
            return false;
        return true;
    }

    @Override
    public boolean contains(IRange<T> range) {
        if (range == null)
            return start == null && stop == null;
        T start2 = range.getStart();
        T stop2 = range.getStop();
        T end2 = range.getEnd();
        if (stop2 != null)
            return containsStartStop(start2, stop2);
        if (end2 != null)
            return containsStartEnd(start2, end2);
        if (stop != null)
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
            if (stop != null && stop.compareTo(start2) < 0) // stop < start2
                return false;
        }
        if (end2 == null) {
            if (stop != null)
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
            if (stop != null && stop.compareTo(start2) < 0) // stop <= start2
                return false;
        }
        if (stop2 == null) {
            if (stop != null)
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
        if (stop == null || stop.compareTo(val) >= 0) // stop >= val
            return false;
        return true;
    }

    @Override
    public boolean isLessOrContains(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) > 0) // start > val
            return false;
        if (stop == null || stop.compareTo(val) > 0) // stop > val
            return false;
        return true;
    }

    @Override
    public boolean isGreaterThan(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) <= 0) // start <= val
            return false;
        if (stop == null || stop.compareTo(val) <= 0) // stop <= val
            return false;
        return true;
    }

    @Override
    public boolean isGreaterOrContains(T val) {
        if (val == null)
            throw new NullPointerException("val");
        if (start != null && start.compareTo(val) < 0) // start < val
            return false;
        if (stop == null || stop.compareTo(val) < 0) // stop < val
            return false;
        return true;
    }

    @Override
    public int compareTo(IRange<T> o) {
        if (o == null) {
            if (start == null && stop == null)
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
        T end2 = o.getEnd();
        if (stop != end2) {
            if (stop == null)
                return 1;
            if (end2 == null)
                return -1;
            int cmp = stop.compareTo(end2);
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stop, start);
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
        StartStopRange<T> other = (StartStopRange<T>) obj;
        return Objects.equals(stop, other.stop) && Objects.equals(start, other.start);
    }

    @Override
    public String toString() {
        return "[ " + start + ", " + stop + " ]";
    }

}
