package net.bodz.bas.t.buffer;

import java.lang.reflect.Array;
import java.util.Objects;

import net.bodz.bas.meta.decl.NotNull;

public interface IBuffer<T> {

    @NotNull
    Class<T> getComponentType();

    void append(T element);

    default void append(@NotNull T[] array, int off, int len) {
        int pos = off;
        for (int i = 0; i < len; i++)
            append(array[pos++]);
    }

    default void append(@NotNull IBuffer<T> src) {
        int len = src.length();
        for (int i = 0; i < len; i++) {
            T el = src.get(i);
            append(el);
        }
    }

    default void moveTo(@NotNull IBuffer<T> dst) {
        dst.append(this);
        this.clear();
    }

    void clear();

    T get(int i);

    void set(int i, T value);

    int capacity();

    int length();

    void ensureCapacity(int required);

    default void ensureAvailable(int required) {
        int requiredCapacity = length() + required;
        ensureCapacity(requiredCapacity);
    }

    void delete(int index);

    void delete(int off, int len);

    /**
     * Truncate the buffer to be no more than the specified length.
     */
    void truncate(int maxLength);

    default void setLength(int len, T fill) {
        ensureCapacity(len);
        truncate(len);

        int add = len - length();
        for (int i = 0; i < add; i++)
            append(fill);
    }

    default boolean isEmpty() {
        return length() == 0;
    }

    default boolean isNotEmpty() {
        return length() != 0;
    }

    default boolean isFull() {
        return length() == capacity();
    }

    default boolean isNotFull() {
        return length() != capacity();
    }

    default int indexOf(T element) {
        return indexOf(element, 0, length());
    }

    default int indexOf(T element, int from, int to) {
        int beginIndex = from;
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        int endIndex = to;
        int len = length();
        if (endIndex > len) {
            endIndex = len;
        }
        if (beginIndex > endIndex) {
            return -1;
        }
        for (int i = beginIndex; i < endIndex; i++) {
            if (Objects.equals(get(i), element)) {
                return i;
            }
        }
        return -1;
    }

    default int _indexOf(T element) {
        return _indexOf(element, 0, length());
    }

    default int _indexOf(T element, int from, int to) {
        int beginIndex = from;
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        int endIndex = to;
        int len = length();
        if (endIndex > len) {
            endIndex = len;
        }
        if (beginIndex > endIndex) {
            return -1;
        }
        for (int i = beginIndex; i < endIndex; i++) {
            if (get(i) == element) {
                return i;
            }
        }
        return -1;
    }

    default T[] toArray() {
        int n = length();
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(getComponentType(), n);
        for (int i = 0; i < n; i++)
            array[i] = get(i);
        return array;
    }

}
