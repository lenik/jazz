package net.bodz.bas.t.range;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Function;

import net.bodz.bas.err.ParseException;

public abstract class Range<This, val_t>
        implements
            Serializable {

    private static final long serialVersionUID = 1L;

    private final Comparator<? super val_t> order;

    public val_t start;
    public val_t end;

    boolean startInclusive = true;
    boolean endInclusive = false;

    public Range(Comparator<? super val_t> order) {
        this.order = order;
    }

    public Range(Comparator<? super val_t> order, val_t start, val_t end) {
        this.order = order;
        this.start = start;
        this.end = end;
    }

    public Range(Comparator<? super val_t> order, boolean startInclusive, val_t start, boolean endInclusive,
            val_t end) {
        this.order = order;
        this.startInclusive = startInclusive;
        this.start = start;
        this.endInclusive = endInclusive;
        this.end = end;
    }

    @SuppressWarnings("unchecked")
    protected final This _this() {
        return (This) this;
    }

    public boolean isPoint() {
        return startInclusive && endInclusive && start == end && start != null;
    }

    public val_t getPointValue() {
        return start;
    }

    public void setPointValue(val_t val) {
        start = end = val;
        startInclusive = true;
        endInclusive = true;
    }

    @SuppressWarnings("unchecked")
    public This point(val_t val) {
        setPointValue(val);
        return (This) this;
    }

    public boolean isUnspecified() {
        return start == null && end == null;
    }

    public boolean isSpecified() {
        return start != null || end != null;
    }

    public boolean isUniOpen() {
        return (start != null) ^ (end != null);
    }

    public boolean isOpen() {
        return start == null || end == null;
    }

    public boolean isClosed() {
        return start != null && end != null;
    }

    public boolean isHasStart() {
        return start != null;
    }

    public boolean isHasEnd() {
        return end != null;
    }

    public boolean isHasStartIncl() {
        return start != null && startInclusive;
    }

    public boolean isHasStartExcl() {
        return start != null && ! startInclusive;
    }

    public boolean isHasEndIncl() {
        return end != null && endInclusive;
    }

    public boolean isHasEndExcl() {
        return end != null && ! endInclusive;
    }

    public boolean hasStart(boolean inclusive) {
        return start != null && startInclusive == inclusive;
    }

    public boolean hasEnd(boolean inclusive) {
        return end != null && endInclusive == inclusive;
    }

    public val_t getStart() {
        return start;
    }

    public void setStart(val_t start) {
        this.start = start;
        this.startInclusive = true;
    }

    public void setStartAfter(val_t start) {
        this.start = start;
        this.startInclusive = false;
    }

    public val_t getEnd() {
        return end;
    }

    public void setEnd(val_t end) {
        this.end = end;
        this.endInclusive = false;
    }

    public boolean isStartInclusive() {
        return startInclusive;
    }

    public void setStartInclusive(boolean startInclusive) {
        this.startInclusive = startInclusive;
    }

    public boolean isStartExclusive() {
        return ! startInclusive;
    }

    public void setStartExclusive(boolean startExclusive) {
        this.startInclusive = ! startExclusive;
    }

    public boolean isEndInclusive() {
        return endInclusive;
    }

    public void setEndInclusive(boolean endInclusive) {
        this.endInclusive = endInclusive;
    }

    public boolean isEndExclusive() {
        return ! endInclusive;
    }

    public void setEndExclusive(boolean endExclusive) {
        this.endInclusive = ! endExclusive;
    }

    public val_t getFrom() {
        if (start == null)
            return null;
        if (startInclusive)
            return start;
        val_t afterStart = successor(start);
        if (afterStart == null) {
            // allow -inf
            return null;
        }
        return afterStart;
    }

    public void setFrom(val_t from) {
        this.start = from;
        this.startInclusive = true;
    }

    /**
     * @return <code>null</code> if empty.
     */
    public val_t getTo() {
        if (end == null)
            return null;
        if (endInclusive)
            return end;
        val_t beforeEnd = preceding(end);
        if (beforeEnd == null) {
            // assert end >= start;
            return null;
        }
        return beforeEnd;
    }

    /**
     * @param to
     *            Set to be right-open for <code>null</code> to. The same as if <code>to</code> is too large.
     */
    public void setTo(val_t to) {
        this.end = to;
        this.endInclusive = true;
    }

    @SuppressWarnings("unchecked")
    public This parse(String s)
            throws ParseException {
        s = s.trim();

        if (s.isEmpty())
            throw new ParseException("unexpected empty string.");

        char startType = s.charAt(0);
        switch (startType) {
        case '[':
            this.setStartInclusive(true);
            break;
        case '(':
            this.setStartInclusive(false);
            break;
        default:
            val_t point = parseValue(s);
            setPointValue(point);
            return (This) this;
        }

        char endType = s.charAt(s.length() - 1);
        switch (endType) {
        case ']':
            this.setEndInclusive(true);
            break;
        case ')':
            this.setEndInclusive(false);
            break;
        default:
            throw new ParseException("Range should be in the format of ?START, END?.");
        }

        int comma = s.indexOf(',');
        if (comma == -1)
            throw new ParseException("Range should be in the format of ?START, END?.");

        String part1 = s.substring(1, comma).trim();
        String part2 = s.substring(comma + 1, s.length() - 1).trim();
        this.start = null;
        this.end = null;
        if (! part1.isEmpty())
            this.start = parseValue(part1);
        if (! part2.isEmpty())
            this.end = parseValue(part2);
        return (This) this;
    }

    public val_t parseValue(String s)
            throws ParseException {
        if (s == null)
            return null;
        String nullStr = nullStr();
        if (nullStr != null)
            if (s.equals(nullStr))
                return null;
        return parseNonNullValue(s);
    }

    public abstract val_t parseNonNullValue(String s)
            throws ParseException;

    /**
     * @return <code>null</code> if no preceding.
     */
    public abstract val_t preceding(val_t val);

    /**
     * @return <code>null</code> if no successor.
     */
    public abstract val_t successor(val_t val);

    public void include(val_t val) {
        if (start != null)
            if (order.compare(val, start) < 0)
                start = val;

        if (end != null)
            if (order.compare(end, val) < 0)
                end = val;
    }

    public boolean contains(val_t val) {
        if (start != null)
            if (order.compare(val, start) < 0)
                return false;

        if (end != null)
            if (order.compare(end, val) < 0)
                return false;

        return true;
    }

    public boolean contains(Range<?, val_t> o) {
        if (! contains(o.start))
            return false;
        if (! contains(o.end))
            return false;
        return true;
    }

    public boolean intersects(Range<?, val_t> o) {
        if (contains(o.start))
            return true;
        if (contains(o.end))
            return true;
        if (o.contains(start))
            return true;
        if (o.contains(end))
            return true;
        return false;
    }

    public String valueSql(val_t val) {
        if (val == null)
            return "null";
        return val.toString();
    }

    public String matchSql(String var) {
        if (isUnspecified())
            return "";
        if (isPoint())
            return var + " = " + valueSql(getPointValue());

        StringBuilder sb = new StringBuilder(100);
        if (start != null && end != null && startInclusive && endInclusive) {
            sb.append(" and ");
            sb.append(var);
            if (startInclusive)
                sb.append(" between ");
            sb.append(valueSql(start));
            sb.append(" and ");
            sb.append(valueSql(end));
        } else {
            if (start != null) {
                sb.append(" and ");
                sb.append(var);
                if (startInclusive)
                    sb.append(" >= ");
                else
                    sb.append(" > ");
                sb.append(valueSql(start));
            }
            if (end != null) {
                sb.append(" and ");
                sb.append(var);
                if (endInclusive)
                    sb.append(" <= ");
                else
                    sb.append(" < ");
                sb.append(valueSql(end));
            }
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + (endInclusive ? 1231 : 1237);
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + (startInclusive ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Range<?, ?> other = (Range<?, ?>) obj;

        if (start == null) {
            if (other.start != null)
                return false;
        } else if (! start.equals(other.start))
            return false;
        if (startInclusive != other.startInclusive)
            return false;

        if (end == null) {
            if (other.end != null)
                return false;
        } else if (! end.equals(other.end))
            return false;
        if (endInclusive != other.endInclusive)
            return false;

        return equalsPartial(other);
    }

    protected boolean equalsPartial(Range<?, ?> o) {
        return true;
    }

    public String format(Function<val_t, String> valueFormat) {
        StringBuilder sb = new StringBuilder(60);
        if (startInclusive)
            sb.append('[');
        else
            sb.append('(');

        String startText = valueFormat.apply(start);
        sb.append(startText);

        sb.append(", ");

        String endText = valueFormat.apply(end);
        sb.append(endText);

        if (endInclusive)
            sb.append(']');
        else
            sb.append(')');

        return sb.toString();
    }

    /**
     * @param val
     *            non-<code>null</code> value.
     */
    protected String format(val_t val) {
        return val.toString();
    }

    protected String nullStr() {
        return "null";
    }

    @Override
    public String toString() {
        return format((val_t val) -> val == null ? nullStr() : format(val));
    }

}
