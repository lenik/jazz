package net.bodz.bas.t.coll;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.bodz.bas.t.pojo.Pair;

public class ListAsStringEntries<E>
        extends
        AbstractSet<Entry<String, E>> {

    List<E> list;

    public ListAsStringEntries(List<E> list) {
        this.list = list;
    }

    @Override
    public Iterator<Entry<String, E>> iterator() {
        Iterator<E> iterator = list.iterator();
        return new Iterator<Entry<String, E>>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Entry<String, E> next() {
                E element = iterator.next();
                String indexStr = String.valueOf(index++);
                return Pair.of(indexStr, element);
            }

            @Override
            public void remove() {
                iterator.remove();
            }

        };
    }

    @Override
    public int size() {
        return list.size();
    }

}
