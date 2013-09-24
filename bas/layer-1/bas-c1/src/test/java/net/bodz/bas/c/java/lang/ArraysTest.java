package net.bodz.bas.c.java.lang;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.Arrays;

public class ArraysTest
        extends Assert {

    @Test(expected = NullPointerException.class)
    public void concatNull() {
        int[] a = null;
        Arrays.concat(a);
    }

    @Test
    public void concatSingle() {
        int[] a = { 1, 2 };
        int[] result = Arrays.concat(a);
        assertNotSame(result, a);
        assertArrayEquals(result, a);
    }

    @Test
    public void concat2() {
        int[] a = { 1, 2 };
        int[] b = { 3 };
        int[] expected = { 1, 2, 3 };
        int[] result = Arrays.concat(a, b);
        assertArrayEquals(expected, result);
    }

    @Test
    public void concat3() {
        int[] a = { 1, 2 };
        int[] b = { 3 };
        int[] c = { 4, 5 };
        int[] expected = { 1, 2, 3, 4, 5 };
        int[] result = Arrays.concat(a, b, c);
        assertArrayEquals(expected, result);
    }

    @Test
    public void indexOf() {
        int[] a = { 1, 2, 3, 4, 1, 2, 3, 2, 3, 4, 5 };
        int[] b = { 2, 3 };
        assertEquals(1, Arrays.indexOf(a, b));
        assertEquals(5, Arrays.indexOf(a, 2, b));
        assertEquals(7, Arrays.indexOf(a, 6, b));
        assertEquals(-1, Arrays.indexOf(a, 8, b));
    }

    @Test
    public void lastIndexOf() {
        int[] a = { 1, 2, 3, 4, 1, 2, 3, 2, 3, 4, 5 };
        int[] b = { 2, 3, 4 };
        assertEquals(7, Arrays.lastIndexOf(a, b));
        assertEquals(1, Arrays.lastIndexOf(a, 8, b));
        assertEquals(1, Arrays.lastIndexOf(a, 3, b));
        assertEquals(-1, Arrays.lastIndexOf(a, 2, b));
    }

}
