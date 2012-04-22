package net.bodz.art.installer.util;

import java.nio.BufferUnderflowException;
import java.util.Arrays;

import net.bodz.bas.err.OutOfDomainException;

public class IntAddressBuffer {

    static final int alignment = 8;

    static int align(int n) {
        return (n + alignment - 1) / alignment * alignment;
    }

    private int[] array;
    private int   size;

    IntAddressBuffer(int[] array) {
        this.array = array;
        this.size = array.length;
    }

    public static IntAddressBuffer wrap(int[] array) {
        return new IntAddressBuffer(array);
    }

    public IntAddressBuffer() {
        this(0, 4);
    }

    public IntAddressBuffer(int initialSize, int initialCapacity) {
        if (initialSize < 0)
            throw new OutOfDomainException("initialSize", initialSize, 0); //$NON-NLS-1$
        if (initialSize > initialCapacity)
            initialCapacity = initialSize;
        array = new int[initialCapacity];
        size = initialSize;
    }

    void checkIndex(int index) {
        if (index < 0)
            throw new OutOfDomainException("index", index, 0); //$NON-NLS-1$
        if (index >= size)
            throw new OutOfDomainException("index", index, size); //$NON-NLS-1$
    }

    public int size() {
        return size;
    }

    public void resize(int newSize) {
        if (newSize < 0)
            throw new OutOfDomainException("size", newSize, 0); //$NON-NLS-1$
        if (newSize > array.length) {
            int newCapacity = align(newSize);
            array = Arrays.copyOf(array, newCapacity);
        }
        this.size = newSize;
    }

    public void push(int val) {
        resize(size + 1);
        array[size - 1] = val;
    }

    public void pop() {
        if (size == 0)
            throw new BufferUnderflowException();
        resize(size - 1);
    }

    public int get(int index) {
        checkIndex(index);
        return array[index];
    }

    public void set(int index, int value) {
        checkIndex(index);
        array[index] = value;
    }

    public void add(int index, int value) {
        checkIndex(index);
        array[index] += value;
    }

    public void and(int index, int value) {
        checkIndex(index);
        array[index] &= value;
    }

    public void or(int index, int value) {
        checkIndex(index);
        array[index] |= value;
    }

    public void xor(int index, int value) {
        checkIndex(index);
        array[index] ^= value;
    }

    public int get() {
        return array[size - 1];
    }

    public void set(int val) {
        array[size - 1] = val;
    }

    public void add(int val) {
        array[size - 1] += val;
    }

    public void and(int val) {
        array[size - 1] &= val;
    }

    public void or(int val) {
        array[size - 1] |= val;
    }

    public void xor(int val) {
        array[size - 1] ^= val;
    }

    public int[] copy() {
        int[] copy = Arrays.copyOf(array, size);
        return copy;
    }

    /**
     * format as <code>a.b.c</code>, or <code>.</code> if size is 0.
     */
    @Override
    public String toString() {
        if (size == 0)
            return "."; //$NON-NLS-1$
        StringBuffer buf = new StringBuffer(size * 10);
        for (int i = 0; i < size; i++) {
            if (i != 0)
                buf.append('.');
            buf.append(array[i]);
        }
        return buf.toString();
    }

}
