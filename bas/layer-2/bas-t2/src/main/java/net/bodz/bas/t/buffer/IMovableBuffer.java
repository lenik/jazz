package net.bodz.bas.t.buffer;

public interface IMovableBuffer {

    int size();

    int capacity();

    void ensureSize(int size);

    void resize(int newSize);

    void trimToSize();

}
