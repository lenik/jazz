package net.bodz.bas.lop.fsm;

import org.junit.Test;

public class CharSetSubtractionTest extends TestData {

    @Test
    public void test() throws Exception {

        CharSetSubtraction s = new CharSetSubtraction(_3to7, _5to9);

        assert !s.contains('2');
        assert s.contains('3');
        assert s.contains('4');
        assert !s.contains('5');

        CharSet reduced = s.reduce();
        System.out.println("Reduced: " + reduced);
    }

}
