package net.bodz.bas.types.buf;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public interface Ring<A, T> {

    A array();

    int capacity();

    int begin();

    Ring<A, T> begin(int newBegin);

    int end();

    Ring<A, T> end(int newEnd);

    int size();

    Ring<A, T> size(int newSize);

    boolean isEmpty();

    boolean isFull();

    int free();

    void reset();

    T _get(int absoluteIndex);

    void _set(int absoluteIndex, T value);

    T get(int offsetToBegin);

    void set(int offsetToBegin, T value);

    A copy(int off, int len);

    A cut(int off, int len);

    void delete(int off, int len);

    void copy(int srcOff, int dstOff, int len);

    void insert(int off, T value);

    void insert(int offsetToBegin, A buf, int off, int len);

    T read() throws BufferUnderflowException;

    /**
     * @return 0 if buffer empty
     */
    int read(A buf, int off, int len);

    void write(T value) throws BufferOverflowException;

    /**
     * @return 0 if no more space to write
     */
    int write(A buf, int off, int len);

    T remove(int off);

}
