package net.bodz.bas.text.lop.fsm;

import net.bodz.bas.util.exception.UnexpectedException;

public abstract class AbstractCharSet
        implements CharSet {

    @Override
    public boolean isAbstract() {
        return true;
    }

    @Override
    public int getBegin() {
        return Character.MIN_CODE_POINT;
    }

    @Override
    public int getEnd() {
        return Character.MAX_CODE_POINT;
    }

    @Override
    public CharSet reduce() {
        if (getBegin() >= getEnd())
            return null;
        return this;
    }

    @Override
    public int intersects(CharSet that) {
        int begin = getBegin();
        int end = getEnd();
        if (that.getBegin() >= end || that.getEnd() <= begin)
            return NONE;
        return UNKNOWN;
    }

    @Override
    public SplitResult split(CharSet that) {
        CharSet thisOnly;
        CharSet thatOnly;
        switch (intersects(that)) {
        case NONE:
            return new SplitResult(this, that, null);
        case ALL_THIS:
            thatOnly = new CharSetSubtraction(that, this).reduce();
            return new SplitResult(null, thatOnly, this);
        case ALL_THAT:
            thisOnly = new CharSetSubtraction(this, that).reduce();
            return new SplitResult(thisOnly, null, that);
        case EQUALS:
            return new SplitResult(null, null, this);
        case UNKNOWN:
        case PARTIAL:
            thisOnly = new CharSetSubtraction(this, that).reduce();
            thatOnly = new CharSetSubtraction(that, this).reduce();
            CharSet common = new CharSetIntersection(this, that).reduce();
            return new SplitResult(thisOnly, thatOnly, common);
        }
        throw new UnexpectedException();
    }

    @Override
    public int compareTo(CharSet that) {
        if (that == null)
            throw new NullPointerException();
        return getBegin() - that.getBegin();
    }

}
