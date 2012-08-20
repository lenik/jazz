package net.bodz.bas.collection.iterator;

import java.util.Iterator;

import org.junit.Test;

import net.bodz.bas.util.iter.Iterators2;
import net.bodz.bas.util.primitive.IntegerComparator;

public class WeaveIteratorTest
        extends IteratorTestBase {

    @Deprecated
    @Test
    public void testWeave() {
        Iterator<Integer> w1 = Iterators2.weave(IntegerComparator.INSTANCE, //
                al.iterator());
        assertEquals("[10, 20, 30, 40, 50]", scan(w1));

        Iterator<Integer> w3 = Iterators2.weave(IntegerComparator.INSTANCE, //
                al.iterator(), cl.iterator());
        assertEquals("[5, 10, 15, 20, 25, 26, 28, 30, 40, 45, 50, 55]", scan(w3));

        Iterator<Integer> w5 = Iterators2.weave(IntegerComparator.INSTANCE, //
                al.iterator(),//
                bl.iterator(),//
                cl.iterator(),//
                dl.iterator(),//
                el.iterator()//
                );
        assertEquals("[5, 10, 15, 20, 22, 23, 24, 25, 25, 26, 28, 30, 40, 45, 50, 55, 60]", scan(w5));

        // 31, 53, 22, 15, 5
        Iterator<Integer> wf1 = Iterators2.weave(IntegerComparator.INSTANCE, //
                al.iterator(),//
                fl.iterator()//
                );
        assertEquals("[10, 20, 30, 31, 40, 50, 53, 22, 15, 5]", scan(wf1));

        Iterator<Integer> wf2 = Iterators2.weave(IntegerComparator.INSTANCE, //
                fl.iterator(),//
                al.iterator()//
                );
        assertEquals("[10, 20, 30, 31, 40, 50, 53, 22, 15, 5]", scan(wf2));
    }

}
