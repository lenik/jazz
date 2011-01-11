package net.bodz.bas.unitperf.oats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IntArrayList {

    ArrayList<Integer> impl;

    public IntArrayList() {
        impl = new ArrayList<Integer>();
    }

    public IntArrayList(Collection<? extends Integer> c) {
        impl = new ArrayList<Integer>(c);
    }

    public IntArrayList(int initialCapacity) {
        impl = new ArrayList<Integer>(initialCapacity);
    }

    public boolean add(int e) {
        return impl.add(e);
    }

    public void clear() {
        impl.clear();
    }

    public boolean contains(int o) {
        return impl.contains(o);
    }

    public int get(int index) {
        return impl.get(index);
    }

    public int indexOf(int o) {
        return impl.indexOf(o);
    }

    public boolean isEmpty() {
        return impl.isEmpty();
    }

    public Iterator<Integer> iterator() {
        return impl.iterator();
    }

    public int remove(int index) {
        return impl.remove(index);
    }

    public Integer set(int index, int element) {
        return impl.set(index, element);
    }

    public int size() {
        return impl.size();
    }

    public int[] toArray() {
        Integer[] array = impl.toArray(new Integer[0]);
        int[] intv = new int[array.length];
        for (int i = 0; i < array.length; i++)
            intv[i] = array[i];
        return intv;
    }

    public String toString() {
        return impl.toString();
    }

}
