package net.bodz.bas.text.lop.fsm;

import net.bodz.bas.exceptions.UnexpectedException;

public final class CharSetInv
        extends _CharSet {

    private final CharSet orig;

    public CharSetInv(CharSet set) {
        if (set == null)
            throw new NullPointerException("charSet");
        this.orig = set;
    }

    @Override
    public boolean isAbstract() {
        return orig.isAbstract();
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
    public boolean contains(int x) {
        return !orig.contains(x);
    }

    @Override
    public int intersects(CharSet that) {
        switch (orig.intersects(that)) {
        case UNKNOWN:
            return UNKNOWN;
        case NONE:
            // if (that.isEmpty())return NONE;
            return ALL_THAT;
        case EQUALS:
            return NONE;
        case ALL_THIS:
            return PARTIAL;
        case ALL_THAT:
            return NONE;
        case PARTIAL:
            return PARTIAL;
        }
        throw new UnexpectedException();
    }

    @Override
    public CharSet reduce() {
        if (orig instanceof CharSetInv) {
            CharSet offset = ((CharSetInv) orig).orig;
            return offset.reduce();
        }
        return super.reduce();
    }

    @Override
    public String toString() {
        return String.format("(^%s)", orig);
    }

}
