package net.bodz.bas.t.range;

import java.io.Serializable;
import java.util.Comparator;

import net.bodz.bas.err.ParseException;

public abstract class AbstractRange<self_t, val_t>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Comparator<? super val_t> order;

    public val_t start;
    public val_t end;

    boolean startInclusive = true;
    boolean endInclusive;

    public AbstractRange(Comparator<? super val_t> order) {
        this.order = order;
    }

    public AbstractRange(Comparator<? super val_t> order, val_t start, val_t end) {
        this.order = order;
        this.start = start;
        this.end = end;
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
        return start != null && !startInclusive;
    }

    public boolean isHasEndIncl() {
        return end != null && endInclusive;
    }

    public boolean isHasEndExcl() {
        return end != null && !endInclusive;
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
    }

    public val_t getEnd() {
        return end;
    }

    public void setEnd(val_t end) {
        this.end = end;
    }

    public boolean isStartInclusive() {
        return startInclusive;
    }

    public void setStartInclusive(boolean startInclusive) {
        this.startInclusive = startInclusive;
    }

    public boolean isStartExclusive() {
        return !startInclusive;
    }

    public void setStartExclusive(boolean startExclusive) {
        this.startInclusive = !startExclusive;
    }

    public boolean isEndInclusive() {
        return endInclusive;
    }

    public void setEndInclusive(boolean endInclusive) {
        this.endInclusive = endInclusive;
    }

    public boolean isEndExclusive() {
        return !endInclusive;
    }

    public void setEndExclusive(boolean endExclusive) {
        this.endInclusive = !endExclusive;
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
        if (from == null) {
            this.start = null;
            return;
        }
        if (startInclusive) {
            this.start = from;
            return;
        }
        val_t beforeFrom = preceding(from);
        if (beforeFrom == null) {
            // allow for -inf
            this.start = null;
            return;
        }
        this.start = beforeFrom;
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
     *            Set to be right-open for <code>null</code> to. The same as if <code>to</code> is
     *            too large.
     */
    public void setTo(val_t to) {
        if (to == null) {
            this.end = null;
            return;
        }
        if (endInclusive) {
            this.end = to;
            return;
        }
        val_t afterTo = successor(to);
        if (afterTo == null) {
            // allow for +inf.
            this.end = null;
            return;
        }
        this.end = afterTo;
    }

    public self_t parse(String s)
            throws ParseException {
        int colon = s.indexOf(':');
        if (colon == -1) {
            val_t point = parseValue(s);
            val_t succ = successor(point);
            return create(point, succ);
        }
        String startStr = s.substring(0, colon);
        String endStr = s.substring(colon + 1);
        val_t start = startStr.isEmpty() ? null : parseValue(startStr);
        val_t end = endStr.isEmpty() ? null : parseValue(endStr);
        return create(start, end);
    }

    public abstract self_t create(val_t start, val_t end);

    public abstract val_t parseValue(String s)
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
        if (start == null)
            start = val;
        else if (order.compare(val, start) < 0)
            start = val;

        if (end == null)
            end = val;
        else if (order.compare(end, val) < 0)
            end = val;
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

}
