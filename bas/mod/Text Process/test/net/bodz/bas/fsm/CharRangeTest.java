package net.bodz.bas.fsm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CharRangeTest {

    @Test
    public void test() throws Exception {
        CharRange a = TestData._0to9;
        CharRange b = TestData._3to7;
        CharRange c = TestData._5to9;
        CharRange d = TestData._atoz;

        assertEquals(CharSet.ALL_THAT, a.intersects(b));
        assertEquals(CharSet.ALL_THIS, b.intersects(a));
        assertEquals(CharSet.NONE, a.intersects(d));

        SplitResult a_b = a.split(b);
        CharSet[] ab = a_b.getCommon();
        assertEquals(1, ab.length);
        assertEquals(ab[0], b);

        SplitResult b_c = b.split(c);
        CharSet[] bc = b_c.getCommon();
        assertEquals(1, bc.length);
        assertEquals("[5-7]", bc[0].toString());

        CharSet[] bc_bOnly = b_c.getThis();
        CharSet[] bc_cOnly = b_c.getThat();
        assertEquals(1, bc_bOnly.length);
        assertEquals("[3-4]", bc_bOnly[0].toString());
        assertEquals(1, bc_cOnly.length);
        assertEquals("[8-9]", bc_cOnly[0].toString());
    }

}
