package net.bodz.bas.t.range;

import java.io.Serializable;

import net.bodz.bas.err.ParseException;

public abstract class AbstractRange<self_t, val_t>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public val_t start;
    public val_t end;

    boolean startInclusive = true;
    boolean endInclusive;

    public AbstractRange() {
    }

    public AbstractRange(val_t start, val_t end) {
        this.start = start;
        this.end = end;
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

    public boolean isEndInclusive() {
        return endInclusive;
    }

    public void setEndInclusive(boolean endInclusive) {
        this.endInclusive = endInclusive;
    }

    public val_t getFrom() {
        if (start == null)
            return null;
        else if (startInclusive)
            return start;
        else
            return successor(start);
    }

    public void setFrom(val_t from) {
        if (from == null)
            this.start = null;
        else if (startInclusive)
            this.start = from;
        else
            this.start = preceding(from);
    }

    public val_t getTo() {
        if (end == null)
            return null;
        else if (endInclusive)
            return end;
        else
            return preceding(end);
    }

    public void setTo(val_t to) {
        if (to == null)
            this.end = null;
        else if (endInclusive)
            this.end = to;
        else
            this.end = successor(to);
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

    public abstract val_t preceding(val_t val);

    public abstract val_t successor(val_t val);

}
