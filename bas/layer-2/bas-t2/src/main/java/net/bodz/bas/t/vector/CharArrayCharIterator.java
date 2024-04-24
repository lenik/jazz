package net.bodz.bas.t.vector;

import java.util.NoSuchElementException;

public class CharArrayCharIterator
        implements
            CharIterator {

    private final char[] chars;
    private final int end;
    private int pos;

    public CharArrayCharIterator(char[] chars, int start, int end) {
        if (chars == null)
            throw new NullPointerException("chars");
        this.chars = chars;
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
        char ch = chars[pos++];
        return ch;
    }

}
