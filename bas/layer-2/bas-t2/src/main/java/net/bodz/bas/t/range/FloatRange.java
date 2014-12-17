package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;

public class FloatRange {

    public float start;
    public float end;

    public FloatRange(float start, float end) {
        this.start = start;
        this.end = end;
    }

    public static FloatRange parse(String s)
            throws ParseException {
        try {
            int colon = s.indexOf(':');
            if (colon == -1) {
                float point = Float.parseFloat(s);
                return new FloatRange(point, point + 1);
            }
            String startStr = s.substring(0, colon);
            String endStr = s.substring(colon + 1);
            float start = startStr.isEmpty() ? Float.MIN_VALUE : Float.parseFloat(startStr);
            float end = endStr.isEmpty() ? Float.MAX_VALUE : Float.parseFloat(endStr);
            return new FloatRange(start, end);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(float end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "FloatRange[" + start + "," + end + ")";
    }

}
