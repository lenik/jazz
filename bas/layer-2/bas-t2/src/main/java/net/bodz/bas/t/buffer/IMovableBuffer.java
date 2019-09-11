package net.bodz.bas.t.buffer;

public interface IMovableBuffer {

    int SIZE_MAX = 0x7ffffff0;

    int size();

    int capacity();

    void ensureSize(int size);

    void resize(int newSize);

    void trimToSize();

}
