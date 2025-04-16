package net.bodz.bas.t.pool;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.UnderflowException;
import net.bodz.bas.meta.decl.NotNull;

public interface IPool<T> {

    int available();

    default boolean isEmpty() {
        return available() == 0;
    }

    default boolean isNotEmpty() {
        return available() != 0;
    }

    @NotNull
    T allocate()
            throws UnderflowException;

    void free(@NotNull T resource);

    default List<T> allocate(int num) {
        List<T> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++)
            list.add(allocate());
        return list;
    }

}
