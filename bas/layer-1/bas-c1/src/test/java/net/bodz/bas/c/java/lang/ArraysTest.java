package net.bodz.bas.c.java.lang;

import org.junit.Assert;
import org.junit.Test;

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

}
