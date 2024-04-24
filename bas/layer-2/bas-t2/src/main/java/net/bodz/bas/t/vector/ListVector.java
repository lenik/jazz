package net.bodz.bas.t.vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListVector<T>
        implements
            AnyVector<T> {

    List<T> list;

    public ListVector(int size) {
        this.list = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            list.add(null);
    }

    public ListVector(List<T> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public void set(int index, T value) {
        list.set(index, value);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
