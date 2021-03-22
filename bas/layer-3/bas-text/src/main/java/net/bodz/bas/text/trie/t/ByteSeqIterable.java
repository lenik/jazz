package net.bodz.bas.text.trie.t;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ByteSeqIterable
        implements
            Iterable<Byte> {

    ByteSequence seq;
    int start, end;

    public ByteSeqIterable(ByteSequence seq) {
        this(seq, 0, seq.length());
    }

    public ByteSeqIterable(ByteSequence seq, int start, int end) {
        this.seq = seq;
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Byte> iterator() {
        return new ByteSeqIterator();
    }

    class ByteSeqIterator
            implements
                Iterator<Byte> {

        int pos = start;

        @Override
        public boolean hasNext() {
            return pos < end;
        }

        @Override
        public Byte next() {
            if (pos >= end)
                throw new NoSuchElementException();
            return seq.byteAt(pos++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}