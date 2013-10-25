package net.bodz.bas.t.iterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class RecIteratorTest
        extends Assert {

    List<Object> tree = Arrays.asList( //
            1, //
            Arrays.asList(2, 3),//
            Arrays.asList(),//
            Arrays.asList(4),//
            Arrays.asList(//
                    Arrays.asList(), //
                    Arrays.asList(5, 6), //
                    Arrays.asList(7)), //
            Arrays.asList(8), //
            Arrays.asList());

    @Test
    public void test1() {
        FlattenCollectionIterator it = new FlattenCollectionIterator(tree);
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertEquals(4, it.next());
        assertEquals(5, it.next());
        assertEquals(6, it.next());
        assertEquals(7, it.next());
        assertEquals(8, it.next());
        assertFalse(it.hasNext());
    }

}

class FlattenCollectionIterator
        extends RecIterator<Object> {

    public FlattenCollectionIterator(Collection<?> start) {
        super(start.iterator());
    }

    @Override
    protected Object fetch() {
        Object obj = super.fetch();
        if (obj instanceof Collection<?>)
            return fetch();
        else
            return obj;
    }

    @Override
    protected Iterator<?> expand(Object node) {
        if (node instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) node;
            return collection.iterator();
        } else
            return null;
    }

}