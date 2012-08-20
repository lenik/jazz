package net.bodz.bas.collection.iterator;

import java.util.Iterator;

import org.junit.Test;

import net.bodz.bas.util.iter.Iterators;

public class IteratorsTest
        extends IteratorTestBase {

    @Test
    public void testConcat() {
        Iterator<Integer> ad = Iterators.concat(al, dl);
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ad));

        Iterator<Integer> ade = Iterators.concat(al, dl, el);
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ade));

        Iterator<Integer> de = Iterators.concat(dl, el);
        assertEquals("[60]", scan(de));

        Iterator<Integer> f = Iterators.concat(fl);
        assertEquals("[31, 53, 22, 15, 5]", scan(f));
    }

}
