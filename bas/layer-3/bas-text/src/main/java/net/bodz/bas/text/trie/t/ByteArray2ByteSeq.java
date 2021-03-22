package net.bodz.bas.text.trie.t;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ByteArray2ByteSeq
        implements
            ByteSequence,
            Iterable<Byte> {

    byte[] array;
    int start;
    int end;

    public ByteArray2ByteSeq(byte[] array) {
        this(array, 0, array.length);
    }

    public ByteArray2ByteSeq(byte[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public int length() {
        return end - start;
    }

    @Override
    public byte byteAt(int index) {
        return array[start + index];
    }

    @Override
    public ByteSequence subSequence(int start, int end) {
        return new ByteArray2ByteSeq(array, this.start + start, this.start + end);
    }

    @Override
    public Iterator<Byte> iterator() {
        return new ByteArrayIterator();
    }

    class ByteArrayIterator
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
            return array[pos++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
