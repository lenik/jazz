package net.bodz.bas.text.lop.fsm;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.text.util.StringPrep;

/**
 * @test {@link CharRangeTest}
 */
public class CharRange
        extends _CharSet {

    private final int begin;
    private final int end;

    /**
     * @param first
     *            inclusive
     * @param last
     *            inclusive
     */
    public CharRange(char first, char last) {
        this(first, last + 1);
    }

    /**
     * Empty range is allowed.
     * 
     * @param begin
     *            inclusive
     * @param end
     *            exclusive
     */
    public CharRange(int begin, int end) {
        if (begin > end)
            throw new IllegalArgumentException("begin>end");
        this.begin = begin;
        this.end = end;
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean contains(int x) {
        return begin <= x && x < end;
    }

    @Override
    public CharSet reduce() {
        switch (end - begin) {
        case 0:
            return null;
        case 1:
            return new Char(begin);
        }
        return this;
    }

    @Override
    public int intersects(CharSet that) {
        int thatBegin = that.getBegin();
        int thatEnd = that.getEnd();
        if (thatBegin >= end)
            return NONE;
        if (thatEnd <= begin)
            return NONE;
        if (that instanceof CharRange) {
            boolean allThat = begin <= thatBegin && thatEnd <= end;
            boolean allThis = thatBegin <= begin && end <= thatEnd;
            if (allThis)
                if (allThat)
                    return EQUALS;
                else
                    return ALL_THIS;
            else if (allThat)
                return ALL_THAT;
            else
                return PARTIAL;
        }
        int n = 0;
        for (int ch = begin; ch < end; ch++)
            if (that.contains(ch))
                n++;
        if (n == 0)
            return NONE;
        if (n == end - begin)
            return ALL_THAT;
        return PARTIAL;
    }

    @Override
    public SplitResult split(CharSet that) {
        assert intersects(that) != NONE;
        if (that instanceof Char) {
            if (end - begin == 1)
                return new SplitResult(null, null, that);
            int thatCh = that.getBegin();
            List<CharSet> thisOnly = new ArrayList<CharSet>(2);
            if (begin < thatCh)
                thisOnly.add(new CharRange(begin, thatCh).reduce());
            if (thatCh + 1 < end)
                thisOnly.add(new CharRange(thatCh + 1, end).reduce());
            return new SplitResult(thisOnly.toArray(new CharSet[0]), null, that);
        }
        if (that instanceof CharRange) {
            int thatBegin = that.getBegin();
            int thatEnd = that.getEnd();
            int cBegin = Math.max(begin, thatBegin);
            int cEnd = Math.min(end, thatEnd);
            assert cBegin < cEnd;
            List<CharSet> thisOnly = new ArrayList<CharSet>(2);
            List<CharSet> thatOnly = new ArrayList<CharSet>(2);
            if (begin < cBegin)
                thisOnly.add(new CharRange(begin, cBegin).reduce());
            if (thatBegin < cBegin)
                thatOnly.add(new CharRange(thatBegin, cBegin).reduce());
            if (cEnd < end)
                thisOnly.add(new CharRange(cEnd, end).reduce());
            if (cEnd < thatEnd)
                thatOnly.add(new CharRange(cEnd, thatEnd).reduce());
            CharSet[] thisv = thisOnly.toArray(new CharSet[0]);
            CharSet[] thatv = thatOnly.toArray(new CharSet[0]);
            return new SplitResult(thisv, thatv, new CharRange(cBegin, cEnd).reduce());
        }
        return super.split(that);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CharRange) {
            CharRange x = (CharRange) obj;
            return begin == x.begin && end == x.end;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 0xfeab18f0;
        hash ^= begin;
        hash <<= 8;
        hash ^= end;
        return hash;
    }

    @Override
    public String toString() {
        String c = StringPrep.escape(begin);
        switch (end - begin) {
        case 0:
            return "[]";
        case 1:
            return c;
        }
        String d = StringPrep.escape(end - 1);
        return "[" + c + "-" + d + "]";
    }

}
