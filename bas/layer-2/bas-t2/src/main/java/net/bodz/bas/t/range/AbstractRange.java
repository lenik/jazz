package net.bodz.bas.t.range;

import java.io.Serializable;

import net.bodz.bas.err.ParseException;

public abstract class AbstractRange<self_t, val_t>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public val_t start;
    public val_t end;

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

    public val_t getFrom() {
        return start;
    }

    public void setFrom(val_t from) {
        this.start = from;
    }

    public val_t getTo() {
        if (end == null)
            return null;
        else
            return preceding(end);
    }

    public void setTo(val_t to) {
        if (to == null)
            this.end = null;
        else
            this.end = successor(to);
    }

    public self_t parse(String s)
            throws ParseException {
        try {
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
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    public abstract self_t create(val_t start, val_t end);

    public abstract val_t parseValue(String s)
            throws NumberFormatException;

    public abstract val_t preceding(val_t val);

    public abstract val_t successor(val_t val);

}
