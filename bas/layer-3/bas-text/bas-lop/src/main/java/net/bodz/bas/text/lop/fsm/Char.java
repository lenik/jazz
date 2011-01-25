package net.bodz.bas.text.lop.fsm;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.string.StringEscape;
 
public class Char
        extends AbstractCharSet {

    private final int ch;

    public Char(int ch) {
        this.ch = ch;
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public int getBegin() {
        return ch;
    }

    @Override
    public int getEnd() {
        return ch + 1;
    }

    @Override
    public boolean contains(int x) {
        return ch == x;
    }

    @Override
    public int intersects(CharSet that) {
        if (that.contains(ch)) {
            if (that instanceof Char)
                return EQUALS;
            if (that instanceof CharRange)
                if (that.getEnd() - that.getBegin() == 1)
                    return EQUALS;
            return ALL_THIS;
        }
        return NONE;
    }

    @Override
    public SplitResult split(CharSet that) {
        assert that.contains(ch);
        if (that instanceof Char)
            return new SplitResult(null, null, this);
        if (that instanceof CharRange) {
            int thatBegin = that.getBegin();
            int thatEnd = that.getEnd();
            if (thatEnd - thatBegin == 1)
                return new SplitResult(null, null, this);
            List<CharSet> thatOnly = new ArrayList<CharSet>(2);
            if (thatBegin < ch)
                thatOnly.add(new CharRange(thatBegin, ch).reduce());
            if (thatEnd > ch + 1)
                thatOnly.add(new CharRange(ch + 1, thatEnd).reduce());
            return new SplitResult(null, thatOnly.toArray(new CharSet[0]), this);
        }
        CharSetSubtraction thatOnly = new CharSetSubtraction(that, this);
        return new SplitResult(null, thatOnly, this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Char)
            return ch == ((Char) obj).ch;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 0xa4a0eb0e + ch;
        return hash;
    }

    @Override
    public String toString() {
        return StringEscape.java(ch);
    }

}
