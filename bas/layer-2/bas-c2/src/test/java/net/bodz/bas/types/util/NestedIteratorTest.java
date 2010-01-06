package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

import net.bodz.bas.collection.iterator.NestedIterator;

import org.junit.Test;

public class NestedIteratorTest {

    static <T> Iterator<T> iter(Iterable<T> iterable) {
        return iterable.iterator();
    }

    static <T> Iterator<T> iter(T[] array) {
        return Arrays.asList(array).iterator();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test1() throws Exception {
        Integer[] v1 = { 10, 20, 30 };
        Integer[] v2 = {};
        Integer[] v3 = {};
        Integer[] v4 = { 40 };
        Integer[] v5 = { 50 };
        Integer[] v6 = {};
        NestedIterator<Integer> nit = new NestedIterator<Integer>(//
                iter(v1), // 
                iter(v2), // 
                iter(v3), // 
                iter(v4), // 
                iter(v5), // 
                iter(v6) // 
        );
        assertEquals(true, nit.hasNext());
        assertEquals((Integer) 10, nit.next());
        assertEquals((Integer) 20, nit.next());
        assertEquals((Integer) 30, nit.next());
        assertEquals((Integer) 40, nit.next());
        assertEquals((Integer) 50, nit.next());
        assertEquals(false, nit.hasNext());
    }

    public static void main(String[] args) {
        Integer[] v = { 1, 2, 3 };
        for (Class<?> iface : v.getClass().getInterfaces())
            System.out.println(iface);
    }

}
