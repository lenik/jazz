package net.bodz.bas.t.pojo;

import java.io.Serializable;

public class Range<T extends Comparable<T>>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    T begin;
    T end;

    public Range() {
    }

    public Range(T begin, T end) {
        this.begin = begin;
        this.end = end;
    }

    public T getBegin() {
        return begin;
    }

    public void setBegin(T begin) {
        this.begin = begin;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    public boolean isEmpty() {
        if (begin == null || end == null)
            return false;
        return begin.compareTo(end) >= 0;
    }

    public boolean contains(T point) {
        if (begin != null) // begin <= point.
            if (begin.compareTo(point) > 0)
                return false;
        if (end != null) // point < end
            if (point.compareTo(end) >= 0)
                return false;
        return true;
    }

    public boolean containsAll(Iterable<T> points) {
        if (begin == null && end == null)
            return true;
        for (T point : points)
            if (!contains(point))
                return false;
        return true;
    }

    public boolean containsAny(Iterable<T> points) {
        for (T point : points)
            if (contains(point))
                return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0x4938fab3;
        if (begin != null)
            hash = hash * 17 + begin.hashCode();
        if (end != null)
            hash = hash * 17 + end.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (begin == null)
            sb.append("(-inf");
        else
            sb.append("[" + begin);

        sb.append(", ");

        if (end == null)
            sb.append("inf)");
        else
            sb.append(end + ")");

        return sb.toString();
    }

}
