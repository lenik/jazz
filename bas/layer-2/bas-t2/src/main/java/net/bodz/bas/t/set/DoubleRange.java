package net.bodz.bas.t.set;

import net.bodz.bas.err.ParseException;

public class DoubleRange {

    public double start;
    public double end;

    public DoubleRange(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public static DoubleRange parse(String s)
            throws ParseException {
        try {
            int colon = s.indexOf(':');
            if (colon == -1) {
                double point = Double.parseDouble(s);
                return new DoubleRange(point, point + 1);
            }
            String startStr = s.substring(0, colon);
            String endStr = s.substring(colon + 1);
            double start = startStr.isEmpty() ? Double.MIN_VALUE : Double.parseDouble(startStr);
            double end = endStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(endStr);
            return new DoubleRange(start, end);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "DoubleRange[" + start + "," + end + ")";
    }

}
