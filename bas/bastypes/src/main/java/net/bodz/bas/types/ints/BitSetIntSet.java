package net.bodz.bas.types.ints;

import java.util.BitSet;
import java.util.NoSuchElementException;

public class BitSetIntSet extends _IntSet {

    private final BitSet bits;
    private int          cardinality;

    public BitSetIntSet() {
        this.bits = new BitSet();
    }

    public BitSetIntSet(int initialCapacity) {
        this.bits = new BitSet(initialCapacity);
    }

    @Override
    public int size() {
        return cardinality;
    }

    /**
     * @param begin
     *            index to add
     * @param end
     *            index (exclusive) to add
     */
    public void add(int begin, int end) {
        bits.set(begin, end);
    }

    /**
     * @param begin
     *            index to remove
     * @param end
     *            index (exclusive) to remove
     */
    public void remove(int begin, int end) {
        bits.clear(begin, end);
    }

    @Override
    public boolean add(int index) {
        boolean exists = bits.get(index);
        if (exists)
            return false;
        bits.set(index);
        cardinality++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        boolean exists = bits.get(index);
        if (!exists)
            return false;
        bits.clear(index);
        cardinality--;
        return true;
    }

    @Override
    public boolean contains(int index) {
        return bits.get(index);
    }

    @Override
    public void clear() {
        bits.clear();
    }

    class Iter implements IntIterator {

        int i       = bits.nextSetBit(0);
        int current = -1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public int next() {
            if (i == -1)
                throw new NoSuchElementException();
            current = i;
            i = bits.nextSetBit(i);
            return current;
        }

        @Override
        public void remove() {
            if (current == -1)
                throw new IllegalStateException();
            BitSetIntSet.this.remove(current);
            current = -1;
        }

    }

    @Override
    public IntIterator iterator() {
        return new Iter();
    }

    @Override
    public int[] toArray() {
        int[] array = new int[cardinality];
        int i = -1;
        int j = 0;
        while ((i = bits.nextSetBit(i + 1)) >= 0) {
            assert j < cardinality : "inconsistency"; //$NON-NLS-1$
            array[j++] = i;
        }
        return array;
    }

}
