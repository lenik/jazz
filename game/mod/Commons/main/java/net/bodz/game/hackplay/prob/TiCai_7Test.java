package net.bodz.game.hackplay.prob;

import static org.junit.Assert.*;

import org.junit.Test;

public class TiCai_7Test {

    @Test
    public void testTest() {
        int[] number = { 1, 2, 3, 4, 5, 6, 7 };
        assertEquals(0, TiCai_7.test(number, 9999999));
        assertEquals(0, TiCai_7.test(number, 0));
        assertEquals(0, TiCai_7.test(number, 7123456));
        assertEquals(1, TiCai_7.test(number, 8921347));
        assertEquals(2, TiCai_7.test(number, 1275823));
        assertEquals(1, TiCai_7.test(number, 1986524));
        assertEquals(1, TiCai_7.test(number, 5126517));
        assertEquals(4, TiCai_7.test(number, 1234067));
        assertEquals(3, TiCai_7.test(number, 1204560));
        assertEquals(6, TiCai_7.test(number, 1234560));
        assertEquals(6, TiCai_7.test(number, 234567));
        assertEquals(7, TiCai_7.test(number, 1234567));
    }

}
