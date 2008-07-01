package net.bodz.bas.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ArrayListl<E> extends ArrayList<E> implements Listl<E> {

    private static final long serialVersionUID = -4885514372711214503L;

    public ArrayListl() {
        super();
    }

    public ArrayListl(Collection<? extends E> c) {
        super(c);
    }

    public ArrayListl(int initialCapacity) {
        super(initialCapacity);
    }

    public boolean addl(E... es) {
        boolean d = false;
        for (E e : es) {
            d = add(e) || d;
        }
        return d;
    }

    public boolean removel(E... es) {
        boolean d = false;
        for (E e : es)
            d = remove(e) || d;
        return d;
    }

    public void removel(int... indexes) {
        switch (indexes.length) {
        case 1:
            remove(indexes[0]);
        case 0:
            return;
        }
        int[] order = new int[indexes.length];
        System.arraycopy(indexes, 0, order, 0, indexes.length);
        Arrays.sort(order);
        if (order[0] < 0)
            throw new IndexOutOfBoundsException("" + order[0]);
        if (order[order.length - 1] >= size())
            throw new IndexOutOfBoundsException("" + order[order.length - 1]);
        for (int i = order.length - 1; i >= 0; i--)
            remove(i);
    }

}
