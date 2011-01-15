package net.bodz.bas.collection.iterator;

import java.util.Iterator;

public class ConcatIteratorTest
        extends IteratorTestBase {

    @SuppressWarnings("unchecked")
    public void testConcat() {
        Iterator<Integer> ad = new ConcatIterator<Integer>(al.iterator(), dl.iterator());
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ad));
        Iterator<Integer> ade = new ConcatIterator<Integer>(al.iterator(), dl.iterator(), el.iterator());
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ade));
        Iterator<Integer> de = new ConcatIterator<Integer>(dl.iterator(), el.iterator());
        assertEquals("[60]", scan(de));
        Iterator<Integer> f = new ConcatIterator<Integer>(fl.iterator());
        assertEquals("[31, 53, 22, 15, 5]", scan(f));
    }

}
