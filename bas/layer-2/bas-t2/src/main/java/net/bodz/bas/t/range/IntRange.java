package net.bodz.bas.t.range;

public class IntRange
        extends AbstractRange<IntRange, Integer> {

    private static final long serialVersionUID = 1L;

    public IntRange() {
        super();
    }

    public IntRange(Integer start, Integer end) {
        super(start, end);
    }

    @Override
    public IntRange create(Integer start, Integer end) {
        return new IntRange(start, end);
    }

    @Override
    public Integer parseValue(String s)
            throws NumberFormatException {
        return new Integer(s);
    }

    @Override
    public Integer preceding(Integer val) {
        return val--;
    }

    @Override
    public Integer successor(Integer val) {
        return val++;
    }

    @Override
    public String toString() {
        return "IntRange[" + start + "," + end + ")";
    }

}
