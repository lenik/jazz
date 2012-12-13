package net.bodz.bas.t.pojo;

public class ClosedRange<T extends Comparable<T>>
        extends Range<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isEmpty() {
        if (begin == null || end == null)
            return false;
        return begin.compareTo(end) > 0;
    }

    @Override
    public boolean contains(T point) {
        if (begin != null) // begin <= point.
            if (begin.compareTo(point) > 0)
                return false;
        if (end != null) // point <= end
            if (point.compareTo(end) > 0)
                return false;
        return true;
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
            sb.append(end + "]");

        return sb.toString();
    }

}
