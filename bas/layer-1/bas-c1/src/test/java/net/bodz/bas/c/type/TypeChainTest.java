package net.bodz.bas.c.type;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.t.iterator.Iterables;

public class TypeChainTest
        extends Assert {

    @Test
    public void testAncestors()
            throws Exception {
        List<?> list = Iterables.toList(TypeChain.ancestors(TreeOutImpl.class, Object.class));
        assertEquals(14, list.size());

        Set<?> set = Iterables.toSet(list);
        assertEquals(list.size(), set.size());
    }

    @Test
    public void statAncestors()
            throws Exception {
        int n = 100000;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Iterables.toList(TypeChain.ancestors(TreeOutImpl.class));
        }
        long duration = System.currentTimeMillis() - t1;
        System.out.println("1000 calls take: " + (float) duration / (n / 1000) + " ms");
    }

}
