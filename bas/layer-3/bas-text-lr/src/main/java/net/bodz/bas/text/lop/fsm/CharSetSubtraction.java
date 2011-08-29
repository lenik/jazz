package net.bodz.bas.text.lop.fsm;

import net.bodz.bas.err.UnexpectedException;

public final class CharSetSubtraction
        extends AbstractCharSet {

    private final CharSet a;
    private final CharSet b;
    private final int ab;
    private final boolean _abstract;
    private final int begin;
    private final int end;

    public CharSetSubtraction(CharSet a, CharSet b) {
        if (a == null)
            throw new NullPointerException("a");
        if (b == null)
            throw new NullPointerException("b");
        this.a = a;
        this.b = b;
        switch (ab = a.intersects(b)) {
        case NONE:
        case ALL_THIS:
        case EQUALS:
            _abstract = false;
            begin = end = 0;
            break;
        case UNKNOWN:
        case PARTIAL:
        case ALL_THAT:
            _abstract = a.isAbstract();
            begin = a.getBegin();
            end = a.getEnd();
            break;
        default:
            throw new UnexpectedException();
        }
    }

    @Override
    public boolean isAbstract() {
        return _abstract;
    }

    @Override
    public int getBegin() {
        return begin;
    }

    @Override
    public int getEnd() {
        return end;
    }

    @Override
    public boolean contains(int x) {
        return a.contains(x) && !b.contains(x);
    }

    @Override
    public CharSet reduce() {
        CharSet _a = a.reduce();
        if (_a == null)
            return null;
        CharSet _b = b.reduce();
        if (_b == null)
            return _a;
        switch (_a.intersects(_b)) {
        case NONE:
            return _a;
        case ALL_THIS:
            return null;
        case ALL_THAT:
            break;
        case EQUALS:
            return null;
        }
        CharSet x = new CharSetSubtraction(_a, _b)._reduce();
        if (x != null && x.equals(this))
            return this;
        return x;
    }

    private CharSet _reduce() {
        return super.reduce();
    }

    @Override
    public int intersects(CharSet that) {
        int at = a.intersects(that);
        int bt = b.intersects(that);
        if (at == NONE)
            return NONE;
        if (bt == NONE)
            return at;
        if (at == UNKNOWN || bt == UNKNOWN)
            return UNKNOWN;
        if (at == PARTIAL || bt == PARTIAL)
            return PARTIAL;
        switch (at) {
        case EQUALS: // (A-B)^X, A=X ==> (X-B)^X => X-B^X
            switch (bt) {
            case EQUALS: // B=X, X-X^X = {}
            case ALL_THAT: // B>X, B^X=X ==> X-X = {}
                return NONE;
            case ALL_THIS: // B<X, B^X=B ==> X-B = A-B
                return testSubtract(ab);
            }
            break;
        case ALL_THIS: // (A-B)^X, A<X, A^X=A ==> A - B^X
            switch (bt) {
            case EQUALS: // B=X, ==> A-X
            case ALL_THAT: // B>X, B^X=X ==> A-X
                return testSubtract(a.intersects(that));
            case ALL_THIS: // B<X, B^X=B ==> A-B
                return testSubtract(ab);
            }
            break;
        case ALL_THAT: // (A-B)^X, A>X, A^X=X ==> X - B^X
            switch (bt) {
            case EQUALS: // B=X, ==> X-X = {}
            case ALL_THAT: // B>X, B^X=X ==> X-X = {}
                return NONE;
            case ALL_THIS: // B<X, B^X=B ==> X - B
                return testSubtract(that.intersects(b));
            }
        }
        throw new UnexpectedException();
    }

    /**
     * test of A-B by A^B
     */
    private static int testSubtract(int ab) {
        switch (ab) {
        case NONE:
            return ALL_THIS;
        case UNKNOWN:
            return UNKNOWN;
        case PARTIAL:
            return PARTIAL; // strictly, this should be UNKNOWN.
        case EQUALS:
        case ALL_THIS:
            return NONE;
        case ALL_THAT:
            return PARTIAL;
        }
        throw new IllegalArgumentException("Bad intersection code: " + ab);
    }

    @Override
    public String toString() {
        return String.format("((%s)-(%s))", a, b);
    }

}
