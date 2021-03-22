package net.bodz.bas.text.trie.t;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CharSeqIterable
        implements
            Iterable<Character> {

    CharSequence cs;
    int start, end;

    public CharSeqIterable(CharSequence cs) {
        this(cs, 0, cs.length());
    }

    public CharSeqIterable(CharSequence cs, int start, int end) {
        this.cs = cs;
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Character> iterator() {
        return new CharSeqIterator();
    }

    class CharSeqIterator
            implements
                Iterator<Character> {

        int pos = start;

        @Override
        public boolean hasNext() {
            return pos < end;
        }

        @Override
        public Character next() {
            if (pos >= end)
                throw new NoSuchElementException();
            return cs.charAt(pos++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}