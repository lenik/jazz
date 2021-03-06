package net.bodz.pkg.sis.util;

import org.junit.Assert;
import org.junit.Test;

public class IndicesTest
        extends Assert {

    @Test
    public void testInverse()
            throws Exception {
        // domain: 1..9
        int[] selected = { 3, 4, 7, 8 };
        int[] expected = { 1, 2, 5, 6, 9, };
        int[] inverse = Indices.inverse(1, 10, selected);
        assertArrayEquals(expected, inverse);
    }

}
