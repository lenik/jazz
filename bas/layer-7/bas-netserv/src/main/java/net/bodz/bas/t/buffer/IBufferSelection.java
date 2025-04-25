package net.bodz.bas.t.buffer;

import java.nio.BufferUnderflowException;

public interface IBufferSelection<T>
        extends IBuffer<T> {

    default T get() {
        return get(advance(1));
    }

    default void put(T value) {
        set(advance(1), value);
    }

    int limit();

    void limit(int limit);

    int position();

    void position(int position);

    /**
     * Set position := position + n
     *
     * @return The position before advance.
     */
    int advance(int n);

    int remaining();

    default boolean hasRemaining() {
        return remaining() != 0;
    }

    default void del() {
        if (!hasRemaining())
            throw new BufferUnderflowException();
        delete(position());
    }

    default void del(int len) {
        if (len > remaining())
            throw new BufferUnderflowException();
        delete(position(), len);
    }

    default void flip() {
        int position = position();
        limit(position);
        position(0);
    }

    void compact();

    void mark();

    void reset();

    @Override
    void clear();

}
