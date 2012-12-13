package net.bodz.bas.t.iterator;

import java.util.Iterator;

import org.junit.Test;

import net.bodz.bas.c.java.util.Iterables;

public class IteratorsTest
        extends IteratorTestBase {

    @Test
    public void testConcat() {
        Iterator<Integer> ad = Iterables.concat(al, dl).iterator();
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ad));

        Iterator<Integer> ade = Iterables.concat(al, dl, el).iterator();
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ade));

        Iterator<Integer> de = Iterables.concat(dl, el).iterator();
        assertEquals("[60]", scan(de));

        Iterator<Integer> f = Iterables.concat(fl).iterator();
        assertEquals("[31, 53, 22, 15, 5]", scan(f));
    }

}
