package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.commons.iterators.Iterates;
import net.bodz.bas.commons.iterators.Iterators;
import net.bodz.bas.commons.iterators.PrefetchedIterator;

import org.junit.Test;

public class IteratorsTest {

    Number a[] = { 10, 20, 30, 40, 50 };
    Number b[] = { 22, 23, 24, 25 };
    Number c[] = { 5, 15, 25, 26, 28, 45, 55 };
    Number d[] = { 60 };
    Number e[] = {};
    Number f[] = { 31, 53, 22, 15, 5 };

    List<Number> al = Arrays.asList(a);
    List<Number> bl = Arrays.asList(b);
    List<Number> cl = Arrays.asList(c);
    List<Number> dl = Arrays.asList(d);
    List<Number> el = Arrays.asList(e);
    List<Number> fl = Arrays.asList(f);

    @Test
    public void iterWraps() throws Exception {
        class It extends PrefetchedIterator<Integer> {
            int n = 0;

            @Override
            protected Integer fetch() {
                if (n >= 100)
                    return end();
                return ++n;
            }
        }

        int t = 0;
        It x = new It();
        while (x.hasNext())
            t += x.next();
        assertEquals(5050, t);

        Iterable<Integer> set = Iterates.iterate(It.class, this);
        t = 0;
        for (Integer n : set)
            t += n;
        assertEquals(5050, t);
    }

    String scan(Iterator<?> it) {
        StringBuffer buf = null;
        while (it.hasNext()) {
            Object v = it.next();
            if (buf == null) {
                buf = new StringBuffer();
                buf.append("["); //$NON-NLS-1$
            } else
                buf.append(", "); //$NON-NLS-1$
            buf.append(v);
        }
        if (buf == null)
            return "[]"; //$NON-NLS-1$
        buf.append("]"); //$NON-NLS-1$
        return buf.toString();
    }

    @SuppressWarnings("unchecked")
    public void testConcat() {
        Iterator<Number> ad = Iterators.concat(al.iterator(), dl.iterator());
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ad)); //$NON-NLS-1$
        Iterator<Number> ade = Iterators.concat(al.iterator(), dl.iterator(), el.iterator());
        assertEquals("[10, 20, 30, 40, 50, 60]", scan(ade)); //$NON-NLS-1$
        Iterator<Number> de = Iterators.concat(dl.iterator(), el.iterator());
        assertEquals("[60]", scan(de)); //$NON-NLS-1$
        Iterator<Number> f = Iterators.concat(fl.iterator());
        assertEquals("[31, 53, 22, 15, 5]", scan(f)); //$NON-NLS-1$
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testWeave() {
        Iterator<Number> w1 = Iterators.weave(al.iterator());
        assertEquals("[10, 20, 30, 40, 50]", scan(w1)); //$NON-NLS-1$

        Iterator<Number> w3 = Iterators.weave(al.iterator(), cl.iterator());
        assertEquals("[5, 10, 15, 20, 25, 26, 28, 30, 40, 45, 50, 55]", //$NON-NLS-1$
                scan(w3));

        Iterator<Number> w5 = Iterators.weave(//
                al.iterator(),//
                bl.iterator(),//
                cl.iterator(),//
                dl.iterator(),//
                el.iterator()//
                );
        assertEquals("[5, 10, 15, 20, 22, 23, 24, 25, 25, 26, 28, 30, 40, 45, 50, 55, 60]", //$NON-NLS-1$
                scan(w5));

        // 31, 53, 22, 15, 5
        Iterator<Number> wf1 = Iterators.weave(//
                al.iterator(),//
                fl.iterator()//
                );
        assertEquals("[10, 20, 30, 31, 40, 50, 53, 22, 15, 5]", scan(wf1)); //$NON-NLS-1$

        Iterator<Number> wf2 = Iterators.weave(//
                fl.iterator(),//
                al.iterator()//
                );
        assertEquals("[10, 20, 30, 31, 40, 50, 53, 22, 15, 5]", scan(wf2)); //$NON-NLS-1$
    }

}
