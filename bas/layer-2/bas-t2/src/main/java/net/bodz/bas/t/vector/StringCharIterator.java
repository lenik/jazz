package net.bodz.bas.t.vector;

import java.util.NoSuchElementException;

public class StringCharIterator
        implements
            CharIterator {

    private final CharSequence s;
    private final int end;
    private int pos;

    public StringCharIterator(CharSequence s, int start, int end) {
        if (s == null)
            throw new NullPointerException("s");
        this.s = s;
        this.end = end;
        this.pos = start;
    }

    @Override
    public boolean hasNext() {
        return pos < end;
    }

    @Override
    public Character next() {
        return nextChar();
    }

    @Override
    public char nextChar() {
        if (pos >= end)
            throw new NoSuchElementException();
        char ch = s.charAt(pos++);
        return ch;
    }

}
