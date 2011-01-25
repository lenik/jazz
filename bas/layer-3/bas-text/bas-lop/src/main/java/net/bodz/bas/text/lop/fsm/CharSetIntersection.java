package net.bodz.bas.text.lop.fsm;

import net.bodz.bas.util.exception.IllegalUsageException;
import net.bodz.bas.util.exception.UnexpectedException;

public final class CharSetIntersection
        extends AbstractCharSet {

    private final CharSet a;
    private final CharSet b;
    private final boolean _abstract;
    private final int begin;
    private final int end;

    public CharSetIntersection(CharSet a, CharSet b) {
        if (a == null)
            throw new NullPointerException("a");
        if (b == null)
            throw new NullPointerException("b");
        if (a.intersects(b) == NONE)
            throw new IllegalUsageException(String.format("CharSets are not intersected: %s ^ %s", a, b));
        this._abstract = a.isAbstract() || b.isAbstract();
        this.a = a;
        this.b = b;
        begin = Math.max(a.getBegin(), b.getBegin());
        end = Math.min(a.getEnd(), b.getEnd());
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
        if (begin <= x && x < end)
            return a.contains(x) && b.contains(x);
        else
            return false;
    }

    @Override
    public CharSet reduce() {
        CharSet _a = a.reduce();
        if (_a == null)
            return null;
        CharSet _b = b.reduce();
        if (_b == null)
            return null;
        switch (_a.intersects(_b)) {
        case NONE:
            return null;
        case ALL_THIS:
            return _a;
        case ALL_THAT:
            return _b;
        case EQUALS:
            return _a;
        }
        CharSet x = new CharSetIntersection(_a, _b)._reduce();
        if (x != null && x.equals(this))
            return this;
        return x;
    }

    private CharSet _reduce() {
        return super.reduce();
    }

    @Override
    public int intersects(CharSet that) {
        // (A^B) ^X => (A^X) ^ (B^X)
        int at = a.intersects(that);
        int bt = b.intersects(that);
        if (at == NONE || bt == NONE)
            return NONE;
        if (at == UNKNOWN || bt == UNKNOWN)
            return UNKNOWN;
        if (at == PARTIAL || bt == PARTIAL)
            return PARTIAL;
        switch (at) {
        case EQUALS: // A=X ==> X^(B^X) = B^X
            if (bt == ALL_THAT) // B^X=X ==> X
                return EQUALS;
            return bt;
        case ALL_THIS: // A<X, A^X=A, ==> A^(B^X) = A^B < X
            return ALL_THIS;
        case ALL_THAT: // A>X, A^X=X, ==> X^(B^X) = B^X
            return bt;
        }
        throw new UnexpectedException();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CharSetIntersection) {
            CharSetIntersection x = (CharSetIntersection) obj;
            return a.equals(x.a) && b.equals(x.b);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 0x20d52cad;
        // if (a != null)
        hash ^= a.hashCode();
        // if (b != null)
        hash ^= b.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return String.format("(%s^%s)", a, b);
    }

}
