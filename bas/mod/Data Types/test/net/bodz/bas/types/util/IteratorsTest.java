package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IteratorsTest {

    @Test
    public void iterWraps() throws Exception {
        class It extends PrefetchedIterator<Integer> {
            int n = 0;

            @Override
            protected Object fetch() {
                if (n >= 100)
                    return END;
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

}
