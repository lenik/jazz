package net.bodz.bas.collection.buffer;

import java.lang.reflect.Array;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.OutOfDomainException;

public abstract class AbstractRingBuffer<A, T>
        implements IRingBuffer<A, T> {

    protected final A array;
    protected int capacity;
    protected int begin;
    protected int end;
    protected boolean full;

    public AbstractRingBuffer(int capacity) {
        array = create(capacity);
        this.capacity = capacity;
    }

    public AbstractRingBuffer(A array) {
        if (array == null)
            throw new NullPointerException("array");
        this.array = array;
        capacity = Array.getLength(array);
        if (capacity == 0)
            throw new IllegalArgumentException("empty array isn't allowed");
        full = true;
    }

    protected abstract A create(int size);

    @Override
    public A array() {
        return array;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int begin() {
        return begin;
    }

    @Override
    public IRingBuffer<A, T> begin(int newBegin) {
        if (newBegin < 0)
            throw new OutOfDomainException("newBegin", newBegin, 0);
        if (newBegin > capacity)
            throw new OutOfDomainException("newBegin", newBegin, capacity);
        this.begin = newBegin;
        return this;
    }

    @Override
    public int end() {
        return end;
    }

    @Override
    public IRingBuffer<A, T> end(int newEnd) {
        if (newEnd < 0)
            throw new OutOfDomainException("newEnd", newEnd, 0);
        if (newEnd > capacity)
            throw new OutOfDomainException("newEnd", newEnd, capacity);
        this.end = newEnd;
        return this;
    }

    @Override
    public int size() {
        if (full)
            return capacity;
        if (begin <= end)
            return end - begin;
        else
            return capacity - begin + end;
    }

    @Override
    public IRingBuffer<A, T> size(int newSize) {
        end = (begin + newSize) % capacity;
        full = newSize == capacity;
        return this;
    }

    @Override
    public int free() {
        return capacity - size();
    }

    @Override
    public boolean isEmpty() {
        return begin == end && !full;
    }

    @Override
    public boolean isFull() {
        return full;
    }

    @Override
    public void reset() {
        begin = end = 0;
        full = false;
    }

    @Override
    public T _get(int absoluteIndex) {
        return (T) Array.get(array, absoluteIndex);
    }

    @Override
    public void _set(int absoluteIndex, T value) {
        Array.set(array, absoluteIndex, value);
    }

    @Override
    public T get(int offsetToBegin) {
        if (offsetToBegin >= size())
            throw new OutOfDomainException("offsetToBegin", offsetToBegin, size());
        return _get((begin + offsetToBegin) % capacity);
    }

    @Override
    public void set(int offsetToBegin, T value) {
        if (offsetToBegin >= size())
            throw new OutOfDomainException("offsetToBegin", offsetToBegin, size());
        _set((begin + offsetToBegin) % capacity, value);
    }

    @Override
    public void copy(int srcOff, int dstOff, int len) {
        int maxoff = Math.max(srcOff, dstOff);
        if (maxoff + len > size())
            throw new IndexOutOfBoundsException("maxoff=" + maxoff);
        int s0 = (begin + srcOff) % capacity;
        int d0 = (begin + dstOff) % capacity;
        while (len > 0) {
            int max = Math.max(s0, d0);
            int n = Math.min(capacity - max, len);
            System.arraycopy(array, s0, array, d0, n);
            s0 = (s0 + n) % capacity;
            d0 = (d0 + n) % capacity;
            len -= n;
        }
    }

    @Override
    public A copy(int off, int len) {
        if (len < 0)
            throw new OutOfDomainException("len", len, 0);
        if (len > size())
            throw new OutOfDomainException("len", len, size());
        A copy = create(len);
        int start = (begin + off) % capacity;
        int p = Math.min(len, capacity - start);
        System.arraycopy(array, start, copy, 0, p);
        len -= p;
        if (len > 0)
            System.arraycopy(array, 0, copy, p, len);
        return copy;
    }

    @Override
    public A cut(int off, int len) {
        A copy = copy(off, len);
        end = (end - len) % capacity;
        return copy;
    }

    @Override
    public void delete(int off, int len) {
        if (len < 0)
            throw new OutOfDomainException("len", len, 0);
        if (len > size())
            throw new OutOfDomainException("len", len, size());
        if (len == 0)
            return;
        int doff = off + len;
        copy(doff, off, size() - doff);
        end = (end - len) % capacity;
        full = false;
    }

    @Override
    public T read()
            throws BufferUnderflowException {
        if (isEmpty())
            throw new BufferUnderflowException();
        T x = _get(begin++);
        begin %= capacity;
        full = false;
        return x;
    }

    @Override
    public void write(T value)
            throws BufferOverflowException {
        if (isFull())
            throw new BufferOverflowException();
        _set(end++, value);
        end %= capacity;
        if (end == begin)
            full = true;
    }

    public void read(int offsetToBegin, A buf, int off, int len) {
        if (buf == null)
            throw new NullPointerException("buf");
        int _off = (begin + offsetToBegin) % capacity;
        while (len > 0) {
            int n = Math.min(capacity - _off, len);
            System.arraycopy(array, _off, buf, off, n);
            _off = (_off + n) % capacity;
            off += n;
            len -= n;
        }
    }

    public void write(int offsetToBegin, A buf, int off, int len) {
        if (buf == null)
            throw new NullPointerException("buf");
        int _off = (begin + offsetToBegin) % capacity;
        while (len > 0) {
            int n = Math.min(capacity - _off, len);
            System.arraycopy(buf, off, array, _off, n);
            _off = (_off + n) % capacity;
            off += n;
            len -= n;
        }
    }

    @Override
    public int read(A buf, int off, int len) {
        if (buf == null)
            throw new NullPointerException("buf");
        if (isEmpty())
            return 0;
        if (len == 0)
            return 0;
        len = Math.min(len, size());
        read(0, buf, off, len);
        begin = (begin + len) % capacity;
        full = false;
        return len;
    }

    @Override
    public int write(A buf, int off, int len) {
        if (buf == null)
            throw new NullPointerException("buf");
        if (isFull())
            return 0;
        if (len == 0)
            return 0;
        int size = size();
        len = Math.min(len, capacity - size);
        size(size + len);
        write(size, buf, off, len);
        return len;
    }

    @Override
    public T remove(int off) {
        T x = get(off);
        delete(off, 1);
        return x;
    }

    @Override
    public void insert(int offsetToBegin, A buf, int off, int len) {
        if (len > free())
            throw new BufferOverflowException();
        int oldsize = size();
        size(oldsize + len);
        copy(offsetToBegin, offsetToBegin + len, oldsize - offsetToBegin);
        int p = Math.min(len, capacity - off);
        System.arraycopy(buf, off, array, begin + offsetToBegin, p);
        throw new NotImplementedException();
    }

    @Override
    public void insert(int off, Object value) {
        throw new NotImplementedException();
    }

}
