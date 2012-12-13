package net.bodz.bas.t.buffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

@Deprecated
public class CharRing
        extends AbstractRingBuffer<char[], Character> {

    public CharRing(int capacity) {
        super(capacity);
    }

    public CharRing(String s) {
        this(s.toCharArray());
    }

    public CharRing(char[] array) {
        super(array);
    }

    @Override
    protected char[] create(int size) {
        return new char[size];
    }

    /**
     * @see #_get(int)
     */
    public char _getChar(int absoluteIndex) {
        return array[absoluteIndex];
    }

    /**
     * @see #_set(int, Object)
     */
    public void _setChar(int absoluteIndex, char value) {
        array[absoluteIndex] = value;
    }

    /**
     * @see #get(int)
     */
    public char getChar(int offsetToBegin) {
        return _getChar((begin + offsetToBegin) % capacity);
    }

    /**
     * @see #set(int, Object)
     */
    public void setChar(int offsetToBegin, char value) {
        _setChar((begin + offsetToBegin) % capacity, value);
    }

    /**
     * @see #read()
     */
    public char readChar() {
        if (isEmpty())
            throw new BufferUnderflowException();
        char x = _getChar(begin++);
        begin %= capacity;
        full = false;
        return x;
    }

    /**
     * @see #write(Object)
     */
    public void writeChar(char value) {
        if (isFull())
            throw new BufferOverflowException();
        _setChar(end++, value);
        end %= capacity;
        if (end == begin)
            full = true;
    }

    public String readString(int len) {
        char[] buf = new char[len];
        len = read(buf, 0, len);
        return new String(buf, 0, len);
    }

    public String readString(int offsetToBegin, int len) {
        len = Math.min(size() - offsetToBegin, len);
        char[] buf = new char[len];
        read(offsetToBegin, buf, 0, len);
        return new String(buf, 0, len);
    }

    public int write(String s) {
        char[] buf = s.toCharArray();
        return write(buf, 0, buf.length);
    }

    public void write(int offsetToBegin, String s) {
        char[] buf = s.toCharArray();
        write(offsetToBegin, buf, 0, buf.length);
    }

    public void insert(int offsetToBegin, String s) {
        char[] buf = s.toCharArray();
        insert(offsetToBegin, buf);
    }

    public String toString(int off, int len) {
        char[] copy = copy(off, len);
        return new String(copy);
    }

    @Override
    public String toString() {
        return toString(0, size());
    }

}
