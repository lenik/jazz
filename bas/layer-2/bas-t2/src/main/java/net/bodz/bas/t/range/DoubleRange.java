package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;

public class DoubleRange
        extends AbstractRange<DoubleRange, Double> {

    private static final long serialVersionUID = 1L;

    public DoubleRange() {
        super();
    }

    public DoubleRange(Double start, Double end) {
        super(start, end);
    }

    @Override
    public DoubleRange create(Double start, Double end) {
        return new DoubleRange(start, end);
    }

    @Override
    public Double parseValue(String s)
            throws ParseException {
        try {
            return new Double(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Double preceding(Double val) {
        return val - Double.MIN_VALUE;
    }

    @Override
    public Double successor(Double val) {
        return val + Double.MIN_VALUE;
    }

    @Override
    public String toString() {
        return "DoubleRange[" + start + "," + end + ")";
    }

}
